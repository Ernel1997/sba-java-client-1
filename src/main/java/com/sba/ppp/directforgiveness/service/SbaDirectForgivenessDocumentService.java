package com.sba.ppp.directforgiveness.service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sba.ppp.directforgiveness.domain.LoanDocument;
import com.sba.ppp.directforgiveness.domain.SbaPPPLoanDocumentTypeResponse;
import com.sba.ppp.directforgiveness.restclient.SbaDirectForgivenessRestApiClient;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service
@Getter
@Setter
@Log4j2
public class SbaDirectForgivenessDocumentService {
	
	@Autowired
	private SbaDirectForgivenessRestApiClient sbaRestApiClient;
	
	public LoanDocument uploadSbaDirectForgivenessRequestDocument(LoanDocument request) throws IOException {
		log.info("Processing Loan Document Submission.");
		return sbaRestApiClient.uploadSbaDirectForgivenessRequestDocument(request);
		
	}
	
	public SbaPPPLoanDocumentTypeResponse getDirectForgivenessRequestDocument(String sbaNumber) throws IOException {
		log.info("Retreiving Sba Loan Document Types");
		return sbaRestApiClient.getDirectForgivenessRequestDocument(sbaNumber);
	}

	public LoanDocument uploadSbaDirectForgivenessRequestDocument(MultipartFile document, String name,
			Integer document_type, UUID etran_loan) {
		// TODO Auto-generated method stub
//		return sbaRestApiClient.getDirectForgivenessRequestDocument(sbaNumber);
		return sbaRestApiClient.uploadSbaDirectForgivenessRequestDocument(document, name, document_type, etran_loan);
	}

}
