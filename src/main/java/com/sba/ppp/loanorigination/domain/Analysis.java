package com.sba.ppp.loanorigination.domain;

import java.util.List;

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
public class Analysis {

	 private String dpv_match_code;
	 private String dpv_footnotes;
	 private String cmra;
	 private String vacant;
	 private String active;
	 private String footnotes;
	 private String lacs_link_code;
	 private String lacs_link_indicator;
	 private String is_suite_link_match;

}
