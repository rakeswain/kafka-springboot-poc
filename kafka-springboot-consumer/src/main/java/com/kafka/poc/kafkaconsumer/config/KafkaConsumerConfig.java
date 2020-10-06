package com.kafka.poc.kafkaconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.poc.kafkaconsumer.dto.Tweet;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	
	@Bean 
	public ConsumerFactory<String, String> getConsumerFactory() {
		
		Map<String, Object> config = new HashMap<String, Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "test_group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config);
		
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		
		ConcurrentKafkaListenerContainerFactory<String, String> factory = 
									new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(getConsumerFactory());
		return factory;
		
	}
	
	@Bean 
	public ConsumerFactory<String, Tweet> getTweetConsumerFactory() {
		
		JsonDeserializer<Tweet> deserializer = new JsonDeserializer<>(Tweet.class);
	    deserializer.setRemoveTypeHeaders(false);
	    deserializer.addTrustedPackages("*");
	    deserializer.setUseTypeMapperForKey(true);

		Map<String, Object> config = new HashMap<String, Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "tweet_group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
														deserializer);
		
	}
	

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Tweet> kafkaListenerContainerFactoryForTweets() {
		
		ConcurrentKafkaListenerContainerFactory<String, Tweet> factory = 
									new ConcurrentKafkaListenerContainerFactory<String, Tweet>();
		factory.setConsumerFactory(getTweetConsumerFactory());
		return factory;
		
	}
	

}
