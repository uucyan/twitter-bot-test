package testbot.twitterbottest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import testbot.twitterbottest.bots.FugaBot
import testbot.twitterbottest.bots.HogeBot
import me.mattak.moment.Moment

@Component
class BatchTasks @Autowired constructor(private val hogeBot: HogeBot,
                                        private val fugaBot: FugaBot) {

  // initialDelay：バッチ起動開始後何秒後に実行するか（ミリ秒指定）
  // fixedDelay：何秒ごとに処理を実行するか（ミリ秒指定）
  @Scheduled(initialDelay = 7000, fixedDelay = 31000)
  fun hogeBot() {
    println("${hogeBot::class.java.name} ツイート処理を開始 ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
    hogeBot.execute()
    println("${hogeBot::class.java.name} ツイート処理を終了 ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
    println()
  }

  /* @Scheduled(initialDelay = 10000, fixedDelay = 60000)
  fun fugaBot() {
    println("Fuga ツイート処理を開始 ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
    fugaBot.execute()
    println("Fuga ツイート処理を終了 ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
    println()
  } */
}
