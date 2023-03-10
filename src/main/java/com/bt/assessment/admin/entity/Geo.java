package com.bt.assessment.admin.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Geo {

	@JsonProperty("lat")
	private float lat;

	@JsonProperty("lng")
	private float lng;

}
