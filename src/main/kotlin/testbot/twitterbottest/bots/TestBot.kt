package testbot.twitterbottest.bots

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.social.DuplicateStatusException
import testbot.twitterbottest.service.TwitterKeysService
import me.mattak.moment.Moment
import java.util.Random

@Component
class TestBot @Autowired constructor(private val twitterKeysService: TwitterKeysService) {

  companion object {
    const val BOT_TYPE = "test"
    // 重複処理実行の最大回数
    const val MAX_DUPLICATE_COUNT = 10
  }

  // テスト用のツイート内容
  private val tweets = arrayOf(
    "つぶやきてすと",
    "ついーとてすと",
    "て・す・と♡"
  )

  private val rand = Random()

  // DBから取得したデータ
  private val twitterKeys = twitterKeysService.findByBotType(BOT_TYPE)

  // 重複回数
  private var duplicateCount = 0

  fun execute() {
    val twitter = TwitterTemplate(twitterKeys.consumer_key, twitterKeys.consumer_secret, twitterKeys.access_token, twitterKeys.access_token_secret)

    // ツイート処理実行
    // Twitterの重複エラーが発生した場合、指定回数分再帰的に処理を繰り返す。
    try {
      twitter.timelineOperations().updateStatus("${tweets[rand.nextInt(tweets.size)]} ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
    } catch (e: DuplicateStatusException) {
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
  }
}
