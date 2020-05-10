package Com.Testcases.Merchant;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Com.base.BaseTest;
import Com.base.CustomRandom;

public class CreateMerchant extends BaseTest {
	int i = 0;

	@BeforeSuite
	public void setProperties(){
		propertiesFile = "CreateMerchant";
	}
	
	@BeforeMethod
	public void Testbefore() throws IOException, InterruptedException, AWTException {

		if (i == 0) {
			TestBeforeMethod();
		}
		i = i + 1;
	}

	@AfterMethod
	public void afterMethod() {

		if (i == 0 || i == RowsCount()) {
			TestAfterMethod();
		}
	}

	@Test(dataProvider = "Test_CreateMerchant", dataProviderClass = DataProviderClass.class)
	public void Test_CreateMerchant(Hashtable<String, String> data)	throws InterruptedException, SQLException, ClassNotFoundException, ParseException {
		try {
			ImplicitWait();
			testCaseName = "Test_CreateMerchant";
			Date d = new Date();

			String TestCaseName = data.get("TestcaseName") + "_" + data.get("Environment") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("TestcaseName") + "_" + data.get("Environment");
			String TestCaseComDesc = data.get("TestcaseName") + "_" + data.get("Environment");

			StartReport(TestCaseDesc, TestCaseComDesc);
			isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			// System.out.println("System user directory path:
			// "+System.getProperty("user.dir"));

			typebyJavascriptExecutor("admin_username", data.get("Username"));
			typebyJavascriptExecutor("admin_password", data.get("Password"));

			test.log(LogStatus.PASS, "User is on the login screen");
			CaptureScreen();

			// Thread.sleep(20000);
			click("admin_loginBtn");

			test.log(LogStatus.PASS, "User is on the OTP screen");
			CaptureScreen();

			waitForElementToBeClickable("ManageMerchantsLink");
			Thread.sleep(500);

			test.log(LogStatus.PASS, "User is on the Dashboard screen");
			CaptureScreen();

			click("ManageMerchantsLink");
			Thread.sleep(500);

			test.log(LogStatus.PASS, "User is on the Manage Merchant screen");
			CaptureScreen();

			click("NewMerchantLink");
			Thread.sleep(500);

//			String containsEnd = "')]";
			String equalEnd = "]";

			/*******************************************************/
			/**************** Create New Merchant ******************/
			/******************** Profile Tab **********************/
			/****************** Merchant Profile *******************/
			/*******************************************************/
			setValue("CMP_LegalNameOfBusiness", data.get("Legal_Name_of_Business"));
			setValue("CMP_BrandingName", data.get("Trading_Branding_Name"));

			if (data.get("CMP_BrandingNameSameAsLegalName").equals("FALSE"))
				click("CMP_BrandingNameSameAsLegalName");

			setValue("CMP_NameofStoreShop", data.get("Name_of_Store_Shop"));

			if (data.get("CMP_StoreNameSameAsLegalName").equals("FALSE"))
				click("CMP_StoreNameSameAsLegalName");

			if (data.get("CMP_Branch").equals("Yes")) {
				click("CMP_Branch_Yes");
			} else

			Thread.sleep(200);
				// click("CMP_MerchantType_DDL");
				// Thread.sleep(500);
				// String Merchantlist =
				// "Xpath@@//ul[@id='select2-merchantType-results']/li[contains(text(),'";
				// SelectContainsDropdown(Merchantlist, containsEnd,
				// data.get("CMP_Merchant_Type"),
				// SizeofList("CMP_MerchantType_List"));
				// Thread.sleep(500);

			click("CMP_MerchantType_DDL");
			Thread.sleep(200);
			String Merchantlist = "Xpath@@//ul[@id='select2-merchantType-results']/li[";
			SelectDropdown(Merchantlist, data.get("CMP_Merchant_Type"), equalEnd, SizeofList("CMP_MerchantType_List"));
			Thread.sleep(200);

			setValue("CMP_Addnotes", data.get("CMP_Add_Notes"));

			switch (data.get("CMP_Status")) {
			case "Active":
				click("CMP_Status_Active");
				break;
			case "Blocked":
				click("CMP_Status_Blocked");
				break;
			case "Expired":
				click("CMP_Status_Expired");
				break;
			case "Other":
				click("CMP_Status_Other");
				break;
			default:
			}

			setValue("CMP_ShopAddress", data.get("CMP_Shop_Address"));
			setValue("CMP_City", data.get("CMP_City"));
			setValue("CMP_ShopLocation", data.get("CMP_Shop_Location"));
			setValue("CMP_LegalLicenseNumber", data.get("CMP_Legal_License_Number"));

			click("CMP_MerchantCategory_DDL");
			Thread.sleep(200);
			String MerchantCategory = "Xpath@@//ul[@id='select2-merchantCategoryCode-results']/li[";
			SelectDropdown(MerchantCategory, data.get("CMP_Merchant_Category"), equalEnd, SizeofList("CMP_MerchantCategory_List"));
			Thread.sleep(200);
			
			if (data.get("CMP_Reference_Number").equals("Yes"))
				click("CMP_ReferenceNumber");
			else

			/*******************************************************/
			/****************** Contact Details ********************/
			/*******************************************************/
			// setValue("MCD_ContactMobileSelectCountry", data.get("CMD_Contact_Mobile_Code"));
			setValue("MCD_ContactMobile", data.get("CMD_Contact_Mobile_Number"));
			// setValue("MCD_ContactLandlineSelectCountry", data.get("CMD_Contact_Landline_Code"));
			setValue("MCD_ContactLandline", data.get("CMD_Contact_Landline_Number"));
			setValue("MCD_ContactEmail", data.get("CMD_Contact_Email"));
			// setValue("MCD_ContactFaxSelectCountry", data.get("MCD_Contact_FAX_Code"));
			setValue("MCD_ContactFax", data.get("MCD_Contact_FAX_Number"));

			/*******************************************************/
			/*************** Social Media Contacts *****************/
			/*******************************************************/
			// setValue("MSMC_WhatsAppNumberSelectCountry", data.get("MSMC_WhatsApp_Code"));
			setValue("MSMC_WhatsAppNumber", data.get("MSMC_WhatsApp_Number"));
			setValue("MSMC_FacebookAddress", data.get("MSMC_Facebook_Address"));
			setValue("MSMC_TwitterAddress", data.get("MSMC_Twitter_Address"));

			/*******************************************************/
			/******************** Attachments **********************/
			/*******************************************************/
			
			attachFile(data.get("MA_Legal_License"));
			attachFile(data.get("MA_Proprietor_Photo"));
			attachFile(data.get("MA_Bank_Passbook"));
			attachFile(data.get("MA_Shop_Store_Photo"));
			attachFile(data.get("MA_National_ID"));
			attachFile(data.get("MA_Other_Supportive_Documents"));

			/*******************************************************/
			/***************** Proprietor Detail *******************/
			/*******************************************************/
			setValue("MPD_FirstName", data.get("MPD_First_Name"));
			setValue("MPD_MiddleName", data.get("MPD_Middle_Name"));
			setValue("MPD_LastName", data.get("MPD_Last_Name"));
			setValue("MPD_NationalIDPassport", data.get("MPD_National_ID_Passport"));
			// setValue("MPD_SelectCountry", data.get("MPD_Contact_Mobile_Code"));
			setValue("MPD_ContactMobile", data.get("MPD_Contact_Mobile_Number"));
			setValue("MPD_ContactAddress", data.get("MPD_Contact_Address"));
			// setValue("MPD_Nationality", data.get("MPD_Nationality"));

			/*******************************************************/
			/********** Merchant Bank Account Informations *********/
			/*******************************************************/
			
			click("MBA_BankName_DDL");
			Thread.sleep(200);
			String MerchantBankName = "Xpath@@//ul[@id='select2-shopBankName-results']/li[";
			SelectDropdown(MerchantBankName, data.get("MBA_Bank_Name"), equalEnd, SizeofList("MBA_BankName_List"));
			Thread.sleep(200);
			
			click("MBA_Branch_DDL");
			Thread.sleep(200);
			String MerchantBranch = "Xpath@@//ul[@id='select2-shopBankBranchCode-results']/li[";
			SelectDropdown(MerchantBranch, data.get("MBA_Branch"), equalEnd, SizeofList("MBA_Branch_List"));
			Thread.sleep(200);
			
			click("MBA_AccountType_DDL");
			Thread.sleep(200);
			String MerchantAccountType = "Xpath@@//ul[@id='select2-shopBankAccountType-results']/li[";
			SelectDropdown(MerchantAccountType, data.get("MBA_Account_Type"), equalEnd, SizeofList("MBA_AccountType_List"));
			Thread.sleep(200);
			
			click("MBA_City_DDL");
			Thread.sleep(200);
			String MerchantCity = "Xpath@@//ul[@id='select2-shopBankLocation-results']/li[";
			SelectDropdown(MerchantCity, data.get("MBA_City"), equalEnd, SizeofList("MBA_City_List"));
			Thread.sleep(200);
			
			setValue("MBA_BankAddress", data.get("MBA_Bank_Address"));
			setValue("MBA_AccountNumber", data.get("MBA_Account_Number"));
			setValue("MBA_Branch_Code", data.get("MBA_Branch_Code"));
			setValue("MBA_OtherDetails", data.get("MBA_Other_Details"));

			/*******************************************************/
			/******************** Store Logo ***********************/
			/*******************************************************/
//			attachFile(data.get("Store_Logo"));

			/*******************************************************/
			/****************** Other Details **********************/
			/*******************************************************/
			setValue("MMisc_OtherDetails", data.get("Misc_Other_Details"));

			test.log(LogStatus.PASS, "User is on Create Merchant - Profile screen");
			CaptureScreen();

			click("Profile_NextButton");
			Thread.sleep(1000);

			/*******************************************************/
			/*************** Login Credentials Tab *****************/
			/************* Mobile Number & Password ****************/
			/*******************************************************/
			// setValue("MMNP_SelectCountry", data.get("MMNP_SelectCountry"));
			
			String mobileNumber = CustomRandom.mobileNumber(10);
			setValue("MMNP_MobileNumber", mobileNumber);
			int rowNum = Integer.parseInt(data.get("row"));
			Result_to_Xls(xls, "TestData", rowNum, mobileNumber, "MMNP_MobileNumber");
			System.out.println("Mobile Number:: " + mobileNumber);
			
			String sp_password = CustomRandom.randomAlphaNumeric(10);
			setValue("MMNP_Password", sp_password);
			rowNum = Integer.parseInt(data.get("row"));
			Result_to_Xls(xls, "TestData", rowNum, sp_password, "MMNP_Password");
			System.out.println("Password:: " + sp_password);
			
			setValue("MMNP_RePassword", sp_password);
			rowNum = Integer.parseInt(data.get("row"));
			Result_to_Xls(xls, "TestData", rowNum, sp_password, "MMNP_RePassword");
			System.out.println("Confirm Password:: " + sp_password);

			/*******************************************************/
			/*************** Login Credentials Tab *****************/
			/**************** Additional Settings ******************/
			/*******************************************************/
			// setValue("MAS_Status", data.get("MAS_Status"));
			switch (data.get("MAS_Status")) {
			case "Active":
				click("MAS_Status_Active");
				break;
			case "Blocked":
				click("MAS_Status_Blocked");
				break;
			case "Expired":
				click("MAS_Status_Expired");
				break;
			case "Other":
				click("MAS_Status_Other");
				break;
			default:
			}
			
			Thread.sleep(100);
			setValue("MAS_ExpiryDate", data.get("MAS_ExpiryDate"));
			Thread.sleep(100);
			setValue("MAS_LoginComments", data.get("MAS_LoginComments"));

			test.log(LogStatus.PASS, "User is on Create Merchant - Login Credentials screen");
			CaptureScreen();

			click("LoginCredentials_Next");
			Thread.sleep(1000);

			/*******************************************************/
			/****************** Generate QR Code *******************/
			/*******************************************************/

			test.log(LogStatus.PASS, "User is on Create Merchant - Generate QRCode screen");
			CaptureScreen();

			click("QRCode_Next");
			Thread.sleep(1000);

			/*******************************************************/
			/********************** Payments ***********************/
			/*******************************************************/
			
			/*****************************************/
			/**************** Section 1 **************/
			/*****************************************/
			
			click("MRF_FeeDuration_DDL");
			Thread.sleep(200);
			String MerchantFeeDuration = "Xpath@@//ul[@id='select2-membershipFeeId-results']/li[";
			SelectDropdown(MerchantFeeDuration, data.get("MRF_FeeDuration"), equalEnd, SizeofList("MRF_FeeDuration_List"));
			Thread.sleep(200);
			
			click("MSSF_TypeOfSupportContact_DDL");
			Thread.sleep(200);
			String MerchantTypeOfSupportContact = "Xpath@@//ul[@id='select2-supportFeeConfigId-results']/li[";
			SelectDropdown(MerchantTypeOfSupportContact, data.get("MSSF_TypeOfSupportContact"), equalEnd, SizeofList("MSSF_TypeOfSupportContact_List"));
			Thread.sleep(200);
			
			click("MSALF_NumberOfStaffs_DDL");
			Thread.sleep(200);
			String MerchantNumberOfStaffs = "Xpath@@//ul[@id='select2-salesAgentLicFeeConfigId-results']/li[";
			SelectDropdown(MerchantNumberOfStaffs, data.get("MSALF_NumberOfStaffs"), equalEnd, SizeofList("MSALF_NumberOfStaffs_List"));
			Thread.sleep(200);
			
			// setValue("MSALF_SalesAgentDeskAttachment", data.get("MSALF_SalesAgentDeskAttachment"));
			// setValue("MSALF_SupportLevelDocumentsAttachment", data.get("MSALF_SupportLevelDocumentsAttachment"));
			// setValue("MSALF_Attachment3", data.get("MSALF_Attachment3"));
			
			
			/*****************************************/
			/**************** Section 2 **************/
			/*****************************************/
			
			click("MSALF_S2_PaymentMethod_DDL");
			Thread.sleep(200);
			String MerchantPaymentMethod = "Xpath@@//ul[@id='select2-paymentMethod-results']/li[";
			SelectDropdown(MerchantPaymentMethod, data.get("MSALF_S2_PaymentMethod"), equalEnd, SizeofList("MSALF_S2_PaymentMethod_List"));
			Thread.sleep(200);
			
//			setValue("MSALF_S2_ChequeNumber", data.get("MSALF_S2_ChequeNumber"));
			// setValue("MSALF_S2_Attachment4", data.get("MSALF_S2_Attachment4"));
			setValue("MSALF_S2_TotalAmount", data.get("MSALF_S2_TotalAmount"));
			setValue("MSALF_S2_AmountInWords", data.get("MSALF_S2_AmountInWords"));
			setValue("MSALF_S2_PaymentComments", data.get("MSALF_S2_PaymentComments"));

			test.log(LogStatus.PASS, "User is on Create Merchant - Payments screen");
			CaptureScreen();

			click("Payments_NextButton");
			Thread.sleep(1000);

			/*******************************************************/
			/****************** Terms&Conditions *******************/
			/*******************************************************/
			click("MSA_ServiceAgreement");
			Thread.sleep(500);
			click("MIA_InterfaceAgreement");
			Thread.sleep(500);
			click("MC_CaptureESignatureBase");
			Thread.sleep(500);
			click("MC_CaptureESignatureNew");
			Thread.sleep(500);
			click("MC_SubmitButton");
			Thread.sleep(500);
			// setValue("MLL_LegalLicense", data.get("MLL_LegalLicense"));
			setValue("MTC_TermsConditionsComments", data.get("MTC_TermsConditionsComments"));

			test.log(LogStatus.PASS, "User is on Create Merchant - Terms and Conditions screen");
			CaptureScreen();

			click("MTC_TermsConditionNextButton");
			Thread.sleep(500);

			test.log(LogStatus.PASS, "User is on Create Merchant - Preview screen");
			CaptureScreen();

			click("MF_FinishButton");
			Thread.sleep(1500);

			test.log(LogStatus.PASS, "User is on Merchant Listing screen");
			CaptureScreen();

			rowNum = Integer.parseInt(data.get("row"));
			Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");

		} catch (Exception e) {

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