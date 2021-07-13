package com.sba.ppp.loanorigination.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Components {

	 private String urbanization;
	 private String primary_number;
	 private String street_name;
	 private String street_predirection;
	 private String street_postdirection;
	 private String street_suffix;
	 private String secondary_number;
	 private String secondary_designator;
	 private String extra_secondary_number;
	 private String extra_secondary_designator;
	 private String pmb_designator;
	 private String pmb_number;
	 private String city_name;
	 private String default_city_name;
	 private String state_abbreviation;
	 private String zipcode;
	 private String plus4_code;
	 private String delivery_point;
	 private String delivery_point_check_digit;


}