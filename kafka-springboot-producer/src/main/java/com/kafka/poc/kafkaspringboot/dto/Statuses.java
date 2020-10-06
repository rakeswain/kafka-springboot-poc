package com.kafka.poc.kafkaspringboot.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statuses {
	
	@JsonProperty("statuses")
	private List<Tweet> statuses;

	public List<Tweet> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Tweet> statuses) {
		this.statuses = statuses;
	}	

}
