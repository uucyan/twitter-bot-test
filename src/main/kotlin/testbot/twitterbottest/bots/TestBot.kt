package testbot.twitterbottest.bots

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import testbot.twitterbottest.model.Test
import testbot.twitterbottest.service.TestService

@Component
class TestBot @Autowired constructor(private val testService: TestService) {

  fun execute() {
    val test: Test = testService.findById(1)
    System.out.println("Hello, World! ${test.name}!!")
  }
}
