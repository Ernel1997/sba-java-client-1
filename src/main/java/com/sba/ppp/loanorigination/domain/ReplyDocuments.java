package com.sba.ppp.loanorigination.domain;

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
public class ReplyDocuments {
	private Integer document_type;
	
    private String document_name;
    
    private String filePathToUpload;

}
