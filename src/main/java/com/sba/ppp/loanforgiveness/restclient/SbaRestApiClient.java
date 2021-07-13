package com.sba.ppp.loanforgiveness.restclient;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.sba.ppp.loanforgiveness.domain.LoanDocument;
import com.sba.ppp.loanforgiveness.domain.LoanDocumentType;
import com.sba.ppp.loanforgiveness.domain.MessageReply;
import com.sba.ppp.loanforgiveness.domain.ReplyDocuments;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanDocumentTypeResponse;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanForgiveness;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanForgivenessStatusResponse;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanMessagesResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SbaRestApiClient {

    @Value("${sba.api-token}")
    private String apiToken;
    
    @Value("${sba.vendor-key}")
    private String vendorKey;
    
    @Value("${sba.loan-forgiveness.url}")
    private String loanForgivenessUrl;
    
    @Value("${sba.loan-documents.url}")
    private String loanDocumentsUrl;
    
    @Value("${sba.loan-document-types.url}")
    private String loanDocumentTypesUrl;
    
    @Value("${sba.loan-forgiveness-messages.url}")
    private String loanForgivenessMessagesUrl;
    
    @Value("${sba.loan-message-reply.url}")
    private String loanForgivenessMessageReplyUrl;
    
	private RestTemplate restTemplate;
	
	public static final String VENDOR_KEY_HEADER = "Vendor-Key";
	
    public SbaRestApiClient(@Qualifier("sbaRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SbaPPPLoanForgiveness invokeSbaLoanForgiveness(SbaPPPLoanForgiveness request) {
    	SbaPPPLoanForgiveness response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<SbaPPPLoanForgiveness> entity = new HttpEntity<SbaPPPLoanForgiveness>(request, headers); 
    	        
    	log.info("Submitting LoanForgiveness Request");
    	ResponseEntity<SbaPPPLoanForgiveness> resEntity = restTemplate.exchange(loanForgivenessUrl, 
    			HttpMethod.POST, entity, SbaPPPLoanForgiveness.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("LoanForgiveness Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while submitting LoanForgiveness Request");
		}
		return response;
    }
    
    public String updateSbaLoanForgivenessMessageReply(MessageReply request) {
    	String response = null;
    	HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    	headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    	headers.add(HttpHeaders.AUTHORIZATION, "Token " + apiToken);
    	headers.add(VENDOR_KEY_HEADER, vendorKey);
    
    	MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    	List<ReplyDocuments> docs = request.getReplyDocuments();
		for (ReplyDocuments replyDocuments : docs) {
			body.add("document_name", replyDocuments.getDocument_name());
			body.add("document_type", replyDocuments.getDocument_type());
			body.add("document", new FileSystemResource(replyDocuments.getFilePathToUpload()));
		}
    	body.add("content", request.getContent());

    	HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    	        
    	log.info("Update LoanForgiveness Message Reply");
    	ResponseEntity<String> resEntity = restTemplate.exchange(loanForgivenessMessageReplyUrl + request.getSlug().toString() + "/", 
    			HttpMethod.PUT, requestEntity, String.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Update LoanForgiveness Message Reply Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Updating LoanForgiveness Message Reply");
		}
		return response;
    }
    
    public LoanDocument uploadSbaLoanDocument(LoanDocument request) {    	
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
    
    public SbaPPPLoanForgivenessStatusResponse getSbaLoanForgiveness(Integer pageNumber, String sbaNumber) {
    	SbaPPPLoanForgivenessStatusResponse response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(headers); 
    	
    	UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanForgivenessUrl);
    	
    	if (StringUtils.isNotBlank(sbaNumber)) {
    		uriBuilder.queryParam("sba_number", sbaNumber);
    	}
    	else if (pageNumber != null && pageNumber > 0) {
    		uriBuilder.queryParam("page", pageNumber);
    	}
    	        
    	log.info("Retreiving LoanForgiveness Request Status");
    	ResponseEntity<SbaPPPLoanForgivenessStatusResponse> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
    			HttpMethod.GET, entity, SbaPPPLoanForgivenessStatusResponse.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Retreiving LoanForgiveness Request Status. Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Retreiving LoanForgiveness Request Status");
		}
		return response;
    }
    
    public SbaPPPLoanForgiveness getSbaLoanForgivenessBySbaNumber(String sbaNumber) {
    	SbaPPPLoanForgiveness response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(headers); 
    	
    	UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanForgivenessUrl);
    	if (sbaNumber != null) {
    		uriBuilder.queryParam("sba_number", sbaNumber);
    	}
    	        
    	log.info("Retreiving LoanForgiveness Request Status by SBA Number");
    	ResponseEntity<SbaPPPLoanForgiveness> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
    			HttpMethod.GET, entity, SbaPPPLoanForgiveness.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Retreiving LoanForgiveness Request Status by SBA Number. Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Retreiving LoanForgiveness Request Status by SBA Number");
		}
		return response;
    }
    
    public SbaPPPLoanMessagesResponse getSbaLoanMessagesBySbaNumber(Integer pageNumber, 
    		String sbaNumber, boolean isComplete) {
    	SbaPPPLoanMessagesResponse response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(headers); 
    	
    	UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanForgivenessMessagesUrl);
    	if (sbaNumber != null) {
    		uriBuilder.queryParam("sba_number", sbaNumber);
    	}
    	else if (pageNumber != null && pageNumber > 0) {
    		uriBuilder.queryParam("page", pageNumber);
    	}
    	
    	if (isComplete) {
    		uriBuilder.queryParam("is_complete", isComplete);
    	}
    	        
    	log.info("Retreiving LoanForgiveness Request Messages by SBA Number");
    	ResponseEntity<SbaPPPLoanMessagesResponse> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
    			HttpMethod.GET, entity, SbaPPPLoanMessagesResponse.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Retreiving LoanForgiveness Request Messages by SBA Number. Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Retreiving LoanForgiveness Request Messages by SBA Number");
		}
		return response;
    }
    
    public SbaPPPLoanMessagesResponse getSbaLoanForgivenessMessagesBySlug(UUID slug) {
    	SbaPPPLoanMessagesResponse response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(headers); 
    	        
    	log.info("Retreiving LoanForgiveness Message");
    	ResponseEntity<SbaPPPLoanMessagesResponse> resEntity = restTemplate.exchange(loanForgivenessMessagesUrl + "/" + slug, 
    			HttpMethod.GET, entity, SbaPPPLoanMessagesResponse.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Retreiving LoanForgiveness Message. Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Retreiving LoanForgiveness Message");
		}
		return response;
    }
    
    public SbaPPPLoanForgiveness getSbaLoanForgivenessBySlug(UUID slug) {
    	SbaPPPLoanForgiveness response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(headers); 
    	        
    	log.info("Retreiving LoanForgiveness Request Status");
    	ResponseEntity<SbaPPPLoanForgiveness> resEntity = restTemplate.exchange(loanForgivenessUrl + "/" + slug, 
    			HttpMethod.GET, entity, SbaPPPLoanForgiveness.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Retreiving LoanForgiveness Request Status. Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Retreiving LoanForgiveness Request Status");
		}
		return response;
    }
    
    public void deleteSbaLoanForgiveness(UUID slug) {
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(null, headers); 
    	        
    	log.info("Deleting LoanForgiveness Request");
    	
    	ResponseEntity<Void> resEntity = restTemplate.exchange(loanForgivenessUrl + slug.toString() +"/", 
    			HttpMethod.DELETE, entity, Void.class);
    	
    	if (resEntity != null) {
    		if (HttpStatus.NO_CONTENT.equals(resEntity.getStatusCode())) {
        		log.info("Deleting LoanForgiveness Request Completed. Slug: {}", slug);
    		}
    		else {
    			log.error("Error while Deleting LoanForgiveness Request Completed. Slug: {} HttpStatusCode: {}", slug,
    					resEntity.getStatusCodeValue());
    		}
    	}
    }
    
    public SbaPPPLoanDocumentTypeResponse getSbaLoanDocumentTypes(Map<String, String> reqParams) {
    	SbaPPPLoanDocumentTypeResponse response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(headers); 
    	
    	UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(loanDocumentTypesUrl);
    	
    	reqParams.entrySet().forEach(r -> {    		
    		uriBuilder.queryParam(r.getKey(), r.getValue());
    	});
    	        
    	log.info("Retreiving Loan Document Types");
    	ResponseEntity<SbaPPPLoanDocumentTypeResponse> resEntity = restTemplate.exchange(uriBuilder.toUriString(), 
    			HttpMethod.GET, entity, SbaPPPLoanDocumentTypeResponse.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Retreiving Loan Document Types. Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Retreiving Loan Document Types");
		}
		return response;
    }
    
    public LoanDocumentType getSbaLoanDocumentTypeById(Integer id) {
    	LoanDocumentType response = null;
    	HttpHeaders headers = getHttpHeaders();
    	HttpEntity<String> entity = new HttpEntity<String>(headers); 
    	        
    	log.info("Retreiving Loan Document Type by Id");
    	ResponseEntity<LoanDocumentType> resEntity = restTemplate.exchange(loanDocumentTypesUrl + "/" + id, 
    			HttpMethod.GET, entity, LoanDocumentType.class);
    	
    	if (resEntity != null) {
    		response = resEntity.getBody();
    		Gson gson = new Gson();
			log.info("Retreiving Loan Document Type by Id. Response: {}", gson.toJson(response));
		}
		else {
			log.error("Error while Retreiving Loan Document Type by Id: {}", id);
		}
		return response;
    }

	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, "Token " + apiToken);
    	headers.add(VENDOR_KEY_HEADER, vendorKey);
		return headers;
	}
 
}
