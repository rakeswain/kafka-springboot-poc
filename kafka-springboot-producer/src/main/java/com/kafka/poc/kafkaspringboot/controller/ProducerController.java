package com.kafka.poc.kafkaspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.poc.kafkaspringboot.service.KafkaProducerService;

@RestController
@RequestMapping("/producer")
public class ProducerController {

	@Autowired
	KafkaProducerService producerService;
	
	// private static final String TOPIC = "kafkapoc";
	private static final String TWEET_TOPIC = "kafkapoc_tweet";
	
//	@GetMapping("/test/{message}")
//	public String publishTestMessage(@PathVariable String message) {
//		producerService.publishMessage(TOPIC, message);
//		return "Test message sent";
//	}
	
	@GetMapping("/tweets")
	public String publisTweet(@RequestParam String query) {
		producerService.publishTweet(TWEET_TOPIC, query);
		return "Tweet published";
	}
	
}
