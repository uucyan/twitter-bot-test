package testbot.twitterbottest.repository

import testbot.twitterbottest.model.TwitterApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository
import java.io.Serializable
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

@Repository
interface TwitterApplicationRepository : JpaRepository<TwitterApplication, Int> {
  fun findById(id: Int): TwitterApplication

  @Query("select tk from TwitterApplication tk where tk.bot_type = :botType")
  fun findByBotType(@Param("botType") botType: String): TwitterApplication
}
