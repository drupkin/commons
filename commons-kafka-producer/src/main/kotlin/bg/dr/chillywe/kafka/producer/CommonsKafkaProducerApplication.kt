package bg.dr.chillywe.kafka.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommonsKafkaProducerApplication

fun main(args: Array<String>) {
	runApplication<CommonsKafkaProducerApplication>(*args)
}
