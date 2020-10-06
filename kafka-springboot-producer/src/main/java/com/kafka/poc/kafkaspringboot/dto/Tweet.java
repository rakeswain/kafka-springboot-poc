package com.kafka.poc.kafkaspringboot.dto;

import java.time.ZonedDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
	
	@JsonProperty("created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm:ss Z yyyy")
	private ZonedDateTime createdAt;
	
	@JsonProperty("id_str")
	private String id;
	
	@JsonProperty("text")
	private String text;
	
	private String user;

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
	@JsonProperty("user")
	public void unpackUser(Map<String, Object> user) {
		this.user = (String)user.get("name");
	}
	
}
