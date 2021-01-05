package com.sba.ppp.loanorigination.domain;

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
public class LoanRequest {
	
	private UUID slug;
	
	private Business business;
	
	private Organization organization;
	
	private String status;

	private BigDecimal average_monthly_payroll;

	private BigDecimal loan_amount;

	private Integer number_of_employees;

	private BigDecimal period_1_revenue;

	private BigDecimal period_2_revenue;

	private Boolean purpose_of_loan_payroll;

	private Boolean purpose_of_loan_mortgage;

	private Boolean purpose_of_loan_utilities;

	private Boolean purpose_of_loan_covered_operations_expenditures;

	private Boolean purpose_of_loan_covered_property_damage;

	private Boolean purpose_of_loan_covered_supplier_costs;

	private Boolean purpose_of_loan_covered_worker_protection_expenditure;

	private Boolean purpose_of_loan_other;

	private String purpose_of_loan_other_info;

	private Boolean ineligible_general;

	private Boolean ineligible_bad_loan;

	private Boolean ineligible_criminal_charges;

	private Boolean ineligible_felony;

	private Boolean has_other_businesses;

	private Boolean received_eidl;

	private Boolean all_employees_residency;

	private Long sba_application_id;

	private Long ppp_first_draw_sba_loan_number;

	private BigDecimal ppp_first_draw_loan_amount;

	private Long sba_number;

	private String validation_errors;

	private Boolean second_draw_ppp_loan;

	private Integer applicant_meets_size_standard;

	private Integer number_of_employees_at_time_of_application;

	private Integer anticipated_number_of_employees_retained;
}
