package testbot.twitterbottest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import testbot.twitterbottest.bots.TestBot
import me.mattak.moment.Moment

@Component
class BatchProcessing @Autowired constructor(private val testBot: TestBot) {

  // initialDelay：バッチ起動開始後何秒後に実行するか（ミリ秒指定）
  // fixedDelay：何秒ごとに処理を実行するか（ミリ秒指定）
  @Scheduled(initialDelay = 10000, fixedDelay = 60000)
  fun fixedRate() {
    println("ツイート処理を開始 ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
    testBot.execute()
    println("ツイートを終了 ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
    println()
  }
}
