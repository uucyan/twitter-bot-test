package testbot.twitterbottest.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import testbot.twitterbottest.model.TwitterKeys
import testbot.twitterbottest.repository.TwitterKeysRepository

@Service
class TwitterKeysService @Autowired constructor(private val twitterKeysRepository: TwitterKeysRepository) {

  fun findAllTwitterKeys(): MutableList<TwitterKeys> = twitterKeysRepository.findAll()

  fun findById(id: Int): TwitterKeys {
    val twitterKeys: TwitterKeys = twitterKeysRepository.findById(id)
    return twitterKeys
  }

  fun findByBotType(botType: String): TwitterKeys {
    return twitterKeysRepository.findByBotType(botType)
  }

  fun exists(id: Int) : Boolean {
    return twitterKeysRepository.exists(id)
  }

  fun save(twitterKeys: TwitterKeys) {
    twitterKeysRepository.save(twitterKeys)
  }

  fun delete(twitterKeys: TwitterKeys) {
    twitterKeysRepository.delete(twitterKeys)
  }
}
