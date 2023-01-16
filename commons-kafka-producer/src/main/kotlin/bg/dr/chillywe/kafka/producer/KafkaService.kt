package bg.dr.chillywe.kafka.producer

import org.apache.avro.specific.SpecificRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

interface KafkaService {
    fun sendMessage(topic: String, record: SpecificRecord)
}

@Service
class KafkaServiceImpl(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) : KafkaService {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun sendMessage(topic: String, record: SpecificRecord) {
        log.info("Sending message to Kafka {}", record)

        kafkaTemplate.send(topic, record)
        log.info("Message sent with success")
    }

}