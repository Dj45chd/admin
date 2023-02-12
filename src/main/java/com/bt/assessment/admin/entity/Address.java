package com.bt.assessment.admin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

	@JsonProperty("street")
	private String street;

	@JsonProperty("suite")
	private String suite;

	@JsonProperty("city")
	private String city;

	@JsonProperty("zipcode")
	private String zipcode;

	@JsonProperty("geo")
	private Geo geo;

}
