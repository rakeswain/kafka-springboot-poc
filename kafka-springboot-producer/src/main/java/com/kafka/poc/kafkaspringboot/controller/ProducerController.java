package com.kafka.poc.kafkaspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.poc.kafkaspringboot.service.KafkaProducerService;
import com.kafka.poc.kafkaspringboot.service.ProducerService;

@RestController
@RequestMapping("/producer")
public class ProducerController {

	@Autowired
	ProducerService producer;

	private static final String TWEET_TOPIC = "kafkapoc_tweet";
	
	@GetMapping("/tweets")
	public ResponseEntity<String> publisTweet(@RequestParam String query) {
		producer.publishTweet(TWEET_TOPIC, query);
		return ResponseEntity.ok("Tweets published");
	}
	
}
