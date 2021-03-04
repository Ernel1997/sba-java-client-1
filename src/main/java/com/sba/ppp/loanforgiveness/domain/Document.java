package com.sba.ppp.loanforgiveness.domain;

import java.time.ZonedDateTime;
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
public class Document {
	
	 private UUID slug;
	 
     private String name;
     
     private ZonedDateTime created_at;
     
     private ZonedDateTime updated_at;
     
     private String document;
     
     private String url;
     
     private String etran_loan;
     
     private LoanDocumentType document_type;

}
