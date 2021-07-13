package com.sba.ppp.directforgiveness.domain;

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
public class SbaDirectForgivenessEligibility {
	private Boolean eligible;
	
	private SbaDirectForgiveness etran_loan;
	
	private Organization organization;
	
	private String ineligible_reason;
}
