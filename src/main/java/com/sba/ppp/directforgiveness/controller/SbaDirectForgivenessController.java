package com.sba.ppp.directforgiveness.controller;


import java.io.IOException;

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

import com.sba.ppp.directforgiveness.domain.SbaDirectForgiveness;
import com.sba.ppp.directforgiveness.domain.SbaDirectForgivenessEligibilityResponse;
import com.sba.ppp.directforgiveness.domain.SbaDirectForgivenessRequestStatusResponse;
import com.sba.ppp.directforgiveness.service.SbaDirectForgivenessService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/sba/ppp/loan/direct")
@Api(value = "/sba/ppp/loan/forgiveness/direct", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Execute SBA Direct Forgiveness API"})
@Log4j2
public class SbaDirectForgivenessController {

	@Autowired
	private SbaDirectForgivenessService sbaDirectForgivenessService;

	@ApiOperation(value = "Submit SBA Direct Forgiveness request", response = SbaDirectForgiveness.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Submit SBA Direct Forgiveness request", response = SbaDirectForgiveness.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaDirectForgiveness.class)})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaDirectForgiveness> execute(@RequestBody SbaDirectForgiveness request,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Submit Request Received: {}", request);
		SbaDirectForgiveness response = sbaDirectForgivenessService.execute(request);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Get Status of the Direct Forgiveness request", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get Status of the Direct Forgiveness request", response = String.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = String.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/status/{sba_number}")
	public ResponseEntity<String> getDirectForgivenessStatus(@PathVariable(value = "sba_number", required = true) String sbaNumber,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		String response = sbaDirectForgivenessService.getDirectForgivenessStatus(sbaNumber);

		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Get SBA Direct Forgiveness Request with Status", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA Direct Forgiveness Request with Status", response = String.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = String.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SbaDirectForgivenessRequestStatusResponse> getDirectForgivenessRequest(@RequestParam(value = "page", required = false) Integer page,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		SbaDirectForgivenessRequestStatusResponse response = sbaDirectForgivenessService.getDirectForgivenessRequest(page);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Get Eligibility of the forgiveness request", response = SbaDirectForgivenessEligibilityResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get Eligibility of the forgiveness request", response = SbaDirectForgivenessEligibilityResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaDirectForgivenessEligibilityResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/eligibility")
	public ResponseEntity<SbaDirectForgivenessEligibilityResponse> getDirectForgivenessEligibility(@RequestParam(value = "sba_number", required = true) String sbaNumber,
			@RequestParam(value = "tin", required = false) String tin,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Get Request Received.");
		SbaDirectForgivenessEligibilityResponse response = sbaDirectForgivenessService.getDirectForgivenessEligibility(sbaNumber, tin);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Delete Direct Forgiveness Request By sba number")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Delete Direct Forgiveness Request By sba number"),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred")})
	@DeleteMapping(value = "/{sba_number}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteDirectForgivenessRequest(@PathVariable(value = "sba_number", required = true) String sbaNumber,
			@RequestHeader HttpHeaders headers) throws IOException {
		log.info("Delete Request Received.");
		sbaDirectForgivenessService.deleteDirectForgivenessRequest(sbaNumber);

		return ResponseEntity.noContent().build();
	}

}
