package com.sba.ppp.loanforgiveness.domain;

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
public class LoanDocumentType {

	private Integer id;
	
    private String name;
    
    private String description;
}
