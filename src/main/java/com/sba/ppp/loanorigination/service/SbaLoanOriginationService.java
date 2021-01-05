package com.sba.ppp.loanorigination.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.ppp.loanorigination.domain.FranchiseOrNaicsCodesResponse;
import com.sba.ppp.loanorigination.domain.LoanRequest;
import com.sba.ppp.loanorigination.domain.PPPLoanOrigination;
import com.sba.ppp.loanorigination.domain.SbaPPPLoanOriginationStatusResponse;
import com.sba.ppp.loanorigination.restclient.SbaRestApiClient;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service
@Getter
@Setter
@Log4j2
public class SbaLoanOriginationService {
	
	@Autowired
	private SbaRestApiClient sbaRestApiClient;
	
	public LoanRequest execute(LoanRequest request) throws IOException {
		log.info("Processing Sba Loan Forgiveness request");
		return sbaRestApiClient.invokeSbaLoanOrigination(request);
		
	}
	
	public SbaPPPLoanOriginationStatusResponse getLoanStatus(Integer page, String sbaNumber) throws IOException {
		log.info("Retreiving Sba Loan Forgiveness request");
		return sbaRestApiClient.getSbaLoanOrigination(page, sbaNumber);
		
	}
	
	public LoanRequest getLoanStatusBySlug(UUID slug) throws IOException {
		log.info("Retreiving Sba Loan Forgiveness request");
		return sbaRestApiClient.getSbaLoanForgivenessBySlug(slug);
		
	}
		
	public void deletePPPLoanRequest(UUID slug) throws IOException {
		log.info("Deleting Sba Loan Forgiveness request for slug: {}", slug);
		sbaRestApiClient.deleteSbaLoanOrigination(slug);
	}

	public FranchiseOrNaicsCodesResponse getNaicsCode(Integer page, String code) {
		// TODO Auto-generated method stub
		return sbaRestApiClient.getFranchiseOrNaicsCode(page, code, "naics");
	}

	public FranchiseOrNaicsCodesResponse getFranchiseCode(Integer page, String code) {
		// TODO Auto-generated method stub
		return sbaRestApiClient.getFranchiseOrNaicsCode(page, code, "franchise");
	}

}
