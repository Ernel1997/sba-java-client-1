package com.sba.ppp.loanorigination.domain;

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
public class MessageReply {
	
	private UUID slug;

	private List<ReplyDocuments> replyDocuments;

	private String content;
}
