package testbot.twitterbottest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import testbot.twitterbottest.bots.TestBot
import me.mattak.moment.Moment

@Component
class BatchProcessing @Autowired constructor(private val testBot: TestBot) {

  // バッチ起動10秒後に処理を開始し、10間隔で処理を再実行。
  @Scheduled(initialDelay = 10000, fixedDelay = 10000)
  fun fixedRate() {
    println("ツイート処理を開始 ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
    testBot.execute()
    println("ツイートを終了 ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
    println()
  }
}
