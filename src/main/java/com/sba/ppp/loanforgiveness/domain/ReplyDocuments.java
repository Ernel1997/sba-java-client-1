package com.sba.ppp.loanforgiveness.domain;

import java.util.List;
import java.util.UUID;

import com.sba.ppp.loanforgiveness.domain.MessageReply.MessageReplyBuilder;

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
