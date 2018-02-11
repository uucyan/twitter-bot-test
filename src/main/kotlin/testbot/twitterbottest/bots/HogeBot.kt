package testbot.twitterbottest.bots

import org.springframework.stereotype.Component
import org.springframework.social.DuplicateStatusException
import org.springframework.core.env.Environment
import testbot.twitterbottest.service.TwitterApplicationService
import testbot.twitterbottest.service.TweetLogService
import testbot.twitterbottest.bots.AbstractBot
import me.mattak.moment.Moment

@Component
class HogeBot(twitterApplicationService: TwitterApplicationService,
              tweetLogService: TweetLogService,
              environment: Environment): AbstractBot(twitterApplicationService, tweetLogService, environment) {

  override val botType = "prod"

  fun execute() {
    setTwitter(botType)
    if (!isExistTwitter()) return
    sendTweet("Hogeのツイート ${Moment().format("yyyy/MM/dd HH:mm:ss")}")
  }
}
