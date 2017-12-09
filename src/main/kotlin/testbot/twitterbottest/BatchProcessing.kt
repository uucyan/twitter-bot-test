package testbot.twitterbottest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import testbot.twitterbottest.bots.TestBot

@Component
class BatchProcessing @Autowired constructor(private val testBot: TestBot) {

  @Scheduled(initialDelay = 10000, fixedDelay = 10000)
  fun fixedRate() {
    testBot.execute()
  }
}
