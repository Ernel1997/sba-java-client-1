package com.sba.ppp.loanforgiveness.domain;

import java.time.ZonedDateTime;

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
public class Message {

	private ZonedDateTime sent_at;
	
	private String content;
	
	private String sender_name;
	
}

