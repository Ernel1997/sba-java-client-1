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
public class GetValidatedAndStandardizedAddressResponse {

	 private String input_id;
	 
	 private Integer input_index;
	 
	 private Integer candidate_index;
	 
	 private String addressee;
	 
	 private String delivery_line_1;
	 
	 private String delivery_line_2;
	 
	 private String last_line;
	 
	 private String delivery_point_barcode;
	 
	 private Components components;
	 
	 private Metadata metadata;
	 
	 private Analysis analysis;
	 

}
