package com.sba.ppp.loanforgiveness.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sba.ppp.loanforgiveness.domain.MessageReply;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanForgiveness;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanForgivenessMessage;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanForgivenessStatusResponse;
import com.sba.ppp.loanforgiveness.domain.SbaPPPLoanMessagesResponse;
import com.sba.ppp.loanforgiveness.service.SbaLoanForgivenessMessageService;
import com.sba.ppp.loanforgiveness.service.SbaLoanForgivenessService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/sba/ppp/loan/forgiveness")
@Api(value = "/sba/ppp/loan/forgiveness", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Execute SBA PPP Loan Forgiveness Messages API"})
@Log4j2
public class SbaLoanForgivenessMessageController {
	
	@Autowired
	private SbaLoanForgivenessMessageService sbaLoanForgivenessMessageService;

	@ApiOperation(value = "Update SBA PPP Loan Forgiveness Message reply", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Update SBA PPP Loan Forgiveness Message reply", response = String.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = String.class)})
	@PutMapping(value = "/message/reply", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody MessageReply request,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Submit Request Received: {}", request);
		String response = sbaLoanForgivenessMessageService.updateSbaLoanMessageReply(request);
		
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Get SBA PPP Loan Forgiveness Messages", response = SbaPPPLoanMessagesResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Forgiveness Request Messages", response = SbaPPPLoanMessagesResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanMessagesResponse.class)})
	@GetMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaPPPLoanMessagesResponse> getSbaLoanMessages(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "is_complete", required = false) boolean isComplete,
			@RequestParam(value = "sba_number", required = false) String sbaNumber,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		SbaPPPLoanMessagesResponse response = sbaLoanForgivenessMessageService.getSbaLoanMessages(page, sbaNumber, isComplete);
		
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Get SBA PPP Loan Forgiveness Messages By slug", response = SbaPPPLoanMessagesResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Forgiveness Request Messages By slug", response = SbaPPPLoanMessagesResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanMessagesResponse.class)})
	@GetMapping(value = "/messages/{slug}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaPPPLoanMessagesResponse> getLoanMessagesBySlug(@PathVariable(value = "slug", required = true) UUID slug,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		SbaPPPLoanMessagesResponse response = sbaLoanForgivenessMessageService.getLoanMessagesBySlug(slug);
		
		return ResponseEntity.ok(response);
	}
			
}
