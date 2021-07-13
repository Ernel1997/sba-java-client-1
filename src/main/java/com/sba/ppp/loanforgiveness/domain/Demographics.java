package com.sba.ppp.loanforgiveness.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Demographics {
	
	@JsonInclude(Include.NON_NULL)
	private String name;
	
	@JsonInclude(Include.NON_NULL)
	private String position;
	
	@JsonInclude(Include.NON_NULL)
	private String veteran_status;
	
	@JsonInclude(Include.NON_NULL)
	private String gender;
	
	@JsonInclude(Include.NON_NULL)
	private String ethnicity;
	
	private List<Race> races;

}
