package bg.dr.chillywe.kafka.producer.config

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@EnableKafka
@Configuration
class KafkaProducerConfig(
    @Value("\${kafka.bootstrapAddress}") private val kafkaBootstrapServer: String,
    @Value("\${kafka.serializer.key}") private val serializerKey: String,
    @Value("\${kafka.serializer.value}") private val serializerValue: String,
    @Value("\${kafka.schema-registry.url}") private val schemaRegistryUrl: String
) {
    @Bean
    fun producerFactory(): ProducerFactory<String, Any> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaBootstrapServer
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = serializerKey
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = serializerValue
        configProps[KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG] = schemaRegistryUrl
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory())
    }
}