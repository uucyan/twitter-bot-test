package testbot.twitterbottest.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "twitter_application")
data class TwitterApplication(@Id @GeneratedValue var id: Int?,
                       @Column(nullable = true) var bot_type: String,
                       @Column(nullable = true) var twitter_id: String,
                       @Column(nullable = true) var twitter_url: String,
                       @Column(nullable = true) var twitter_name: String,
                       @Column(nullable = false) var consumer_key: String,
                       @Column(nullable = false) var consumer_secret: String,
                       @Column(nullable = false) var access_token: String,
                       @Column(nullable = false) var access_token_secret: String,
                       @Column(nullable = false) var is_execute: Boolean) {
}
