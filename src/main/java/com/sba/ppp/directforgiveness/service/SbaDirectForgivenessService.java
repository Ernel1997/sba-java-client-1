package com.sba.ppp.directforgiveness.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.ppp.directforgiveness.domain.SbaDirectForgiveness;
import com.sba.ppp.directforgiveness.domain.SbaDirectForgivenessEligibilityResponse;
import com.sba.ppp.directforgiveness.domain.SbaDirectForgivenessRequestStatusResponse;
import com.sba.ppp.directforgiveness.restclient.SbaDirectForgivenessRestApiClient;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service
@Getter
@Setter
@Log4j2
public class SbaDirectForgivenessService {
	
	@Autowired
	private SbaDirectForgivenessRestApiClient sbaRestApiClient;
	
	// This is for submitting the direct forgiveness request
	public SbaDirectForgiveness execute(SbaDirectForgiveness request) throws IOException {
		log.info("Processing Sba Diect Forgiveness request");
		return sbaRestApiClient.invokeSbaDirectForgiveness(request);
		
	}
	
	public String getDirectForgivenessStatus(String sbaNumber) throws IOException {
		log.info("Retreiving Sba Loan Forgiveness request");
		return sbaRestApiClient.getDirectForgivenessStatus(sbaNumber);
		
	}
	
	public SbaDirectForgivenessEligibilityResponse getDirectForgivenessEligibility(String sbaNumber, String tin) throws IOException {
		log.info("Retreiving Sba Direct Forgiveness eligibility request");
		return sbaRestApiClient.getDirectForgivenessEligibility(sbaNumber,tin);
		
	}
	
	public SbaDirectForgivenessRequestStatusResponse getDirectForgivenessRequest(Integer pageNumber) throws IOException {
		log.info("Retreiving Sba Direct pending Forgiveness request");
		return sbaRestApiClient.getDirectForgivenessRequest(pageNumber);
		
	}
		
	public void deleteDirectForgivenessRequest(String sbaNumber) throws IOException {
		log.info("Deleting Sba Direct Forgiveness request for slug: {}", sbaNumber);
		sbaRestApiClient.deleteDirectForgivenessRequest(sbaNumber);
	}

}
