package com.bt.assessment.admin.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {

	@JsonProperty("name")
	private String name;

	@JsonProperty("catchPhrase")
	private String catchPhrase;

	@JsonProperty("bs")
	private String bs;

}
