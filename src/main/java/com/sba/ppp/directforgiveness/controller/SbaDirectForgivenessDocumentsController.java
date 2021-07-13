package com.sba.ppp.directforgiveness.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sba.ppp.directforgiveness.domain.LoanDocument;
import com.sba.ppp.directforgiveness.domain.SbaPPPLoanDocumentTypeResponse;
import com.sba.ppp.directforgiveness.service.SbaDirectForgivenessDocumentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/sba/ppp/direct/forgiveness/document")
@Api(value = "/sba/ppp/direct/forgiveness/document", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Execute SBA PPP Direct Forgiveness API"})
@Log4j2
public class SbaDirectForgivenessDocumentsController {
	
	@Autowired
	private SbaDirectForgivenessDocumentService sbaLoanDocumentService;

	@ApiOperation(value = "Get forgiveness request documents", response = SbaPPPLoanDocumentTypeResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get forgiveness request documents", response = SbaPPPLoanDocumentTypeResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanDocumentTypeResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaPPPLoanDocumentTypeResponse> getDirectForgivenessDocuments(
			@RequestParam(value = "sba_number", required = false) String sbaNumber,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Loan Document Types.");
		
		SbaPPPLoanDocumentTypeResponse documentTypes = sbaLoanDocumentService.getDirectForgivenessRequestDocument(sbaNumber);
		
		return ResponseEntity.ok(documentTypes);
	}

	@ApiOperation(value = "Upload document to Direct Forgiveness", response = LoanDocument.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Upload document to Direct Forgiveness", response = LoanDocument.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = LoanDocument.class)})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoanDocument> uploadDirectForgivenessRequestDocument(
			@RequestParam("document") MultipartFile document,
			@RequestParam("name") String name,
			@RequestParam("document_type") Integer document_type,
			@RequestParam("etran_loan") UUID etran_loan,
			
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Submit Loan Document.");
		
		LoanDocument response = sbaLoanDocumentService.uploadSbaDirectForgivenessRequestDocument(document, name, document_type, etran_loan);
		
		return ResponseEntity.ok(response);
	}
			
}
