package com.sba.ppp.loanforgiveness.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.ppp.loanforgiveness.domain.LoanDocument;
import com.sba.ppp.loanforgiveness.domain.LoanDocumentType;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanDocumentTypeResponse;
import com.sba.ppp.loanforgiveness.restclient.SbaRestApiClient;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service
@Getter
@Setter
@Log4j2
public class SbaLoanDocumentService {
	
	@Autowired
	private SbaRestApiClient sbaRestApiClient;
	
	public LoanDocument submitLoanDocument(LoanDocument request) throws IOException {
		log.info("Processing Loan Document Submission.");
		return sbaRestApiClient.uploadSbaLoanDocument(request);
		
	}
	
	public SbaPPPLoanDocumentTypeResponse getDocumentTypes(Map<String, String> reqParams) throws IOException {
		log.info("Retreiving Sba Loan Document Types");
		return sbaRestApiClient.getSbaLoanDocumentTypes(reqParams);
		
	}
	
	public LoanDocumentType getDocumentTypeById(Integer id) throws IOException {
		log.info("Retreiving Sba Loan Document Type by Id: {}", id);
		return sbaRestApiClient.getSbaLoanDocumentTypeById(id);
		
	}


}
