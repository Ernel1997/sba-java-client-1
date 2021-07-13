package com.sba.ppp.loanforgiveness.domain;

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
public class SbaPPPLoanMessagesResponse {

	 private Integer count;
	 
	 private String next;
	 
	 private String previous;
	 
	 private List<SbaPPPLoanForgivenessMessage> results;

}
