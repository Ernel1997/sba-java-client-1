package com.sba.ppp.loanorigination.domain;

import java.util.UUID;

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
public class PPPLoanOrigination {
	
	private UUID slug;
	
	@JsonInclude(Include.NON_NULL)
	private String borrower_name;
	
	private LoanRequest etran_loan;
	
	@JsonInclude(Include.NON_NULL)
	private String created;
	
}

