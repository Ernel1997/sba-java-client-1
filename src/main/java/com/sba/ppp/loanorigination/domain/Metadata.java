package com.sba.ppp.loanorigination.domain;

import java.util.List;

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
public class Metadata {

	 private String record_type;
	 private String zip_type;
	 private String county_fips;
	 private String county_name;
	 private String carrier_route;
	 private String congressional_district;
	 private String building_default_indicator;
	 private String rdi;
	 private String elot_sequence;
	 private String elot_sort;
	 private String latitude;
	 private String longitude;
	 private String precision;
	 private String time_zone;
	 private String utc_offset;
	 private String obeys_dst;
	 private String is_ews_match;


}