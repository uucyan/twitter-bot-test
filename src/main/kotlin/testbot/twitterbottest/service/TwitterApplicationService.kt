package testbot.twitterbottest.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import testbot.twitterbottest.model.TwitterApplication
import testbot.twitterbottest.repository.TwitterApplicationRepository

@Service
class TwitterApplicationService @Autowired constructor(private val twitterApplicationRepository: TwitterApplicationRepository) {

  fun findAllTwitterApplication(): MutableList<TwitterApplication> = twitterApplicationRepository.findAll()

  fun findById(id: Int): TwitterApplication {
    val twitterApplication: TwitterApplication = twitterApplicationRepository.findById(id)
    return twitterApplication
  }

  fun findByBotType(botType: String): TwitterApplication {
    return twitterApplicationRepository.findByBotType(botType)
  }

  fun exists(id: Int) : Boolean {
    return twitterApplicationRepository.exists(id)
  }

  fun save(twitterApplication: TwitterApplication) {
    twitterApplicationRepository.save(twitterApplication)
  }

  fun delete(twitterApplication: TwitterApplication) {
    twitterApplicationRepository.delete(twitterApplication)
  }
}
