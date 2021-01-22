# Java Client Code Usage

Please refer [API Dictionary URL](https://ussbaforgiveness.github.io/API-Dictionary.html) for any clarifications related to API request/response attributes.

Java Client code is provided to make it easier to integrate to SBA APIs.

## Cloning the repository

Clone repository using SourceTree or Git Bash.

`$git clone https://github.com/UsSbaPPP/sba-java-client.git`

## Setting up API Token and Vendor Key

The API-Token and Vendor-key is required for all the operations to be performed in the sandbox/production environment.
Update the below properties in  `/src/main/resources/sandbox/application.yml` with the api-token and Vendor-key 
```  
  api-token: {API_KEY}
  vendor-key: {VENDOR_KEY}
  ....
``` 

## Installation

This is a maven project, so for building the project, browse to the parent directory /sba-java-client-master and run the below maven command for building the project
```
mvn clean install
```

## For running the project
For running the project, just execute the java application jar
```
java -jar sba-java-client-master/target/sba-ppp-loan-origination-1.0.jar
```

The project is a sample Spring Boot web application. This application runs on port `9091` and URL: `http://localhost:9091/sandbox`.
The complete set of URLs for different operation is part of the `application.yml` file.


**Usage #1:** Use Services provided in the code to eliminate the complexity of creating Rest Clients to integrate with SBA API's
> src/main/java/com/sba/ppp/loanorigination/service
		
**Usage #2:** Use domain objects to include in your code to make Rest API calls.
> src/main/java/com/sba/ppp/loanorigination/domain
	
**Usage #3:** Use complete repository as a Spring boot Application for your integration.

## Example steps for a Origination request
	
## Step1: Submit Loan Origination Request

This example is part of a sample [Use Case 1]
(https://ussbappp.github.io/API-Dictionary.html#3-get-specific-ppp-loan-request-details)

## POST API Call using SbaLoanOriginationService Service
	
'SbaLoanOriginationService.execute(LoanRequest request)'
		
You need to populate LoanRequest object with all the information required for submission.

Please refer to [Create Origination Request API](https://ussbappp.github.io/API-Dictionary.html#3-get-specific-ppp-loan-request-details)

### Step 2: Get Details of ALL PPP Loan Requests ###

This example is part of a sample [Use Case 4](https://ussbappp.github.io/API-Dictionary.html#2-get-details-of-all-ppp-loan-requests)

### To get all PPP Loan details make a GET API Call to ###

`SbaLoanOriginationService.getLoanStatus(Integer page, String sbaNumber)`

reqParams -> Please refer to [Get Details of ALL PPP Loan Requests](https://ussbaforgiveness.github.io/API-Dictionary.html)
	  
### Step 3: Get specific PPP Loan Request Details ###

This example is part of a sample (https://ussbappp.github.io/API-Dictionary.html#3-get-specific-ppp-loan-request-details)
  
This is a POST API call to get PPP Loan Request by SLUG.
`SbaLoanOriginationService.getLoanStatusBySlug(UUID slug)`

Please refer to [Upload Forgiveness Document API](https://ussbaforgiveness.github.io/API-Dictionary.html#3-upload-forgiveness-documents)

### Step 4: Delete PPP Loan Request ###

This example is part of a sample (https://ussbappp.github.io/API-Dictionary.html#4-delete-ppp-loan-request)

### This is a DELETE API Call to delete origination application created in Step 1. ###
		
		`SbaLoanOriginationService.deletePPPLoanRequest(UUID slug) `
		

### Step 5: Get ALL NAICS Codes ###

This example is part of a sample (https://ussbappp.github.io/API-Dictionary.html#5-get-all-naics-codes)

### This is to get details for all NAICS. ###
		
		`SbaLoanOriginationService.getNaicsCode(Integer page, String code,String description)`

### Step 6: Get NAICS details using NAICS code or Industry description ###

This example is part of a sample (https://ussbappp.github.io/API-Dictionary.html#6-get-naics-details-using-naics-code-or-industry-description)

### This is to get details based on specific naics code or descirption. ###
		
		`SbaLoanOriginationService.getNaicsCode(Integer page, String code,String description)`

### Step 7: Get ALL Franchise Details ###

This example is part of a sample (https://ussbappp.github.io/API-Dictionary.html#7-get-all-franchise-details)

### This is to get details for all Francise ###
		
		`SbaLoanOriginationService.getFranchiseCode(Integer page, String code, String  description)`

### Step 8: Get Franchise Details using Franchise code or name ###

This example is part of a sample (https://ussbappp.github.io/API-Dictionary.html#8-get-franchise-details-using-franchise-code-or-name)

### This is to get details for franchise based on franchise code or franchise description###
		
		`SbaLoanOriginationService.getFranchiseCode(Integer page, String code, String  description)`

### Step 9: Get eTran PPP Loan Validation ###

This example is part of a sample (https://ussbappp.github.io/API-Dictionary.html#9-get-etran-ppp-loan-validation)

### This API validates the First Draw eTran PPP Loan ###
		
		`SbaLoanOriginationService.getETranPPPLoanValidationResponse(Integer page, String sba_number)`

### Step 10: Get Validated and Standardized Address ###

This example is part of a sample (https://ussbappp.github.io/API-Dictionary.html#10-get-validated-and-standardized-address)

### This API is used to validate and get standardized addresses along with Zip+4. ###
		
		`SbaLoanOriginationService.getValidateAndStandardizedAddressResponse(String address_1,String address_2,String city,String state,String zip_code)`

### Step 11: Get EIDL Loan Validation ###

This example is part of a sample (https://ussbappp.github.io/API-Dictionary.html#11-get-eidl-loan-validation)

### This API validates EIDL Loan number ###
		
		`SbaLoanOriginationService.getEidlLoanValidationResponse(Integer page, String eidl_loan_number)`
		