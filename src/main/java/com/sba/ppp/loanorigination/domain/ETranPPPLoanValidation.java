package com.sba.ppp.loanorigination.domain;

import java.util.List;

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
public class ETranPPPLoanValidation {

		private String sba_number;
	 private Number etran_notional_amount;
	 private String etran_busnm;
	 private String address1;
	 private String address2;


}