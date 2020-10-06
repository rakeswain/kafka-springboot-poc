package com.kafka.poc.kafkaspringboot.service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.kafka.poc.kafkaspringboot.dto.Statuses;
import com.kafka.poc.kafkaspringboot.dto.Tweet;

@Service
public class KafkaProducerService implements ProducerService {
	
	private static Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
	
	@Autowired
	KafkaTemplate<String, Tweet> kafkaTemplateTweet;
	
	@Value("${twitter.bearer.token}")
	private String BEARER_TOKEN;
	
	@Value("${HTTP_PROXY:http://localhost}")
	private String HTTP_PROXY;
	
	@Value("${HTTP_PORT:8080}")
	private int HTTP_PORT;
	
	@Value("${proxy.enabled:false}")
	private boolean proxyEnabled;
	
	
	private RestTemplate getRestTemplateWithProxy() {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HTTP_PROXY, HTTP_PORT));
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setProxy(proxy);
		return new RestTemplate(requestFactory);
	}
	
	private RestTemplate getRestTemplateWithoutProxy() {
		return new RestTemplate();
	}

	public void publishTweet(String topic, String query) {
		RestTemplate restTemplate = proxyEnabled ? getRestTemplateWithProxy()
												 : getRestTemplateWithoutProxy();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+BEARER_TOKEN);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Statuses> response = restTemplate.exchange(getTweetUrl(query), HttpMethod.GET, entity, Statuses.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			logger.info("Twitter API connecte with rsponse code "+response.getStatusCode()); 
			for(Tweet tweet : response.getBody().getStatuses()) {
				kafkaTemplateTweet.send(topic, tweet);
			}
		} else { 
			logger.error("Connection to Twitter API failed with response code "+response.getStatusCode());
		}		
	}
	 
	private String getTweetUrl(String query) {
		String url = "https://api.twitter.com/1.1/search/tweets.json?q=+"+query+"+&result_type=recent&lang=en";
		return url;
	}
}
