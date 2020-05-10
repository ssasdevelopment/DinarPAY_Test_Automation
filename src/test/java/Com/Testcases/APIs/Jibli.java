package Com.Testcases.APIs;

import java.util.Date;
import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Com.base.BaseClassJibli;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Jibli extends BaseClassJibli {

	int i=0;

	@BeforeTest
	public void beforeTest() {

		i+=1;
	}

	@AfterClass
	public void afterMethod() throws InterruptedException {

		Thread.sleep(500);
		TestAfterMethod();
	}

	@Test(dataProvider = "POST_createUserProfile", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_createUserProfile(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_createUserProfile";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
//			isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("userProfileId", data.get("userProfileId"));
			requestParams.put("merchantCode", data.get("merchantCode"));
			requestParams.put("customerCode", data.get("customerCode"));
			requestParams.put("isCustomer", data.get("isCustomer"));
			requestParams.put("isMerchant", data.get("isMerchant"));
			requestParams.put("loginUserName", data.get("loginUserName"));
			requestParams.put("loginPassword", data.get("loginPassword"));
			requestParams.put("firstName", data.get("firstName"));
			requestParams.put("lastName", data.get("lastName"));
			requestParams.put("gender", data.get("gender"));
			requestParams.put("countryCode", data.get("countryCode"));
			requestParams.put("countryId", data.get("countryId"));
			requestParams.put("mobileNumberCountryCode", data.get("mobileNumberCountryCode"));
			requestParams.put("mobileNumber", data.get("mobileNumber"));
			requestParams.put("emailAddress", data.get("emailAddress"));
			requestParams.put("faceBookAddress", data.get("faceBookAddress"));
			requestParams.put("twitterAddress", data.get("twitterAddress"));
			requestParams.put("lastLoginMode", data.get("lastLoginMode"));
			requestParams.put("agreedTermsOfUsage", data.get("agreedTermsOfUsage"));
			requestParams.put("additionalRemarks", data.get("additionalRemarks"));
			requestParams.put("profileImage1", data.get("profileImage1"));
			requestParams.put("profileImage2", data.get("profileImage2"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			Thread.sleep(100);
			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			Thread.sleep(100);
			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			Thread.sleep(100);
			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				Thread.sleep(100);
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				Thread.sleep(100);
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}

	@Test(dataProvider = "POST_validateOTPForSignup", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_validateOTPForSignup(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_validateOTPForSignup";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("transactionId",data.get("transactionId"));
			requestParams.put("requestType",data.get("requestType"));
			requestParams.put("verificationOTPCode",data.get("verificationOTPCode"));
			requestParams.put("isResendOTP",data.get("isResendOTP"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}

	@Test(dataProvider = "POST_resendOTP", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_resendOTP(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_resendOTP";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("transactionId",data.get("transactionId"));
			requestParams.put("requestType",data.get("requestType"));
			requestParams.put("verificationOTPCode",data.get("verificationOTPCode"));
			requestParams.put("isResendOTP",data.get("isResendOTP"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_validateMobileNumber", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_validateMobileNumber(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_validateMobileNumber";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode"));
			requestParams.put("mobileNumberCountryCode",data.get("mobileNumberCountryCode"));
			requestParams.put("mobileNumber",data.get("mobileNumber"));
			requestParams.put("emailAddress",data.get("emailAddress"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_validateEmailAddress", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_validateEmailAddress(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_validateEmailAddress";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("mobileNumberCountryCode",data.get("mobileNumberCountryCode"));
			requestParams.put("mobileNumber",data.get("mobileNumber"));
			requestParams.put("emailAddress",data.get("emailAddress"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_retrieveUserPassword", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_retrieveUserPassword(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_retrieveUserPassword";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("mobileNumberCountryCode",data.get("mobileNumberCountryCode"));
			requestParams.put("mobileNumber",data.get("mobileNumber"));
			requestParams.put("emailAddress",data.get("emailAddress"));
			requestParams.put("isForgetPassword",data.get("isForgetPassword"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_updateUserProfile", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_updateUserProfile(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_updateUserProfile";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("userProfileId", data.get("userProfileId"));
			requestParams.put("merchantCode", data.get("merchantCode"));
			requestParams.put("customerCode", data.get("customerCode"));
			requestParams.put("isCustomer", data.get("isCustomer"));
			requestParams.put("isMerchant", data.get("isMerchant"));
			requestParams.put("loginUserName", data.get("loginUserName"));
			requestParams.put("oldLoginPassword", data.get("oldLoginPassword"));
			requestParams.put("newLoginPassword", data.get("newLoginPassword"));
			requestParams.put("firstName", data.get("firstName"));
			requestParams.put("lastName", data.get("lastName"));
			requestParams.put("gender", data.get("gender"));
			requestParams.put("countryCode", data.get("countryCode"));
			requestParams.put("countryId", data.get("countryId"));
			requestParams.put("mobileNumberCountryCode", data.get("mobileNumberCountryCode"));
			requestParams.put("mobileNumber", data.get("mobileNumber"));
			requestParams.put("emailAddress", data.get("emailAddress"));
			requestParams.put("faceBookAddress", data.get("faceBookAddress"));
			requestParams.put("twitterAddress", data.get("twitterAddress"));
			requestParams.put("lastLoginMode", data.get("lastLoginMode"));
			requestParams.put("profileImage1", data.get("profileImage1"));
			requestParams.put("profileImage2", data.get("profileImage2"));
			requestParams.put("isDeleteUserProfile", data.get("isDeleteUserProfile"));
			requestParams.put("statusCode", data.get("statusCode"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_mobileLoginRequest", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_mobileLoginRequest(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_mobileLoginRequest";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("isCustomer", data.get("isCustomer"));
			requestParams.put("isMerchant", data.get("isMerchant"));
			requestParams.put("loginUserName", data.get("loginUserName"));
			requestParams.put("loginPassword", data.get("loginPassword"));
			requestParams.put("mobileNumberCountryCode", data.get("mobileNumberCountryCode"));
			requestParams.put("mobileNumber", data.get("mobileNumber"));
			requestParams.put("emailAddress", data.get("emailAddress"));
			requestParams.put("lastLoginMode", data.get("lastLoginMode"));
			requestParams.put("faceBookId", data.get("faceBookId"));
			requestParams.put("twitterId", data.get("twitterId"));


			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_searchUserProfile", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_searchUserProfile(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_searchUserProfile";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("mobileNumberCountryCode",data.get("mobileNumberCountryCode"));
			requestParams.put("mobileNumber",data.get("mobileNumber"));
			requestParams.put("emailAddress",data.get("emailAddress"));
			requestParams.put("userProfileId",data.get("userProfileId"));
			requestParams.put("merchantCode",data.get("merchantCode"));
			requestParams.put("customerCode",data.get("customerCode"));
			requestParams.put("firstName",data.get("firstName"));
			requestParams.put("lastName",data.get("lastName"));
			requestParams.put("countryCode",data.get("countryCode"));
			requestParams.put("statusCode",data.get("statusCode"));
			requestParams.put("seeAllStaffDetails",data.get("seeAllStaffDetails"));
			requestParams.put("pageNumber",data.get("pageNumber"));
			requestParams.put("limit",data.get("limit"));


			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_createPaymentCardDetails", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_createPaymentCardDetails(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_createPaymentCardDetails";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("paymentCardId",data.get("paymentCardId")); 
			requestParams.put("userProfileId",data.get("userProfileId")); 
			requestParams.put("customerCode",data.get("customerCode")); 
			requestParams.put("additionalRemarks",data.get("additionalRemarks")); 
			requestParams.put("cardNumber",data.get("cardNumber")); 
			requestParams.put("cardType",data.get("cardType")); 
			requestParams.put("cvvCode",data.get("cvvCode")); 
			requestParams.put("expiryMonth",data.get("expiryMonth")); 
			requestParams.put("expiryYear",data.get("expiryYear")); 
			requestParams.put("nameOnTheCard",data.get("nameOnTheCard")); 
			requestParams.put("otherInformation",data.get("otherInformation")); 
			requestParams.put("statusCode",data.get("statusCode")); 
			requestParams.put("isDeleteCardRequest",data.get("isDeleteCardRequest")); 

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_updatePaymentCardDetails", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_updatePaymentCardDetails(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_updatePaymentCardDetails";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("paymentCardId",data.get("paymentCardId")); 
			requestParams.put("userProfileId",data.get("userProfileId")); 
			requestParams.put("customerCode",data.get("customerCode")); 
			requestParams.put("additionalRemarks",data.get("additionalRemarks")); 
			requestParams.put("cardNumber",data.get("cardNumber")); 
			requestParams.put("cardType",data.get("cardType")); 
			requestParams.put("cvvCode",data.get("cvvCode")); 
			requestParams.put("expiryMonth",data.get("expiryMonth")); 
			requestParams.put("expiryYear",data.get("expiryYear")); 
			requestParams.put("nameOnTheCard",data.get("nameOnTheCard")); 
			requestParams.put("otherInformation",data.get("otherInformation")); 
			requestParams.put("statusCode",data.get("statusCode")); 
			requestParams.put("isDeleteCardRequest",data.get("isDeleteCardRequest")); 

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_searchPaymentCardDetails", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_searchPaymentCardDetails(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_searchPaymentCardDetails";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("paymentCardId",data.get("paymentCardId")); 
			requestParams.put("userProfileId",data.get("userProfileId")); 
			requestParams.put("customerCode",data.get("customerCode")); 
			requestParams.put("cardType",data.get("cardType")); 
			requestParams.put("latitude",data.get("latitude")); 
			requestParams.put("longitude",data.get("longitude")); 
			requestParams.put("statusCode",data.get("statusCode")); 
			requestParams.put("seeAllCardDetails",data.get("seeAllCardDetails"));
			requestParams.put("pageNumber",data.get("pageNumber")); 
			requestParams.put("limit",data.get("limit")); 

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_createDeliveryLocation", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_createDeliveryLocation(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_createDeliveryLocation";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("deliveryLocationId",data.get("deliveryLocationId"));
			requestParams.put("userProfileId",data.get("userProfileId"));
			requestParams.put("customerCode",data.get("customerCode"));
			requestParams.put("addressName",data.get("addressName"));
			requestParams.put("deliveryAddressLine1",data.get("deliveryAddressLine1"));
			requestParams.put("deliveryAddressLine2",data.get("deliveryAddressLine2"));
			requestParams.put("cityName",data.get("cityName"));
			requestParams.put("poBox",data.get("poBox"));
			requestParams.put("contactPersonName",data.get("contactPersonName"));
			requestParams.put("contactMobileNumberCode",data.get("contactMobileNumberCode"));
			requestParams.put("contactMobileNumber",data.get("contactMobileNumber"));
			requestParams.put("alternativeMobileNumberCode",data.get("alternativeMobileNumberCode"));
			requestParams.put("alternativeMobileNumber",data.get("alternativeMobileNumber"));
			requestParams.put("deliveryTimingsI",data.get("deliveryTimingsI"));
			requestParams.put("deliveryTimingsII",data.get("deliveryTimingsII"));
			requestParams.put("latitude",data.get("latitude"));
			requestParams.put("longitude",data.get("longitude"));
			requestParams.put("otherInformation",data.get("otherInformation"));
			requestParams.put("additionalRemarks",data.get("additionalRemarks"));
			requestParams.put("statusCode",data.get("statusCode"));
			requestParams.put("isDeleteDeliveryLocationRequest",data.get("isDeleteDeliveryLocationRequest"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_updateDeliveryLocation", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_updateDeliveryLocation(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_updateDeliveryLocation";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("deliveryLocationId",data.get("deliveryLocationId"));
			requestParams.put("userProfileId",data.get("userProfileId"));
			requestParams.put("customerCode",data.get("customerCode"));
			requestParams.put("addressName",data.get("addressName"));
			requestParams.put("deliveryAddressLine1",data.get("deliveryAddressLine1"));
			requestParams.put("deliveryAddressLine2",data.get("deliveryAddressLine2"));
			requestParams.put("cityName",data.get("cityName"));
			requestParams.put("poBox",data.get("poBox"));
			requestParams.put("contactPersonName",data.get("contactPersonName"));
			requestParams.put("contactMobileNumberCode",data.get("contactMobileNumberCode"));
			requestParams.put("contactMobileNumber",data.get("contactMobileNumber"));
			requestParams.put("alternativeMobileNumberCode",data.get("alternativeMobileNumberCode"));
			requestParams.put("alternativeMobileNumber",data.get("alternativeMobileNumber"));
			requestParams.put("deliveryTimingsI",data.get("deliveryTimingsI"));
			requestParams.put("deliveryTimingsII",data.get("deliveryTimingsII"));
			requestParams.put("latitude",data.get("latitude"));
			requestParams.put("longitude",data.get("longitude"));
			requestParams.put("otherInformation",data.get("otherInformation"));
			requestParams.put("additionalRemarks",data.get("additionalRemarks"));
			requestParams.put("statusCode",data.get("statusCode"));
			requestParams.put("isDeleteDeliveryLocationRequest",data.get("isDeleteDeliveryLocationRequest"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_searchDeliveryLocation", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_searchDeliveryLocation(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_searchDeliveryLocation";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("deliveryLocationId",data.get("deliveryLocationId"));
			requestParams.put("userProfileId",data.get("userProfileId"));
			requestParams.put("customerCode",data.get("customerCode"));
			requestParams.put("addressName",data.get("addressName"));
			requestParams.put("cityName",data.get("cityName"));
			requestParams.put("latitude",data.get("latitude"));
			requestParams.put("longitude",data.get("longitude"));
			requestParams.put("statusCode",data.get("statusCode"));
			requestParams.put("seeAllDeliveryLocations",data.get("seeAllDeliveryLocations"));
			requestParams.put("pageNumber",data.get("pageNumber"));
			requestParams.put("limit",data.get("limit"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_createMigrationRequest", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_createMigrationRequest(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_createMigrationRequest";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("migrationRequestd",data.get("migrationRequestd")); 
			requestParams.put("userProfileId",data.get("userProfileId"));
			requestParams.put("customerCode",data.get("customerCode"));
			requestParams.put("requestDate",data.get("requestDate"));
			requestParams.put("approvalDate",data.get("approvalDate"));
			requestParams.put("approvedBy",data.get("approvedBy"));
			requestParams.put("approverComments",data.get("approverComments"));
			requestParams.put("paymentDetails",data.get("paymentDetails"));
			requestParams.put("attachment1",data.get("attachment1"));
			requestParams.put("additionalRemarks",data.get("additionalRemarks"));
			requestParams.put("statusCode",data.get("statusCode"));
			requestParams.put("isDeleteRequest",data.get("isDeleteRequest"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_updateMigrationRequest", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_updateMigrationRequest(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_updateMigrationRequest";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("migrationRequestd",data.get("migrationRequestd")); 
			requestParams.put("userProfileId",data.get("userProfileId"));
			requestParams.put("customerCode",data.get("customerCode"));
			requestParams.put("requestDate",data.get("requestDate"));
			requestParams.put("approvalDate",data.get("approvalDate"));
			requestParams.put("approvedBy",data.get("approvedBy"));
			requestParams.put("approverComments",data.get("approverComments"));
			requestParams.put("paymentDetails",data.get("paymentDetails"));
			requestParams.put("attachment1",data.get("attachment1"));
			requestParams.put("additionalRemarks",data.get("additionalRemarks"));
			requestParams.put("statusCode",data.get("statusCode"));
			requestParams.put("isDeleteRequest",data.get("isDeleteRequest"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_searchMigrationRequest", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_searchMigrationRequest(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_searchMigrationRequest";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("migrationRequestd",data.get("migrationRequestd")); 
			requestParams.put("userProfileId",data.get("userProfileId"));
			requestParams.put("customerCode",data.get("customerCode"));
			requestParams.put("requestStartDate",data.get("requestStartDate"));
			requestParams.put("requestEndDate",data.get("requestEndDate"));
			requestParams.put("statusCode",data.get("statusCode"));
			requestParams.put("seeAllMigrationRequest",data.get("seeAllMigrationRequest"));
			requestParams.put("pageNumber",data.get("pageNumber"));
			requestParams.put("limit",data.get("limit"));

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "POST_getGooglePlacesCategory", dataProviderClass = DataProviderClass.class, priority = 0)
	public void POST_getGooglePlacesCategory(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "POST_getGooglePlacesCategory";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			RequestSpecification request = RestAssured.given();

			// JSONObject is a class that represents a Simple JSON.
			// We can add Key - Value pairs using the put method
			JSONObject requestParams = new JSONObject();

			requestParams.put("accessUserName",data.get("accessUserName")); 
			requestParams.put("accessPassword",data.get("accessPassword")); 
			requestParams.put("accessSmartSecurityKey",data.get("accessSmartSecurityKey")); 
			requestParams.put("requestChannel",data.get("requestChannel")); 
			requestParams.put("languageCode",data.get("languageCode")); 
			requestParams.put("placesId",data.get("placesId")); 
			requestParams.put("placesCode",data.get("placesCode")); 
			requestParams.put("constantValue",data.get("constantValue")); 
			requestParams.put("placesDescription",data.get("placesDescription")); 
			requestParams.put("placesDescriptionAR",data.get("placesDescriptionAR")); 
			requestParams.put("placesDescriptionFR",data.get("placesDescriptionFR")); 

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");

			// Add the Json to the body of the request
			request.body(requestParams.toJSONString());

			// Post the request and check the response
			Response response = request.post(resource);

			test.log(LogStatus.PASS, "Request Submitted successfully.");
			test.log(LogStatus.PASS, "Request Body is: " + requestParams);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));

		}

		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}


	@Test(dataProvider = "GET_getCountriesConfig", dataProviderClass = DataProviderClass.class, priority = 1 )
	public void GET_getCountriesConfig(Hashtable<String, String> data) {

		try {

			if(i==1)
				testCaseName = "GET_getCountriesConfig";
			Date d = new Date();

			String TestCaseName = data.get("Environment") + "_" + data.get("TestcaseName") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("Environment") + "_" + data.get("TestcaseName");
			String TestCaseComDesc = data.get("Environment") + "_" + data.get("TestcaseName");

			StartReport(TestCaseDesc, TestCaseComDesc);
			//		isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			System.out.println("Custom Report Name: "+PrintValue);

			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = data.get("BaseURI");
			String resource = data.get("Resource");

			test.log(LogStatus.PASS, data.get("BaseURI")+resource);

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			RequestSpecification httpRequest = RestAssured.given();

			test.log(LogStatus.PASS, "Request Submitted successfully.");

			// Make a request to the server by specifying the method Type and the method URL.
			// This will return the Response from the server. Store the response in a variable.
			Response response = httpRequest.request(Method.GET, resource);

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();

			if(response.statusCode() == Integer.parseInt(data.get("ExpResCode"))){
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
				test.log(LogStatus.PASS, "Request Completed successfully.");
				test.log(LogStatus.PASS, "Response Status Code is: " + response.getStatusCode());
				test.log(LogStatus.PASS, "Response Body is: " + responseBody);
			}
			else{
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
				test.log(LogStatus.FAIL, "Request not completed");
				test.log(LogStatus.FAIL, "Response Status Code is: " + response.getStatusCode() + "  :: Expected Response Code is: " + Integer.parseInt(data.get("ExpResCode")));
				test.log(LogStatus.FAIL, "Response Body is: " + responseBody);
			}

			Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("ExpResCode")));


		}
		catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}

}
