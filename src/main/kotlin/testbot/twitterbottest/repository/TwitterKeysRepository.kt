package testbot.twitterbottest.repository

import testbot.twitterbottest.model.TwitterKeys
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository
import java.io.Serializable
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

@Repository
interface TwitterKeysRepository : JpaRepository<TwitterKeys, Int> {
  fun findById(id: Int): TwitterKeys

  @Query("select tk from TwitterKeys tk where tk.bot_type = :botType")
  fun findByBotType(@Param("botType") botType: String): TwitterKeys
}
