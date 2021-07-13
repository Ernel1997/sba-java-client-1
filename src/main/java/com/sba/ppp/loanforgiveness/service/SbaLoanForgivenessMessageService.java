package com.sba.ppp.loanforgiveness.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.ppp.loanforgiveness.domain.MessageReply;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanForgivenessMessage;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanMessagesResponse;
import com.sba.ppp.loanforgiveness.restclient.SbaRestApiClient;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service
@Getter
@Setter
@Log4j2
public class SbaLoanForgivenessMessageService {
	
	@Autowired
	private SbaRestApiClient sbaRestApiClient;
	
	public String updateSbaLoanMessageReply(MessageReply request) throws IOException {
		log.info("Processing Update LoanForgiveness Message Reply");
		return sbaRestApiClient.updateSbaLoanForgivenessMessageReply(request);
		
	}
	
	public SbaPPPLoanMessagesResponse getSbaLoanMessages(Integer page, 
			String sbaNumber, boolean isComplete) throws IOException {
		log.info("Retreiving LoanForgiveness Request Messages by SBA Number");
		return sbaRestApiClient.getSbaLoanMessagesBySbaNumber(page, sbaNumber, isComplete);
		
	}
	
	public SbaPPPLoanMessagesResponse getLoanMessagesBySlug(UUID slug) throws IOException {
		log.info("Retreiving LoanForgiveness Message");
		return sbaRestApiClient.getSbaLoanForgivenessMessagesBySlug(slug);
		
	}

}
