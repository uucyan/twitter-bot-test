package testbot.twitterbottest.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import testbot.twitterbottest.model.TweetLog
import testbot.twitterbottest.repository.TweetLogRepository

@Service
class TweetLogService @Autowired constructor(private val tweetLogRepository: TweetLogRepository) {

  fun findAllTweetLog(): MutableList<TweetLog> = tweetLogRepository.findAll()

  fun findById(id: Int): TweetLog {
    val tweetLog: TweetLog = tweetLogRepository.findById(id)
    return tweetLog
  }

  fun exists(id: Int) : Boolean {
    return tweetLogRepository.exists(id)
  }

  fun save(tweetLog: TweetLog) {
    tweetLogRepository.save(tweetLog)
  }

  fun delete(tweetLog: TweetLog) {
    tweetLogRepository.delete(tweetLog)
  }
}
