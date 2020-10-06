package com.kafka.poc.kafkaspringboot.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.kafka.poc.kafkaspringboot.dto.Tweet;

/**
 * Description - This is the configuration class for Kafka.
 * 
 * @author Rakesh Swain
 * @version 0.0.1
 */
@Configuration
public class KafkaProdcuerConfig {
	
	// Server on which kafka is running
	@Value("${bootstrap.server:localhost}")
	private String bootstrapServer;

	// Port on which kafka is listening 
	@Value("${bootstrap.port:9092}")
	private String bootstrapPort;

    /**
	 * This method is responsible for creating and configuring producer factory instance. 
	 * The producer factory instance is then injected into KafkaTemplate.
	 * 
	 * @return ProducerFactory<String, Tweet> - Producer factory instance
	 */
	@Bean
	public ProducerFactory<String, Tweet> getProducerFactoryTweet() {
		Map<String,Object> config = new HashMap<String,Object>();	
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer +":"+ bootstrapPort);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); 
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
	}
	
	/**
	 * This function return an instance of the KafkaTemplate.
	 * 
	 * @return KafkaTemplate<String, Tweet> - KafkaTemplate instance
	 */
	@Bean
	public KafkaTemplate<String, Tweet> kafkaTemplateTweet() {
		return new KafkaTemplate<>(getProducerFactoryTweet());
	}

}
