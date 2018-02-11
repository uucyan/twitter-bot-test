package testbot.twitterbottest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.core.env.Environment
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.HttpClientErrorException
import org.springframework.http.HttpMethod;
import testbot.twitterbottest.bots.FugaBot
import testbot.twitterbottest.bots.HogeBot
import me.mattak.moment.Moment

@Component
class BatchTasks @Autowired constructor(private val hogeBot: HogeBot,
                                        private val fugaBot: FugaBot,
                                        private val environment: Environment) {

  companion object {
    private const val DEV_ENV = "dev"
    private const val HEROKU_APP_URL = "https://twitter-bot-prod-test.herokuapp.com/"
  }

  // initialDelay：バッチ起動開始後何秒後に実行するか（ミリ秒指定）
  // fixedDelay：何秒ごとに処理を実行するか（ミリ秒指定）
  @Scheduled(initialDelay = 7000, fixedDelay = 300000)
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

  /**
   * 20分毎にアプリケーションのURLへGETアクセスする
   *
   * 無料版のHerokuだと、Dynoに30分間アクセスが無いとアイドリング状態になってしまうため、
   * それを回避するための策略。
   */
  @Scheduled(initialDelay = 1200000, fixedDelay = 1200000)
  fun connectionHerokuApp() {
    // 開発環境の場合は処理を実行しない
    if (environment.getProperty("spring.config.name") == DEV_ENV) return

    val restTemplate = RestTemplate()
    try {
      restTemplate.exchange(HEROKU_APP_URL, HttpMethod.GET, null, String::class.java)
    } catch (e: HttpClientErrorException) {
      // 現状ページの実装はしていないため必ず404で例外発生
      println("定期アクセス ステータスコード:${e.getStatusCode()}")
    }
  }
}
