package testbot.twitterbottest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class TwitterBotTestApplication

fun main(args: Array<String>) {
    SpringApplication.run(TwitterBotTestApplication::class.java, *args)
}
