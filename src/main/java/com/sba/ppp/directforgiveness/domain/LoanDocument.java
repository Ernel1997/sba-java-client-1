package com.sba.ppp.directforgiveness.domain;

import java.time.ZonedDateTime;
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
public class LoanDocument {

	@JsonInclude(Include.NON_NULL)
	private UUID slug;

	@JsonInclude(Include.NON_NULL)
	private String name;

	@JsonInclude(Include.NON_NULL)
	private ZonedDateTime created_at;

	@JsonInclude(Include.NON_NULL)
	private ZonedDateTime updated_at;

	@JsonInclude(Include.NON_NULL)
	private String document;

	@JsonInclude(Include.NON_NULL)
	private String url;

	@JsonInclude(Include.NON_NULL)
	private UUID etran_loan;

	@JsonInclude(Include.NON_NULL)
	private Integer document_type;

	@JsonInclude(Include.NON_NULL)
	private String filePathToUpload;

}
