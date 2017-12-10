package testbot.twitterbottest.bots

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
/*import org.springframework.social.twitter.api.**/
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import testbot.twitterbottest.model.Test
import testbot.twitterbottest.service.TestService

@Component
class TestBot @Autowired constructor(private val testService: TestService) {

  fun execute() {
    val test: Test = testService.findById(1)
    System.out.println("Hello, World! ${test.name}!!")

    val consumerKey = "DRrJp1VWExASN64BVgknupI3T"
    val consumerSecret = "NgWxrk0cWk1VJVbYV0SQFmVk8ahdzKSqphxfGsHbIdutEBiMgp"
    val accessToken = "938316768862527488-KcwZafQ7FD8r1eMACFoNyNcztCN5Xuj"
    val accessTokenSecret = "VvHiFm5kcNodCDgR3BGrYDumF3UH7Rvxsd7sSs1mx77ce"
    val twitter = TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret)
    twitter.timelineOperations().updateStatus("Hello,Twitter! ${test.name}!!")
  }
}
