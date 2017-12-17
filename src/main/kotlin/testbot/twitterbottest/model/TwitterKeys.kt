package testbot.twitterbottest.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "twitter_keys")
data class TwitterKeys(@Id @GeneratedValue var id: Int?,
                       @Column(nullable = false) var bot_type: String,
                       @Column(nullable = false) var consumer_key: String,
                       @Column(nullable = false) var consumer_secret: String,
                       @Column(nullable = false) var access_token: String,
                       @Column(nullable = false) var access_token_secret: String) {
}
