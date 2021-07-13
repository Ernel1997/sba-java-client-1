package com.sba.ppp.directforgiveness.restclient;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.sba.ppp.directforgiveness.domain.LoanDocument;
import com.sba.ppp.directforgiveness.domain.SbaDirectForgiveness;
import com.sba.ppp.directforgiveness.domain.SbaDirectForgivenessEligibilityResponse;
import com.sba.ppp.directforgiveness.domain.SbaDirectForgivenessRequestStatusResponse;
import com.sba.ppp.directforgiveness.domain.SbaPPPLoanDocumentTypeResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SbaDirectForgivenessRestApiClient {

    @Value("${sba.api-token}")
    private String apiToken;
    
    @Value("${sba.vendor-key}")
    private String vendorKey;
    
    @Value("${sba.loan-direct-forgiveness.url}")
    private String loanDirectForgivenessUrl;
    
    @Value("${sba.loan-direct-forgiveness-documents.url}")
    private String loanDocumentsUrl;
    
    @Value("${sba.loan-direct-forgiveness-status.url}")
    private String directForgivenessStatusUrl;
    
    @Value("${sba.loan-direct-forgiveness-eligibility.url}")
    private String directForgivenessEligibilityUrl;
    
    @Value("${sba.loan-direct-forgiveness-request.url}")
    private String directForgivenessRequestUrl;
    
    
	private RestTemplate restTemplate;
	
	public static final String VENDOR_KEY_HEADER = "Vendor-Key";
	
    public SbaDirectForgivenessRestApiClient(@Qualifier("sbaDirectForgivenessRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, "Token " + apiToken);
    	headers.add(VENDOR_KEY_HEADER, vendorKey);
		return headers;
	}

	public SbaDirectForgiveness invokeSbaDirectForgiveness(SbaDirectForgiveness request) {
		SbaDirectForgiveness response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<SbaDirectForgiveness> entity = new HttpEntity<SbaDirectForgiveness>(request, headers); 
    	        
    	log.info("Submitting Direct Forgiveness Request");
    	ResponseEntity<SbaDirectForgiveness> resEntity = restTemplate.exchange(loanDirectForgivenessUrl, 
    			HttpMethod.POST, entity, SbaDirectForgiveness.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("LoanForgiveness Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while submitting Direct Forgiveness Request");
		}
		return response;
	}

	public String getDirectForgivenessStatus(String sbaNumber) {
		String response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(headers); 
    	        
    	log.info("Retreiving LoanForgiveness Request Status");
    	ResponseEntity<String> resEntity = restTemplate.exchange(directForgivenessStatusUrl + sbaNumber, 
    			HttpMethod.GET, entity, String.class);

		Gson gson = new Gson();
    	if (resEntity != null) {
    		response = resEntity.getBody();
			log.info("Retreiving Direct Forgiveness Request Status. Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Retreiving Direct Forgiveness Request Status");
			return "Internal Error";
		}
		return response;
	}

	public SbaDirectForgivenessEligibilityResponse getDirectForgivenessEligibility(String sbaNumber, String tin) {
		SbaDirectForgivenessEligibilityResponse response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(directForgivenessEligibilityUrl);

		uriBuilder.queryParam("sba_number", sbaNumber);
		uriBuilder.queryParam("tin", tin);

		ResponseEntity<SbaDirectForgivenessEligibilityResponse> resEntity = restTemplate.exchange(
				uriBuilder.toUriString(), HttpMethod.GET, entity, SbaDirectForgivenessEligibilityResponse.class);

		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
			log.info("Retreiving forgiveness eligibility. Response: {}", gson.toJson(response));
		} else {
			log.error("Error while Retreiving forgiveness eligibility");
		}
		return response;
	}

	public void deleteDirectForgivenessRequest(String sbaNumber) {
		HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(null, headers); 
    	        
    	log.info("Deleting LoanForgiveness Request");
    	
    	ResponseEntity<Void> resEntity = restTemplate.exchange(loanDirectForgivenessUrl + sbaNumber +"/", 
    			HttpMethod.DELETE, entity, Void.class);
    	
    	if (resEntity != null) {
    		if (HttpStatus.NO_CONTENT.equals(resEntity.getStatusCode())) {
        		log.info("Deleting Direct Forgiveness Request Completed. sbaNumber: {}", sbaNumber);
    		}
    		else {
    			log.error("Error while Deleting Direct Forgiveness Request Completed. sba number: {} HttpStatusCode: {}", sbaNumber,
    					resEntity.getStatusCodeValue());
    		}
    	}	
	}

	public SbaDirectForgivenessRequestStatusResponse getDirectForgivenessRequest(
			Integer pageNumber) {
		SbaDirectForgivenessRequestStatusResponse response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(headers); 
    	
    	UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(directForgivenessRequestUrl);
    	
    	if (pageNumber != null && pageNumber > 0) {
    		uriBuilder.queryParam("page", pageNumber);
    	}
    	        
    	log.info("Retreiving Pending Forgiveness Request");
    	ResponseEntity<SbaDirectForgivenessRequestStatusResponse> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
    			HttpMethod.GET, entity, SbaDirectForgivenessRequestStatusResponse.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Retreiving Pending Forgiveness Request. Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Retreiving Pending Forgiveness Request");
		}
		return response;
	}

	public LoanDocument uploadSbaDirectForgivenessRequestDocument(LoanDocument request) {
		LoanDocument response = null;
    	HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    	headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    	headers.add(HttpHeaders.AUTHORIZATION, "Token " + apiToken);
    	headers.add(VENDOR_KEY_HEADER, vendorKey);

    	MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    	body.add("name", request.getName());
    	body.add("document_type", request.getDocument_type());
    	body.add("etran_loan", request.getEtran_loan().toString());
    	body.add("document", new FileSystemResource(request.getFilePathToUpload()));
    	
    	HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    	        
    	log.info("Submitting Upload Document Request");
    	ResponseEntity<LoanDocument> resEntity = restTemplate.exchange(loanDocumentsUrl, 
    			HttpMethod.POST, requestEntity, LoanDocument.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Upload Document Service Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Uploading Document");
		}
		return response;

	}

	public SbaPPPLoanDocumentTypeResponse getDirectForgivenessRequestDocument(
			String sbaNumber) {
		SbaPPPLoanDocumentTypeResponse response = null;
		HttpHeaders headers = getHttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanDocumentsUrl);

		uriBuilder.queryParam("sba_number", sbaNumber);

		ResponseEntity<SbaPPPLoanDocumentTypeResponse> resEntity = restTemplate.exchange(
				uriBuilder.toUriString(), HttpMethod.GET, entity, SbaPPPLoanDocumentTypeResponse.class);

		if (resEntity != null) {
			response = resEntity.getBody();
			Gson gson = new Gson();
			log.info("Retreiving forgiveness eligibility. Response: {}", gson.toJson(response));
		} else {
			log.error("Error while Retreiving forgiveness eligibility");
		}
		return response;
	}

	public LoanDocument uploadSbaDirectForgivenessRequestDocument(MultipartFile document, String name,
			Integer document_type, UUID etran_loan) {
		LoanDocument response = null;
    	HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    	headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    	headers.add(HttpHeaders.AUTHORIZATION, "Token " + apiToken);
    	headers.add(VENDOR_KEY_HEADER, vendorKey);

    	MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    	body.add("name", name);
    	body.add("document_type", document_type);
    	body.add("etran_loan", etran_loan.toString());
    	try {
			File file = File.createTempFile(name, "");
			document.transferTo(file);
	    	body.add("document", new FileSystemResource(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Error while Uploading Document");
			e.printStackTrace();
			return response;
		}

    	HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    	        
    	log.info("Submitting Upload Document Request");
    	ResponseEntity<LoanDocument> resEntity = restTemplate.exchange(loanDocumentsUrl, 
    			HttpMethod.POST, requestEntity, LoanDocument.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Upload Document Service Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Uploading Document");
		}
		return response;
	}
}
