package testbot.twitterbottest.bots

import org.springframework.stereotype.Component
import org.springframework.social.DuplicateStatusException
import org.springframework.core.env.Environment
import testbot.twitterbottest.service.TwitterApplicationService
import testbot.twitterbottest.service.TweetLogService
import testbot.twitterbottest.bots.AbstractBot

@Component
class HogeBot(twitterApplicationService: TwitterApplicationService,
              tweetLogService: TweetLogService,
              environment: Environment): AbstractBot(twitterApplicationService, tweetLogService, environment) {

  override val botType = "test"

  fun execute() {
    if (!isExistTwitter()) return
    sendTweet("Hogeのツイート内容")
  }
}
