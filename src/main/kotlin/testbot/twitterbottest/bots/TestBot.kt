package testbot.twitterbottest.bots

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.social.DuplicateStatusException
import testbot.twitterbottest.model.Test
import testbot.twitterbottest.service.TestService
import me.mattak.moment.Moment
import java.util.Random

@Component
class TestBot @Autowired constructor(private val testService: TestService) {

  companion object {
    // 重複処理実行の最大回数
    const val MAX_DUPLICATE_COUNT = 13
  }

  // テスト用のツイート内容
  private val tweets = arrayOf(
    "ツイート1",
    "ツイート2",
    "ツイート3",
    "ツイート4",
    "ツイート5",
    "ツイート6",
    "ツイート7",
    "ツイート8",
    "ツイート9",
    "ツイート10",
    "ツイート11",
    "ツイート12",
    "ツイート13"
  )

  private val rand = Random()

  // DBから取得したデータ
  private val test = testService.findById(1)

  // ツイッターアカウントへのアクセス情報
  private val consumerKey = "DRrJp1VWExASN64BVgknupI3T"
  private val consumerSecret = "NgWxrk0cWk1VJVbYV0SQFmVk8ahdzKSqphxfGsHbIdutEBiMgp"
  private val accessToken = "938316768862527488-KcwZafQ7FD8r1eMACFoNyNcztCN5Xuj"
  private val accessTokenSecret = "VvHiFm5kcNodCDgR3BGrYDumF3UH7Rvxsd7sSs1mx77ce"

  // 重複回数
  private var duplicateCount = 0

  fun execute() {
    val twitter = TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret)

    // ツイート処理実行
    // Twitterの重複エラーが発生した場合、11回まで再帰的に処理を繰り返す。
    try {
      twitter.timelineOperations().updateStatus("${tweets[rand.nextInt(tweets.size)]} ${test.name}")
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
    }
  }
}
