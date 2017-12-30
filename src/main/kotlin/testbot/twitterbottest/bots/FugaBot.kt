package testbot.twitterbottest.bots

import org.springframework.stereotype.Component
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.social.DuplicateStatusException
import org.springframework.core.env.Environment
import testbot.twitterbottest.service.TwitterApplicationService
import testbot.twitterbottest.service.TweetLogService
import testbot.twitterbottest.model.TweetLog
import testbot.twitterbottest.model.TwitterApplication
import testbot.twitterbottest.bots.AbstractBot
import me.mattak.moment.Moment
import java.util.Random

@Component
class FugaBot(twitterApplicationService: TwitterApplicationService,
              tweetLogService: TweetLogService,
              environment: Environment): AbstractBot(twitterApplicationService, tweetLogService, environment) {

  companion object {
    // 重複処理実行の最大回数
    const val MAX_DUPLICATE_COUNT = 10
  }

  override val botType = "prod"

  // テスト用のツイート内容
  private val tweets = arrayOf(
    "つぶやきてすと",
    "ついーとてすと",
    "て・す・と♡"
  )

  private val rand = Random()

  // 重複回数
  private var duplicateCount = 0

  fun execute() {
    if (twitter !is TwitterTemplate || twitterApplication !is TwitterApplication) {
      println("Twitterの情報を取得できませんでした。")
      return
    }

    // ツイート内容生成
    val tweet = "${tweets[rand.nextInt(tweets.size)]} ${Moment().format("yyyy/MM/dd HH:mm:ss")}"
    try {
      // ツイート処理実行
      twitter.timelineOperations().updateStatus(tweet)
    } catch (e: DuplicateStatusException) {
      // Twitterの重複エラーが発生した場合、指定回数分再帰的に処理を繰り返す。
      duplicateCount += 1
      if (duplicateCount <= MAX_DUPLICATE_COUNT) {
        println("ツイート内容が重複しました。再度ツイート処理を実行します。")
        println("重複回数：${duplicateCount}")
        execute()
      } else {
        println("重複回数が${duplicateCount}回に到達。処理を終了します。")
        duplicateCount = 0
      }
    } finally {
      // 今のところここで処理する内容ないけどfinally定義しておく
    }

    // ツイート内容のログを保存
    val tweetLog = TweetLog(null, twitterApplication.id, tweet, false)
    tweetLogService.save(tweetLog)
  }
}
