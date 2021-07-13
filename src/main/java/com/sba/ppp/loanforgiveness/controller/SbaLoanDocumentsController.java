package com.sba.ppp.loanforgiveness.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sba.ppp.loanforgiveness.domain.LoanDocument;
import com.sba.ppp.loanforgiveness.domain.LoanDocumentType;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanDocumentTypeResponse;
import com.sba.ppp.loanforgiveness.service.SbaLoanDocumentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/sba/ppp/loan/document")
@Api(value = "/sba/ppp/loan/document", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Execute SBA PPP Loan Forgiveness API"})
@Log4j2
public class SbaLoanDocumentsController {
	
	@Autowired
	private SbaLoanDocumentService sbaLoanDocumentService;

	@ApiOperation(value = "Get SBA PPP Loan Document Types API", response = SbaPPPLoanDocumentTypeResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Document Types", response = SbaPPPLoanDocumentTypeResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanDocumentTypeResponse.class)})
	@GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaPPPLoanDocumentTypeResponse> getDocumentTypes(
			@RequestParam Map<String, String> reqParams,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Loan Document Types.");
		
		SbaPPPLoanDocumentTypeResponse documentTypes = sbaLoanDocumentService.getDocumentTypes(reqParams);
		
		return ResponseEntity.ok(documentTypes);
	}
	
	@ApiOperation(value = "Get SBA PPP Loan Document Type By Id", response = LoanDocumentType.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Document Types", response = LoanDocumentType.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = LoanDocumentType.class)})
	@GetMapping(value = "/type/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoanDocumentType> getDocumentTypes(
			@PathVariable(value = "id", required = true) Integer id,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Loan Document Type by id: {}", id);
		
		LoanDocumentType documentTypes = sbaLoanDocumentService.getDocumentTypeById(id);
		
		return ResponseEntity.ok(documentTypes);
	}
	
	@ApiOperation(value = "Submit SBA PPP Loan Document", response = LoanDocument.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Submit SBA PPP Loan Document", response = LoanDocument.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = LoanDocument.class)})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoanDocument> submitLoanDocument(
			@RequestBody LoanDocument request,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Submit Loan Document.");
		
		LoanDocument document = sbaLoanDocumentService.submitLoanDocument(request);
		
		return ResponseEntity.ok(document);
	}
			
}
