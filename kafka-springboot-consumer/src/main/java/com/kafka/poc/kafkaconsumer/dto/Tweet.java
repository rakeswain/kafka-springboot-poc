package com.kafka.poc.kafkaconsumer.dto;

import java.time.ZonedDateTime;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "tweets",type="tweets",shards=1)
public class Tweet {
	
	@JsonProperty("created_at")
	
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "EEE MMM dd HH:mm:ss Z yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm:ss Z yyyy")
	private ZonedDateTime createdAt;
	
	@JsonProperty("id_str")
	private String id;
	
	@JsonProperty("text")
	private String text;
	
	private String user;
	
	public Tweet() {
		
	}
	
	public Tweet(ZonedDateTime createdAt, String id, String text, String user) {
		super();
		this.createdAt = createdAt;
		this.id = id;
		this.text = text;
		this.user = user;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
