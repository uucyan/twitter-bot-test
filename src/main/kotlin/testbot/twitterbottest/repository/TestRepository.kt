package testbot.twitterbottest.repository

import testbot.twitterbottest.model.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.io.Serializable
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

@Repository
interface TestRepository : JpaRepository<Test, Int> {
  fun findById(id:Int): Test
}
