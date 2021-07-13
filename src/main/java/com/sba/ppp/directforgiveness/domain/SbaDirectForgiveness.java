package com.sba.ppp.directforgiveness.domain;

import java.math.BigDecimal;
import java.util.List;
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
public class SbaDirectForgiveness {
	
	@JsonInclude(Include.NON_NULL)
	private UUID slug;

	@JsonInclude(Include.NON_NULL)
	private String entity_name;
	
	@JsonInclude(Include.NON_NULL)
	private String address1;

	@JsonInclude(Include.NON_NULL)
	private String address2;

	@JsonInclude(Include.NON_NULL)
	private String dba_name;

	@JsonInclude(Include.NON_NULL)
	private String ein;

	@JsonInclude(Include.NON_NULL)
	private String naics_code;

	@JsonInclude(Include.NON_NULL)
	private String primary_name;

	@JsonInclude(Include.NON_NULL)
	private String primary_email;

	@JsonInclude(Include.NON_NULL)
	private String phone_number;

	@JsonInclude(Include.NON_NULL)
	private Integer ppp_loan_draw;

	@JsonInclude(Include.NON_NULL)
	private String sba_number;

	@JsonInclude(Include.NON_NULL)
	private String loan_number;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal bank_notional_amount;

	@JsonInclude(Include.NON_NULL)
	private String funding_date;

	@JsonInclude(Include.NON_NULL)
	private String forgive_covered_period_from;

	@JsonInclude(Include.NON_NULL)
	private String forgive_covered_period_to;

	@JsonInclude(Include.NON_NULL)
	private Integer forgive_fte_at_loan_application;

	@JsonInclude(Include.NON_NULL)
	private Integer forgive_fte_at_forgiveness_application;

	@JsonInclude(Include.NON_NULL)
	private Boolean forgive_2_million;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal forgive_payroll;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal forgive_amount;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal loan_increase;

	@JsonInclude(Include.NON_NULL)
	private String loan_increase_date;

	@JsonInclude(Include.NON_NULL)
	private List<Demographics> demographics;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal outstanding_balance;

	@JsonInclude(Include.NON_NULL)
	private BigDecimal undisbursed_balance;

	@JsonInclude(Include.NON_NULL)
	private String disbursement_date;

	@JsonInclude(Include.NON_NULL)
	private String covid_score;

	@JsonInclude(Include.NON_NULL)
	private Boolean covid_flag;

	@JsonInclude(Include.NON_NULL)
	private Integer tin_type;

	@JsonInclude(Include.NON_NULL)
	private String status;
	
}

