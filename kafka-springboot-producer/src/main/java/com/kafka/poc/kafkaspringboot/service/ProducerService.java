package com.kafka.poc.kafkaspringboot.service;

public interface ProducerService {
    public void publishTweet(String topic, String query);
}
