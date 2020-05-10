package Com.Testcases.APIs;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Com.base.BaseTest;
import Com.base.ExcelUtil;

public class DataProviderClass extends BaseTest {
	@Test(dataProvider = "getData")
	public static void TestQuoteStatus(Hashtable<String, String> data) {
		try {
			test = rep.startTest("TestQuoteStatus");
			// System.out.println("in test "+dataRowNum);
			int rowNum = Integer.parseInt(data.get("row"));

			// xls.setCellData("TestData","Result",rowNum,testStartRowNum,"PASS");

			// System.out.println(data.get("Runmode")+"---"+data.get("Col1")+"---"+data.get("Col2")+"--------------"+data.get("row"));

			if (!ExcelUtil.isRunnable("TestQuoteStatus", xls) || data.get("Runmode").equals("N")) {
				// xls.setCellData("TestData","Result",dataRowNum,testStartRowNum,
				// "SKIP");
				// test.log(LogStatus.SKIP,"Skipping the test as runmode is N");
				test.log(LogStatus.FAIL, "Testcase failed");
				// throw new SkipException("Skipping the test as runmode is N");

			}
			// System.out.println("Testcase C");

			// xls.setCellData("TestData","Result",dataRowNum,testStartRowNum,
			// "PASS");

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@DataProvider(name = "Test_Jibli_API_GET")
	public static Object[][] Test_Jibli_API_GET() {

		return ExcelUtil.getTestData(xls, "Test_Jibli_API_GET");
	}

	@DataProvider(name = "Test_Jibli_API_POST")
	public static Object[][] Test_Jibli_API_POST() {

		return ExcelUtil.getTestData(xls, "Test_Jibli_API_POST");
	}
	
	@DataProvider(name = "POST_getGooglePlacesCategory")
	public static Object[][] POST_getGooglePlacesCategory() {

		return ExcelUtil.getTestData(xls, "POST_getGooglePlacesCategory");
	}
	
	@DataProvider(name = "GET_getCountriesConfig")
	public static Object[][] GET_getCountriesConfig() {

		return ExcelUtil.getTestData(xls, "GET_getCountriesConfig");
	}
	
	@DataProvider(name = "POST_createUserProfile")
	public static Object[][] POST_createUserProfile() {

		return ExcelUtil.getTestData(xls, "POST_createUserProfile");
	}
	
	@DataProvider(name = "POST_validateOTPForSignup")
	public static Object[][] POST_validateOTPForSignup() {

		return ExcelUtil.getTestData(xls, "POST_validateOTPForSignup");
	}
	
	@DataProvider(name = "POST_resendOTP")
	public static Object[][] POST_resendOTP() {

		return ExcelUtil.getTestData(xls, "POST_resendOTP");
	}
	
	@DataProvider(name = "POST_validateMobileNumber")
	public static Object[][] POST_validateMobileNumber() {

		return ExcelUtil.getTestData(xls, "POST_validateMobileNumber");
	}
	
	@DataProvider(name = "POST_validateEmailAddress")
	public static Object[][] POST_validateEmailAddress() {

		return ExcelUtil.getTestData(xls, "POST_validateEmailAddress");
	}
	
	@DataProvider(name = "POST_retrieveUserPassword")
	public static Object[][] POST_retrieveUserPassword() {

		return ExcelUtil.getTestData(xls, "POST_retrieveUserPassword");
	}
	
	@DataProvider(name = "POST_updateUserProfile")
	public static Object[][] POST_updateUserProfile() {

		return ExcelUtil.getTestData(xls, "POST_updateUserProfile");
	}
	
	@DataProvider(name = "POST_mobileLoginRequest")
	public static Object[][] POST_mobileLoginRequest() {

		return ExcelUtil.getTestData(xls, "POST_mobileLoginRequest");
	}
	
	@DataProvider(name = "POST_searchUserProfile")
	public static Object[][] POST_searchUserProfile() {

		return ExcelUtil.getTestData(xls, "POST_searchUserProfile");
	}
	
	@DataProvider(name = "POST_createPaymentCardDetails")
	public static Object[][] POST_createPaymentCardDetails() {

		return ExcelUtil.getTestData(xls, "POST_createPaymentCardDetails");
	}
	
	@DataProvider(name = "POST_updatePaymentCardDetails")
	public static Object[][] POST_updatePaymentCardDetails() {

		return ExcelUtil.getTestData(xls, "POST_updatePaymentCardDetails");
	}
	
	@DataProvider(name = "POST_searchPaymentCardDetails")
	public static Object[][] POST_searchPaymentCardDetails() {

		return ExcelUtil.getTestData(xls, "POST_searchPaymentCardDetails");
	}
	
	@DataProvider(name = "POST_createDeliveryLocation")
	public static Object[][] POST_createDeliveryLocation() {

		return ExcelUtil.getTestData(xls, "POST_createDeliveryLocation");
	}
	
	@DataProvider(name = "POST_updateDeliveryLocation")
	public static Object[][] POST_updateDeliveryLocation() {

		return ExcelUtil.getTestData(xls, "POST_updateDeliveryLocation");
	}
	
	@DataProvider(name = "POST_searchDeliveryLocation")
	public static Object[][] POST_searchDeliveryLocation() {

		return ExcelUtil.getTestData(xls, "POST_searchDeliveryLocation");
	}
	
	@DataProvider(name = "POST_createMigrationRequest")
	public static Object[][] POST_createMigrationRequest() {

		return ExcelUtil.getTestData(xls, "POST_createMigrationRequest");
	}
	
	@DataProvider(name = "POST_updateMigrationRequest")
	public static Object[][] POST_updateMigrationRequest() {

		return ExcelUtil.getTestData(xls, "POST_updateMigrationRequest");
	}
	
	@DataProvider(name = "POST_searchMigrationRequest")
	public static Object[][] POST_searchMigrationRequest() {

		return ExcelUtil.getTestData(xls, "POST_searchMigrationRequest");
	}
}
