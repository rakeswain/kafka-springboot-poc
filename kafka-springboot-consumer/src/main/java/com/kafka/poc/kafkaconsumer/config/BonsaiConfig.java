package com.kafka.poc.kafkaconsumer.config;

import java.net.InetSocketAddress;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.kafka.poc.kafkaconsumer.repository")
public class BonsaiConfig {
		
		@Value("${BONSAI_URL}")
		private String BONSAI_URL;

		@Value("${BONSAI_PORT:9200}")
		private int BONSAI_PORT;
		
		@Value("${HTTP_PROXY}")
		private String HTTP_PROXY;
		
	 	@Bean
	    public RestHighLevelClient client() {


	        ClientConfiguration clientConfiguration 
	            = ClientConfiguration.builder()
	            	.connectedTo(new InetSocketAddress(BONSAI_URL, BONSAI_PORT))
	                .withProxy(HTTP_PROXY)
	                .build();
	 
	        return RestClients.create(clientConfiguration).rest();
	    }
	 
	    @Bean
	    public ElasticsearchOperations elasticsearchTemplate() {
	        return new ElasticsearchRestTemplate(client());
	    }

}
