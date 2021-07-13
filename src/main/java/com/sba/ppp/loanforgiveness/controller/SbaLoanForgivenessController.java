package com.sba.ppp.loanforgiveness.controller;

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

import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanForgiveness;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanForgivenessStatusResponse;
import com.sba.ppp.loanforgiveness.service.SbaLoanForgivenessService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/sba/ppp/loan/forgiveness")
@Api(value = "/sba/ppp/loan/forgiveness", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Execute SBA PPP Loan Forgiveness API"})
@Log4j2
public class SbaLoanForgivenessController {
	
	@Autowired
	private SbaLoanForgivenessService sbaLoanForgivenessService;

	@ApiOperation(value = "Execute SBA PPP Loan Forgiveness API", response = SbaPPPLoanForgiveness.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Execute SBA PPP Loan Forgiveness Submitted", response = SbaPPPLoanForgiveness.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanForgiveness.class)})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaPPPLoanForgiveness> execute(@RequestBody SbaPPPLoanForgiveness request,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Submit Request Received: {}", request);
		SbaPPPLoanForgiveness response = sbaLoanForgivenessService.execute(request);
		
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Get SBA PPP Loan Forgiveness Request Status", response = SbaPPPLoanForgivenessStatusResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Forgiveness Request Status", response = SbaPPPLoanForgivenessStatusResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanForgivenessStatusResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaPPPLoanForgivenessStatusResponse> getSbaLoanRequestStatus(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "sba_number", required = false) String sbaNumber,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		SbaPPPLoanForgivenessStatusResponse response = sbaLoanForgivenessService.getLoanStatus(page, sbaNumber);
		
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Get SBA PPP Loan Forgiveness Request By slug", response = SbaPPPLoanForgiveness.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Forgiveness Request Status By slug", response = SbaPPPLoanForgiveness.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanForgiveness.class)})
	@GetMapping(value = "/{slug}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaPPPLoanForgiveness> getSbaLoanRequestBySlug(@PathVariable(value = "slug", required = true) UUID slug,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		SbaPPPLoanForgiveness response = sbaLoanForgivenessService.getLoanStatusBySlug(slug);
		
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Delete SBA PPP Loan Forgiveness Request By Slug")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Delete SBA PPP Loan Forgiveness Request By Slug"),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred")})
	@DeleteMapping(value = "/{slug}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteSbaLoanRequestStatus(@PathVariable(value = "slug", required = true) UUID slug,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Delete Request Received.");
		sbaLoanForgivenessService.deletePPPLoanRequest(slug);
		
		return ResponseEntity.noContent().build();
	}
			
}
