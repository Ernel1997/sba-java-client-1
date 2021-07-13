package com.sba.ppp.loanorigination.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
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
public class Owner {
	private UUID slug;
	private String first_name;
	private String last_name;
	private String title;
	private Integer duns_number;
	private Integer owner_type;
	private BigDecimal ownership_percentage;
	private String tin;
	private String tin_type;
	private String address_line_1;
	private String address_line_2;
	private String city;
	private String state;
	private String zip_code;
	private String zip_code_plus4;
	private String position;
	private String veteran_status;
	private String gender;
	private String ethnicity;
	private String[] race;
	private String business_type;
}
