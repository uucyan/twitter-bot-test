package testbot.twitterbottest.bots

import org.springframework.beans.factory.annotation.Autowired
/* import org.springframework.stereotype.Component */
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.social.DuplicateStatusException
import org.springframework.core.env.Environment
import testbot.twitterbottest.service.TwitterApplicationService
import testbot.twitterbottest.service.TweetLogService
import testbot.twitterbottest.model.TweetLog
import testbot.twitterbottest.model.TwitterApplication
import me.mattak.moment.Moment
import java.util.Random

abstract class AbstractBot @Autowired constructor(private val twitterApplicationService: TwitterApplicationService,
                                                  protected val tweetLogService: TweetLogService,
                                                  private val environment: Environment) {

  companion object {
    private const val DEV_ENV = "dev"
  }

  abstract val botType: String
  protected val twitterApplication: TwitterApplication?
  protected val twitter: TwitterTemplate?

  init {
    // 開発環境の場合はテスト用のボットを使用する
    val env = environment.getProperty("spring.config.name")

    // ボットのTwitter情報を取得
    twitterApplication = twitterApplicationService.findByBotType(if (env == DEV_ENV) DEV_ENV else botType)

    // 取得したアクセス情報を元にテンプレートを取得
    twitter = TwitterTemplate(twitterApplication.consumer_key,
                              twitterApplication.consumer_secret,
                              twitterApplication.access_token,
                              twitterApplication.access_token_secret)
  }

  /**
   * ツイート送信
   *
   * @param tweet String
   */
  protected fun sendTweet(tweet: String) {
    try {
      twitter!!.timelineOperations().updateStatus(tweet)
      saveTweetLog(tweet, false)
    } catch (e: DuplicateStatusException) {
      saveTweetLog(tweet, true)
    } finally {
      // 今のところここで処理する内容ないけどfinally定義しておく
    }
  }

  /**
   * ツイートのログを保存
   *
   * @param tweet String
   * @param isDuplicateError Boolean
   */
  protected fun saveTweetLog(tweet: String, isDuplicateError: Boolean) {
    tweetLogService.save(TweetLog(null, twitterApplication!!.id, tweet, isDuplicateError))
  }

  /**
   * Twitter情報の存在チェック
   */
  protected fun isExistTwitter(): Boolean = twitter is TwitterTemplate && twitterApplication is TwitterApplication
}
