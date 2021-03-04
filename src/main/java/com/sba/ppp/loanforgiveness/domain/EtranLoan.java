package com.sba.ppp.loanforgiveness.domain;

import java.math.BigDecimal;
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
public class EtranLoan {
	
	private UUID slug;
	
	private BigDecimal bank_notional_amount;
	
	private String sba_number;
	
	private String loan_number;
	
	private String entity_name;
	
	private String ein;
	
	// format - yyyy-MM-dd
	private String funding_date;
	
	@Deprecated
	private BigDecimal forgive_eidl_amount;
	
	@Deprecated
	private Long forgive_eidl_application_number;
	
	private BigDecimal forgive_payroll;
	
	private BigDecimal forgive_rent;
	
	private BigDecimal forgive_utilities;
	
	private BigDecimal forgive_mortgage;
	
	private String address1;
	
	private String address2;
	
	private String dba_name;
	
	private String phone_number;

	private Integer forgive_fte_at_loan_application;
	
	private Organization organization;
	
	private List<Demographics> demographics;
	
	private String status;
	
	private List<Document> documents;

	private BigDecimal forgive_line_6_3508_or_line_5_3508ez;
	
	private BigDecimal forgive_modified_total;
	
	private BigDecimal forgive_payroll_cost_60_percent_requirement;
	
	private BigDecimal forgive_amount;
	
	private Integer forgive_fte_at_forgiveness_application;
	
	private BigDecimal forgive_schedule_a_line_1;

	private BigDecimal forgive_schedule_a_line_2;
	
	private boolean forgive_schedule_a_line_3_checkbox;
	
	private BigDecimal forgive_schedule_a_line_3;
	
	private BigDecimal forgive_schedule_a_line_4;
	
	private BigDecimal forgive_schedule_a_line_5;
	
	private BigDecimal forgive_schedule_a_line_6;
	
	private BigDecimal forgive_schedule_a_line_7;
	
	private BigDecimal forgive_schedule_a_line_8;
	
	private BigDecimal forgive_schedule_a_line_9;
	
	private BigDecimal forgive_schedule_a_line_10;
	
	private boolean forgive_schedule_a_line_10_checkbox;
	
	private BigDecimal forgive_schedule_a_line_11;
	
	private BigDecimal forgive_schedule_a_line_12;
	
	private BigDecimal forgive_schedule_a_line_13;
	
	private String forgive_covered_period_from;
	
	private String forgive_covered_period_to;
	
	@Deprecated
	private String forgive_alternate_covered_period_from;
	
	@Deprecated
	private String forgive_alternate_covered_period_to;
	
	private boolean forgive_2_million;
	
	@Deprecated
	private String forgive_payroll_schedule;
	
	private String primary_email;
	
	private String primary_name;
	
	private boolean ez_form;
	
	private boolean no_reduction_in_employees;
	
	private boolean no_reduction_in_employees_and_covid_impact;
	
	private boolean forgive_lender_confirmation;
	
	private Integer forgive_lender_decision;
	
	private String sba_decision;
	
	private String approval_date;
	
	private String final_forgive_amount;
	
	private String calculated_interest;
	
	private String final_forgive_payment;
	
	private String final_forgive_payment_date;
	
	private String final_forgive_payment_batch;
	
	private BigDecimal final_forgive_amount_with_interest;
	
	private boolean s_form;
	
	private BigDecimal forgive_covered_operations_expenditures;
	
	private BigDecimal forgive_covered_property_damage_costs;
	
	private BigDecimal forgive_covered_supplier_costs;
	
	private BigDecimal forgive_covered_protection_expenditures;
	
	private Integer naics_code;
	
	private Integer ppp_loan_draw;

}
