package testbot.twitterbottest.repository

import testbot.twitterbottest.model.TweetLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository
import java.io.Serializable
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

@Repository
interface TweetLogRepository : JpaRepository<TweetLog, Int> {
  fun findById(id: Int): TweetLog
}
