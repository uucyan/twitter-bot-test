package testbot.twitterbottest.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import testbot.twitterbottest.model.Test
import testbot.twitterbottest.repository.TestRepository

@Service
class TestService @Autowired constructor(private val testRepository: TestRepository) {

  fun findAllTest(): MutableList<Test> = testRepository.findAll()

  fun findById(id: Int): Test {
    var test: Test = testRepository.findById(id)
    return test
  }

  fun exists(id: Int) : Boolean {
    return testRepository.exists(id)
  }

  fun save(test: Test) {
    testRepository.save(test)
  }

  fun delete(test: Test) {
    testRepository.delete(test)
  }
}
