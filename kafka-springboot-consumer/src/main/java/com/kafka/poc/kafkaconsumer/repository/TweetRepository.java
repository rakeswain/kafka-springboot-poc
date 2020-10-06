package com.kafka.poc.kafkaconsumer.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.kafka.poc.kafkaconsumer.dto.Tweet;

@Repository
public interface TweetRepository extends ElasticsearchRepository<Tweet, String>{

}
