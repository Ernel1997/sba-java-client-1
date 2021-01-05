package com.sba.ppp.loanorigination.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sba.ppp.loanorigination.domain.FranchiseOrNaicsCodesResponse;
import com.sba.ppp.loanorigination.domain.LoanRequest;
import com.sba.ppp.loanorigination.domain.PPPLoanOrigination;
import com.sba.ppp.loanorigination.domain.SbaPPPLoanOriginationStatusResponse;
import com.sba.ppp.loanorigination.service.SbaLoanOriginationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/sba/ppp/loan/origination")
@Api(value = "/sba/ppp/loan/origination", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Execute SBA PPP Loan Origination API"})
@Log4j2
public class SbaLoanOriginationController {
	
	@Autowired
	private SbaLoanOriginationService pppLoanOriginationService;

	@ApiOperation(value = "Execute SBA PPP Loan Origination API", response = LoanRequest.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Execute PPP Loan Origination Submitted", response = LoanRequest.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = LoanRequest.class)})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoanRequest> execute(@RequestBody LoanRequest request,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Submit Request Received: {}", request);
		LoanRequest response = pppLoanOriginationService.execute(request);
		
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Get SBA PPP Loan Origination Request Status", response = SbaPPPLoanOriginationStatusResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Origination Request Status", response = SbaPPPLoanOriginationStatusResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanOriginationStatusResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaPPPLoanOriginationStatusResponse> getSbaLoanRequestStatus(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "sba_number", required = false) String sbaNumber,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		SbaPPPLoanOriginationStatusResponse response = pppLoanOriginationService.getLoanStatus(page, sbaNumber);
		
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Get SBA PPP Loan Origination Request By slug", response = PPPLoanOrigination.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Origination Request Status By slug", response = PPPLoanOrigination.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = PPPLoanOrigination.class)})
	@GetMapping(value = "/{slug}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoanRequest> getSbaLoanRequestBySlug(@PathVariable(value = "slug", required = true) UUID slug,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		LoanRequest response = pppLoanOriginationService.getLoanStatusBySlug(slug);
		
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Delete SBA PPP Loan Origination Request By Slug")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Delete SBA PPP Loan Origination Request By Slug"),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred")})
	@DeleteMapping(value = "/{slug}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteSbaLoanRequestStatus(@PathVariable(value = "slug", required = true) UUID slug,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Delete Request Received.");
		pppLoanOriginationService.deletePPPLoanRequest(slug);
		
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Get SBA PPP Loan Origination Request Status", response = SbaPPPLoanOriginationStatusResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Origination Request Status", response = SbaPPPLoanOriginationStatusResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanOriginationStatusResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/naics")
	public ResponseEntity<FranchiseOrNaicsCodesResponse> getNaicsCodes(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "code", required = false) String code,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		FranchiseOrNaicsCodesResponse response = pppLoanOriginationService.getNaicsCode(page, code);
		
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Get SBA PPP Loan Origination Request Status", response = SbaPPPLoanOriginationStatusResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Origination Request Status", response = SbaPPPLoanOriginationStatusResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanOriginationStatusResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/franchise")
	public ResponseEntity<FranchiseOrNaicsCodesResponse> getFranchiseCodes(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "code", required = false) String code,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		FranchiseOrNaicsCodesResponse response = pppLoanOriginationService.getFranchiseCode(page, code);
		
		return ResponseEntity.ok(response);
	}
			
}
