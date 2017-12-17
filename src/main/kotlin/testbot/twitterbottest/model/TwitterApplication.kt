package testbot.twitterbottest.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.OneToMany
import javax.persistence.JoinColumn
import javax.persistence.FetchType
import testbot.twitterbottest.model.TweetLog

@Entity
@Table(name = "twitter_application")
data class TwitterApplication(@Id @GeneratedValue var id: Int,
                              @Column(nullable = true) var bot_type: String,
                              @Column(nullable = true) var twitter_id: String,
                              @Column(nullable = true) var twitter_url: String,
                              @Column(nullable = true) var twitter_name: String,
                              @Column(nullable = false) var consumer_key: String,
                              @Column(nullable = false) var consumer_secret: String,
                              @Column(nullable = false) var access_token: String,
                              @Column(nullable = false) var access_token_secret: String,
                              @Column(nullable = false) var is_execute: Boolean) {

  // cascade = なし, fetch = FetchType.LAZY(初回select時にtweetLogは取得しない)
  @OneToMany(mappedBy = "twitterApplication", fetch = FetchType.EAGER)
  /* @JoinColumn(name = "id") */
  private val tweetLog: List<TweetLog>? = null

  fun getTweetLog(): List<TweetLog>? {
    return tweetLog
  }
}
