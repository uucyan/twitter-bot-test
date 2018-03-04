package testbot.twitterbottest.bots

import org.springframework.beans.factory.annotation.Autowired
/* import org.springframework.stereotype.Component */
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.social.DuplicateStatusException
import org.springframework.web.client.ResourceAccessException
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
  protected var twitterApplication: TwitterApplication? = null
  protected var twitter: TwitterTemplate? = null

  protected fun setTwitter() {
    // ボットのTwitter情報を取得
    twitterApplication = twitterApplicationService.findByBotType(getEnvBotType())

    // 取得したアクセス情報を元にテンプレートを取得
    twitter = TwitterTemplate(twitterApplication?.consumer_key,
                              twitterApplication?.consumer_secret,
                              twitterApplication?.access_token,
                              twitterApplication?.access_token_secret)
  }

  /**
   * ツイート送信
   *
   * @param tweet String
   */
  protected fun sendTweet(tweet: String) {
    var errorMessage = ""
    try {
      twitter?.run {
        timelineOperations().updateStatus(tweet)
      }
    } catch (e: DuplicateStatusException) {
      // ツイートの重複エラー
      errorMessage = "${e}"
    } catch (e: ResourceAccessException) {
      // 何らかの理由でTwitter API側でPOSTを拒否された
      errorMessage = "${e}"
    } finally {
      saveTweetLog(tweet, errorMessage)
    }
  }

  /**
   * ツイートのログを保存
   *
   * @param tweet String
   * @param errorMessage String
   */
  protected fun saveTweetLog(tweet: String, errorMessage: String) {
    tweetLogService.save(TweetLog(null, twitterApplication?.id, tweet, errorMessage))
  }

  /**
   * Twitter情報の存在チェック
   *
   * @return Boolean
   */
  protected fun isExistTwitter(): Boolean = twitter is TwitterTemplate && twitterApplication is TwitterApplication

  /**
   * 環境を判定してbotTypeを返却
   *
   * @return String
   */
  protected fun getEnvBotType(): String = if (environment.getProperty("spring.config.name") == DEV_ENV) DEV_ENV else botType
}
