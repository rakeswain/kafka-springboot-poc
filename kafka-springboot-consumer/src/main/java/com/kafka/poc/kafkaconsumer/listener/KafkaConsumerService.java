package com.kafka.poc.kafkaconsumer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.poc.kafkaconsumer.dto.Tweet;
import com.kafka.poc.kafkaconsumer.repository.TweetRepository;

@Service
public class KafkaConsumerService {
	
	@Autowired
	ElasticsearchOperations operations;
	
	@Autowired
	TweetRepository tweetRepository;
	
	@KafkaListener(topics = "kafkapoc", groupId = "test_group")
	public void consumerTest(String message) {
		System.out.println("Consumed : "+message);
	}
	

	@KafkaListener(topics = "kafkapoc_tweet", groupId = "tweet_group",
	 containerFactory = "kafkaListenerContainerFactoryForTweets")
	public void consumerTweet(Tweet tweet) {
		System.out.println("========== "+tweet.getUser()+" tweeted ==========");
		System.out.println(tweet.getText());
		tweetRepository.save(tweet);
	}
	

}
