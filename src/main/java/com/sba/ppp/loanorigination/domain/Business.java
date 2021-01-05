package com.sba.ppp.loanorigination.domain;

import java.util.List;
import java.util.UUID;

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
public class Business {
	
	 private List<Owner> owners;
	 
	 private Integer naics_code;

	 private String business_type;

	 private String tin_type;

	 private String tin;

	 private String franchise_code;

	 private UUID slug;

	 private String dba_tradename;

	 private Integer duns_number;

	 private String legal_name;

	 private String first_name;

	 private String last_name;

	 private String address_line_1;

	 private String address_line_2;

	 private String city;

	 private String state;

	 private String zip_code;

	 private String zip_code_plus4;

	 private String phone_number;

	 private String primary_contact;

	 private String primary_contact_email;

	 private Boolean is_franchise;

	 private String date_of_establishment;
	 
}
