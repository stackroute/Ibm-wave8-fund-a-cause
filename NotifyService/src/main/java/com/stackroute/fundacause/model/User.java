package com.stackroute.fundacause.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.stereotype.Component;

import java.util.Map;

@JsonIgnoreProperties
@Component
public class User {

	@JsonProperty("name")
	private String name;

	@JsonProperty("cause")
	private String cause;

	@JsonProperty("email")
	private String email;

	@JsonProperty("amount")
	private Long amount;

	private Map<String, Object> model;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", cause='" + cause + '\'' +
				", emailAddress='" + email + '\'' +
				", amount=" + amount +
				'}';
	}


}
