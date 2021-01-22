package com.sba.ppp.loanorigination.controller;


import java.io.IOException;
import java.util.List;
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

import com.sba.ppp.loanorigination.domain.ETranPPPLoanValidationResponse;
import com.sba.ppp.loanorigination.domain.FranchiseOrNaicsCodesResponse;
import com.sba.ppp.loanorigination.domain.GetEidlLoanValidationResponse;
import com.sba.ppp.loanorigination.domain.GetValidatedAndStandardizedAddressResponse;
import com.sba.ppp.loanorigination.domain.LoanRequest;
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
	//	log.info("Submit Request Received: {}", request);
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
	//	log.info("Get Request Received.");
		SbaPPPLoanOriginationStatusResponse response = pppLoanOriginationService.getLoanStatus(page, sbaNumber);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Get SBA PPP Loan Origination Request By slug", response = LoanRequest.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Origination Request Status By slug", response = LoanRequest.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = LoanRequest.class)})
	@GetMapping(value = "/{slug}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoanRequest> getSbaLoanRequestBySlug(@PathVariable(value = "slug", required = true) UUID slug,
			@RequestHeader HttpHeaders headers) throws IOException {
	//	log.info("Get Request Received.");
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
	//	log.info("Delete Request Received.");
		pppLoanOriginationService.deletePPPLoanRequest(slug);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Get NAICS details using NAICS code or Industry description", response = SbaPPPLoanOriginationStatusResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get NAICS details using NAICS code or Industry description Request Status", response = SbaPPPLoanOriginationStatusResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanOriginationStatusResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/api/naics")
	public ResponseEntity<FranchiseOrNaicsCodesResponse> getNaicsCodes(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "description", required = false) String description,
			@RequestHeader HttpHeaders headers) throws IOException {
	//	log.info("Get Request Received.");
		FranchiseOrNaicsCodesResponse response = pppLoanOriginationService.getNaicsCode(page, code,description);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Get SBA PPP Loan Origination Request Status", response = SbaPPPLoanOriginationStatusResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get SBA PPP Loan Origination Request Status", response = SbaPPPLoanOriginationStatusResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = SbaPPPLoanOriginationStatusResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/api/franchise")
	public ResponseEntity<FranchiseOrNaicsCodesResponse> getFranchiseCodes(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "description", required = false) String description,
			@RequestHeader HttpHeaders headers) throws IOException {
	//	log.info("Get Request Received.");
		FranchiseOrNaicsCodesResponse response = pppLoanOriginationService.getFranchiseCode(page, code, description);

		return ResponseEntity.ok(response);
	}


	@ApiOperation(value = "Get Etran ppp validation response", response = ETranPPPLoanValidationResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get Etran ppp validation Request Status", response = ETranPPPLoanValidationResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = ETranPPPLoanValidationResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/api/etran_ppp_validation")
	public ResponseEntity<ETranPPPLoanValidationResponse> getETranPPPLoanValidationResponse(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "sba_number", required = false) String sba_number,
			@RequestHeader HttpHeaders headers) throws IOException {
	//	log.info("Get Request Received.");
		ETranPPPLoanValidationResponse response = pppLoanOriginationService.getETranPPPLoanValidationResponse(page, sba_number);

		return ResponseEntity.ok(response);
	}

	
	
	@ApiOperation(value = "Get Validated and Standardized address", response = GetValidatedAndStandardizedAddressResponse[].class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get Validated and Standardized address Request", response = GetValidatedAndStandardizedAddressResponse[].class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = GetValidatedAndStandardizedAddressResponse[].class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/address_validation")
	public ResponseEntity<GetValidatedAndStandardizedAddressResponse[]> getValidateAndStandardizedAddressResponse(
			@RequestParam(value = "address_1", required = true) String address_1,
			@RequestParam(value = "address_2", required = false) String address_2,
			@RequestParam(value = "city", required = true) String city,
			@RequestParam(value = "state", required = true) String state,
			@RequestParam(value = "zip_code", required = true) String zip_code,

			@RequestHeader HttpHeaders headers) throws IOException {
	//	log.info("Get Request Received.");
		GetValidatedAndStandardizedAddressResponse[] response = pppLoanOriginationService.getValidateAndStandardizedAddressResponse(address_1,address_2,city,state,zip_code);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Get EIDL Loan Validation", response = GetEidlLoanValidationResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get EIDL Loan Validation", response = GetEidlLoanValidationResponse.class),
			@ApiResponse(code = 400, message = "Unauthorized Error"),
			@ApiResponse(code = 500, message = "Internal Error Occurred", response = GetEidlLoanValidationResponse.class)})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/api/etran_eidl_loan_validation")
	public ResponseEntity<GetEidlLoanValidationResponse> getEidlLoanValidationResp(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "eidl_loan_number", required = false) String eidl_loan_number,

			@RequestHeader HttpHeaders headers) throws IOException {
	//	log.info("Get Request Received.");
		GetEidlLoanValidationResponse response = pppLoanOriginationService.getEidlLoanValidationResponse(page, eidl_loan_number);

		return ResponseEntity.ok(response);
	}

}
