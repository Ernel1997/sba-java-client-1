package com.sba.ppp.loanorigination.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.ppp.loanorigination.domain.ETranPPPLoanValidationResponse;
import com.sba.ppp.loanorigination.domain.FranchiseOrNaicsCodesResponse;
import com.sba.ppp.loanorigination.domain.GetEidlLoanValidationResponse;
import com.sba.ppp.loanorigination.domain.GetValidatedAndStandardizedAddressResponse;
import com.sba.ppp.loanorigination.domain.LoanRequest;
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

	public FranchiseOrNaicsCodesResponse getNaicsCode(Integer page, String code,String description) {
		// TODO Auto-generated method stub
		return sbaRestApiClient.getFranchiseOrNaicsCode(page, code, description, "naics");
	}

	public FranchiseOrNaicsCodesResponse getFranchiseCode(Integer page, String code, String  description) {
		// TODO Auto-generated method stub
		return sbaRestApiClient.getFranchiseOrNaicsCode(page, code, description,  "franchise");
	}

	public GetValidatedAndStandardizedAddressResponse[] getValidateAndStandardizedAddressResponse(String address_1,String address_2,String city,String state,String zip_code) {
		// TODO Auto-generated method stub
		return sbaRestApiClient.getValidateAndStandardizedAddressResponse(address_1, address_2, city, state, zip_code);
	}
	
	public GetEidlLoanValidationResponse getEidlLoanValidationResponse(Integer page, String eidl_loan_number) {
		// TODO Auto-generated method stub
		return sbaRestApiClient.getEidlLoanValidationResponse(page, eidl_loan_number);
	}

	public ETranPPPLoanValidationResponse getETranPPPLoanValidationResponse(Integer page, String sba_number) {
		// TODO Auto-generated method stub
		return sbaRestApiClient.getETranPPPLoanValidationResponse(page, sba_number);
	}
}
