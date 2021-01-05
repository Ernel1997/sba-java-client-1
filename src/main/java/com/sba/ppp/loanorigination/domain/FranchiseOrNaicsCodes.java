package com.sba.ppp.loanorigination.domain;

import java.util.List;

import com.sba.ppp.loanorigination.domain.SbaPPPLoanMessagesResponse.SbaPPPLoanMessagesResponseBuilder;

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
public class FranchiseOrNaicsCodes {
	private String id;
	
	private String code;
	
	private String description;
}
