package testbot.twitterbottest.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn
import testbot.twitterbottest.model.TwitterApplication

@Entity
@Table(name = "tweet_log")
data class TweetLog(@Id @GeneratedValue var id: Int?,
                    @Column(nullable = false) var twitter_application_id: Int,
                    @Column(nullable = false) var tweet: String,
                    @Column(nullable = false) var is_duplicate_error: Boolean) {

  @ManyToOne
  @JoinColumn(nullable = false, insertable = false, updatable = false, name = "twitter_application_id")
  private val twitterApplication: TwitterApplication? = null
}
