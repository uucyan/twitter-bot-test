package testbot.twitterbottest.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "test")
data class Test(@Id @GeneratedValue var id: Int?,
                @Column(nullable = false) var name: String) {
}
