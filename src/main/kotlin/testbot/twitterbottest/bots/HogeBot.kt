package testbot.twitterbottest.bots

import org.springframework.stereotype.Component
import org.springframework.core.env.Environment
import testbot.twitterbottest.service.TwitterApplicationService
import testbot.twitterbottest.service.TweetLogService
import testbot.twitterbottest.bots.AbstractBot
import me.mattak.moment.Moment
import java.util.Random

@Component
class HogeBot(twitterApplicationService: TwitterApplicationService,
              tweetLogService: TweetLogService,
              environment: Environment): AbstractBot(twitterApplicationService, tweetLogService, environment) {

  override val botType = "prod"

  fun execute() {
    setTwitter()
    if (!isExistTwitter()) return
    sendTweet(generateTweet())
  }

  /**
   * ツイート内容を生成
   * 機械的な感じだと凍結されるから人間っぽい内容で誤魔化す。
   */
  private fun generateTweet(): String {
    val rand = Random()
    val tweets = arrayOf(
      "おはようございます。今日は気持ちの良い朝ですね。",
      "こんにちは。お昼ごはんを食べすぎてしまいました。",
      "こんばんは。お星様がとても綺麗ですね。",
      "僕の名前はHogeといいます。",
      "君のことはなんて呼べば良いでしょうか？",
      "ねむたいです。",
      "課金のしすぎには注意ですよ？",
      "突然ですが、お金を下さい。",
      "明日こそ目覚めませんように…。",
      "もうこれくらいのパターンがあればいいかな？",
      "いいよね",
      "うん、いいと思う。"
    )

    return tweets[rand.nextInt(tweets.size)]
  }
}
