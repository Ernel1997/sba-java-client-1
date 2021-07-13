package com.sba.ppp.loanorigination.restclient;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.sba.ppp.loanorigination.domain.ETranPPPLoanValidationResponse;
import com.sba.ppp.loanorigination.domain.FranchiseOrNaicsCodesResponse;
import com.sba.ppp.loanorigination.domain.GetEidlLoanValidationResponse;
import com.sba.ppp.loanorigination.domain.GetValidatedAndStandardizedAddressResponse;
import com.sba.ppp.loanorigination.domain.LoanRequest;
import com.sba.ppp.loanorigination.domain.PPPLoanOrigination;
import com.sba.ppp.loanorigination.domain.ReplyDocuments;
import com.sba.ppp.loanorigination.domain.SbaPPPLoanOriginationStatusResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SbaOriginationRestApiClient {

	@Value("${sba.api-token}")
	private String apiToken;

	@Value("${sba.vendor-key}")
	private String vendorKey;

	@Value("${sba.loan-origination.url}")
	private String loanOriginationUrl;

	private RestTemplate restTemplate;

	public static final String VENDOR_KEY_HEADER = "Vendor-Key";

	public SbaOriginationRestApiClient(@Qualifier("sbaOriginationRestTemplate") RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public LoanRequest invokeSbaLoanOrigination(LoanRequest request) {
		LoanRequest response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<LoanRequest> entity = new HttpEntity<LoanRequest>(request, headers); 

//		log.info("Submitting LoanForgiveness Request");
		ResponseEntity<LoanRequest> resEntity = restTemplate.exchange(loanOriginationUrl, 
				HttpMethod.POST, entity, LoanRequest.class);

		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
			log.info("LoanOrigination Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while submitting LoanForgiveness Request");
		}
		return response;
	}


	public SbaPPPLoanOriginationStatusResponse getSbaLoanOrigination(Integer pageNumber, String sbaNumber) {
		SbaPPPLoanOriginationStatusResponse response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers); 

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanOriginationUrl);

		if (StringUtils.isNotBlank(sbaNumber)) {
			uriBuilder.queryParam("sba_number", sbaNumber);
		}
		else if (pageNumber != null && pageNumber > 0) {
			uriBuilder.queryParam("page", pageNumber);
		}

		ResponseEntity<SbaPPPLoanOriginationStatusResponse> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
				HttpMethod.GET, entity, SbaPPPLoanOriginationStatusResponse.class);

		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
//			log.info("Retreiving LoanForgiveness Request Status. Response: {}", gson.toJson(response));
		}
		else {
//			log.error("Error while Retreiving LoanForgiveness Request Status");
		}
		return response;
	}

	public PPPLoanOrigination getSbaLoanForgivenessBySbaNumber(String sbaNumber) {
		PPPLoanOrigination response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers); 

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanOriginationUrl);
		if (sbaNumber != null) {
			uriBuilder.queryParam("sba_number", sbaNumber);
		}

		ResponseEntity<PPPLoanOrigination> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
				HttpMethod.GET, entity, PPPLoanOrigination.class);

		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
	//		log.info("Retreiving LoanForgiveness Request Status by SBA Number. Response: {}", gson.toJson(response));
		}
		else {
	//		log.error("Error while Retreiving LoanForgiveness Request Status by SBA Number");
		}
		return response;
	}


	public LoanRequest getSbaLoanForgivenessBySlug(UUID slug) {
		LoanRequest response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers); 

		//log.info("Retreiving Loan Origination Request Status");
		ResponseEntity<LoanRequest> resEntity = restTemplate.exchange(loanOriginationUrl + "/" + slug, 
				HttpMethod.GET, entity, LoanRequest.class);

		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
		//	log.info("Retreiving Loan Origination Request Status. Response: {}", gson.toJson(response));
		}
		else {
		//	log.error("Error while Retreiving Loan Origination Request Status");
		}
		return response;
	}

	public void deleteSbaLoanOrigination(UUID slug) {
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers); 

//		log.info("Deleting Loan Origination Request");

		ResponseEntity<Void> resEntity = restTemplate.exchange(loanOriginationUrl + slug.toString() +"/", 
				HttpMethod.DELETE, entity, Void.class);

		if (resEntity != null) {
			if (HttpStatus.NO_CONTENT.equals(resEntity.getStatusCode())) {
	//			log.info("Deleting Loan Origination Request Completed. Slug: {}", slug);
			}
			else {
		/*		log.error("Error while Deleting Loan Origination Request Completed. Slug: {} HttpStatusCode: {}", slug,
						resEntity.getStatusCodeValue());
			*/}
		}
	}


	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.AUTHORIZATION, "Token " + apiToken);
		headers.add(VENDOR_KEY_HEADER, vendorKey);
		return headers;
	}

	public FranchiseOrNaicsCodesResponse getFranchiseOrNaicsCode(Integer pageNumber, String code, String description, String codeType) {
		FranchiseOrNaicsCodesResponse response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers); 

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanOriginationUrl);

		if (StringUtils.isNotBlank(code)) {
			uriBuilder.queryParam("code", code);
		}
		else if (pageNumber != null && pageNumber > 0) {
			uriBuilder.queryParam("page", pageNumber);
		}

		if (StringUtils.isNotBlank(description)) {
			uriBuilder.queryParam("description", description);
		}
		else if (pageNumber != null && pageNumber > 0) {
			uriBuilder.queryParam("page", pageNumber);
		}
		if (codeType.equalsIgnoreCase("naics")) {
			uriBuilder.path("naics");
			uriBuilder.replacePath("/api/naics");
		} else if (codeType.equalsIgnoreCase("franchise")) {
			uriBuilder.replacePath("/api/franchise");
		}

		//log.info("Retreiving LoanForgiveness Request Status");
		ResponseEntity<FranchiseOrNaicsCodesResponse> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
				HttpMethod.GET, entity, FranchiseOrNaicsCodesResponse.class);

		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
	//		log.info("Retreiving LoanForgiveness Request Status. Response: {}", gson.toJson(response));
		}
		else {
	//		log.error("Error while Retreiving FranchiseOrNaicsCodesResponse Request Status");
		}
		return response;
	}

	public ETranPPPLoanValidationResponse getETranPPPLoanValidationResponse(Integer pageNumber, String sba_number) {
		ETranPPPLoanValidationResponse response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers); 

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanOriginationUrl);

		if (StringUtils.isNotBlank(sba_number)) {
			uriBuilder.queryParam("sba_number", sba_number);
		}     if (pageNumber != null && pageNumber > 0) {
			uriBuilder.queryParam("page", pageNumber);
		}
		uriBuilder.replacePath("/api/etran_ppp_validation");

		ResponseEntity<ETranPPPLoanValidationResponse> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
				HttpMethod.GET, entity, ETranPPPLoanValidationResponse.class);
		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
		//	log.info("Retreiving ETranPPPLoanValidationResponse Request Status. Response: {}", gson.toJson(response));
		
		}
		else {
		//	log.error("Error while Retreiving ETranPPPLoanValidationResponse Request Status");
		}
		return response;
	}


	public GetValidatedAndStandardizedAddressResponse[] getValidateAndStandardizedAddressResponse(String address_1,String address_2,String city,String state,String zip_code) {
		GetValidatedAndStandardizedAddressResponse[] response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers); 

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanOriginationUrl);//"https://beta.forgiveness.sba.gov/api/address_validation/");

		if (StringUtils.isNotBlank(address_1)) {
			uriBuilder.queryParam("address_1", address_1);
		}

		if (StringUtils.isNotBlank(address_2)) {
			uriBuilder.queryParam("address_2", address_2);
		}

		if (StringUtils.isNotBlank(city)) {
			uriBuilder.queryParam("city", city);
		}
		if (StringUtils.isNotBlank(state)) {
			uriBuilder.queryParam("state", state);
		}
		if (StringUtils.isNotBlank(zip_code)) {
			uriBuilder.queryParam("zip_code", zip_code);
		}

		uriBuilder.replacePath("/api/address_validation/");

		ResponseEntity<GetValidatedAndStandardizedAddressResponse[]> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
				HttpMethod.GET, entity, GetValidatedAndStandardizedAddressResponse[].class);
	
		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
//			log.info("Retreiving Valid and standard address. Response: {}", gson.toJson(response));
			return response;
		}
		else {
//			log.error("Error while Retreiving Valid and standard address");
		}
		return response;
	}

	public GetEidlLoanValidationResponse getEidlLoanValidationResponse(Integer pageNumber, String eidl_loan_number) {
		GetEidlLoanValidationResponse response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers); 

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanOriginationUrl);

		if (StringUtils.isNotBlank(eidl_loan_number)) {
			uriBuilder.queryParam("eidl_loan_number", eidl_loan_number);
		}
		else if (pageNumber != null && pageNumber > 0) {
			uriBuilder.queryParam("page", pageNumber);
		}
		uriBuilder.replacePath("/api/etran_eidl_loan_validation");

	//	log.info("Retreiving Eidl Loan validation request ");
		ResponseEntity<GetEidlLoanValidationResponse> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
				HttpMethod.GET, entity, GetEidlLoanValidationResponse.class);

		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
	//		log.info("Retreiving Eidl Loan validation request. Response: {}", gson.toJson(response));
		}
		else {
	//		log.error("Error while Retreiving Eidl Loan validation request");
		}
		return response;
	}

}
