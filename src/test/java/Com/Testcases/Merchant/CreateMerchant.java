package Com.Testcases.Merchant;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.lang.System;
import java.util.Hashtable;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Com.Testcases.Merchant.DataProviderClass;
import Com.base.BaseTest;

public class CreateMerchant extends BaseTest {
	int i = 0;

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
	public void Test_CreateMerchant(Hashtable<String, String> data)
			throws InterruptedException, SQLException, ClassNotFoundException, ParseException {
		try {
			ImplicitWait();
			testCaseName = "Test_CreateMerchant";
			Date d = new Date();

			String TestCaseName = data.get("TestcaseName") + "-" + data.get("Environment") + "_"
					+ d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("TestcaseName") + "-" + data.get("Environment");
			String TestCaseComDesc = data.get("TestcaseName") + "-" + data.get("Environment");

			StartReport(TestCaseDesc, TestCaseComDesc);
			isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);
			// System.out.println("System user directory path:
			// "+System.getProperty("user.dir"));

			typebyJavascriptExecutor("admin_username", data.get("Username"));
			typebyJavascriptExecutor("admin_password", data.get("Password"));
			
			test.log(LogStatus.PASS, "User is on the login screen");
			CaptureScreen();

//			Thread.sleep(20000);
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

			/*******************************************************/
			/**************** Create New Merchant ******************/
			/******************** Profile Tab **********************/
			/****************** Merchant Profile *******************/
			/*******************************************************/
			setValue("CMP_LegalNameOfBusiness", data.get("Legal_Name_of_Business"));
			setValue("CMP_BrandingName", data.get("Trading_Branding_Name"));
			// setValue("CMP_SameAsLegaName", data.get("Same_as_Legal_Name"));
			setValue("CMP_NameofStoreShop", data.get("Name_of_Store_Shop"));
			// setValue("CMP_SameAsLegaName", data.get("Same_as_Legal_Name"));

			if (data.get("Branch").equals("Yes")) {
				click("CMP_Branch_Yes");
			} else {
//				click("CMP_Branch_No");
			}

//			setValue("CMP_MerchantType", data.get("Merchant_Type"));
			setValue("CMP_Addnotes", data.get("Add_Notes"));

			switch (data.get("CMP_Status")) {
				case "Active":
//					click("CMP_Status_Active");
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
//			setValue("CMP_MerchantCategory", data.get("CMP_Merchant_Category"));
			
			if(data.get("CMP_Reference_Number_QR_Code").equals("No"))
			click("CMP_ReferenceNumber");
			else
//			click("CMP_QRCode");

			/*******************************************************/
			/****************** Contact Details ********************/
			/*******************************************************/
//			setValue("MCD_ContactMobileSelectCountry", data.get("CMD_Contact_Mobile_Code"));
			setValue("MCD_ContactMobile", data.get("CMD_Contact_Mobile_Number"));
//			setValue("MCD_ContactLandlineSelectCountry", data.get("CMD_Contact_Landline_Code"));
			setValue("MCD_ContactLandline", data.get("CMD_Contact_Landline_Number"));
			setValue("MCD_ContactEmail", data.get("CMD_Contact_Email"));
//			setValue("MCD_ContactFaxSelectCountry", data.get("MCD_Contact_FAX_Code"));
			setValue("MCD_ContactFax", data.get("MCD_Contact_FAX_Number"));

			/*******************************************************/
			/*************** Social Media Contacts *****************/
			/*******************************************************/
//			setValue("MSMC_WhatsAppNumberSelectCountry", data.get("MSMC_WhatsApp_Code"));
			setValue("MSMC_WhatsAppNumber", data.get("MSMC_WhatsApp_Number"));
			setValue("MSMC_FacebookAddress", data.get("MSMC_Facebook_Address"));
			setValue("MSMC_TwitterAddress", data.get("MSMC_Twitter_Address"));

			/*******************************************************/
			/******************** Attachments **********************/
			/*******************************************************/
//			setValue("MA_LegalLicense", data.get("MA_Legal_License"));
//			setValue("MA_ProprietorPhoto", data.get("MA_Proprietor_Photo"));
//			setValue("MA_BankPassbook", data.get("MA_Bank_Passbook"));
//			setValue("MA_ShopStorePhoto", data.get("MA_Shop_Store_Photo"));
//			setValue("MA_NationalID", data.get("MA_National_ID"));
//			setValue("MA_OtherSupportiveDocuments", data.get("MA_Other_Supportive_Documents"));

			/*******************************************************/
			/***************** Proprietor Detail *******************/
			/*******************************************************/
			setValue("MPD_FirstName", data.get("MPD_First_Name"));
			setValue("MPD_MiddleName", data.get("MPD_Middle_Name"));
			setValue("MPD_LastName", data.get("MPD_Last_Name"));
			setValue("MPD_NationalIDPassport", data.get("MPD_National_ID_Passport"));
//			setValue("MPD_SelectCountry", data.get("MPD_Contact_Mobile_Code"));
			setValue("MPD_ContactMobile", data.get("MPD_Contact_Mobile_Number"));
			setValue("MPD_ContactAddress", data.get("MPD_Contact_Address"));
//			setValue("MPD_Nationality", data.get("MPD_Nationality"));

			/*******************************************************/
			/********** Merchant Bank Account Informations *********/
			/*******************************************************/
//			setValue("MBA_BankName", data.get("MBA_Bank_Name"));
//			setValue("MBA_Branch", data.get("MBA_Branch"));
//			setValue("MBA_AccountType", data.get("MBA_Account_Type"));
//			setValue("MBA_City", data.get("MBA_City"));
			setValue("MBA_BankAddress", data.get("MBA_Bank_Address"));
			setValue("MBA_AccountNumber", data.get("MBA_Account_Number"));
			setValue("MBA_Branch_Code", data.get("MBA_Branch_Code"));
			setValue("MBA_OtherDetails", data.get("MBA_Other_Details"));

			/*******************************************************/
			/******************** Store Logo ***********************/
			/*******************************************************/
//			setValue("MSL_StoreLogo", data.get("Store_Logo"));

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
//			setValue("MMNP_SelectCountry", data.get("MMNP_SelectCountry"));
			setValue("MMNP_MobileNumber", data.get("MMNP_MobileNumber"));
			setValue("MMNP_Password", data.get("MMNP_Password"));
			setValue("MMNP_RePassword", data.get("MMNP_RePassword"));

			/*******************************************************/
			/*************** Login Credentials Tab *****************/
			/**************** Additional Settings ******************/
			/*******************************************************/
//			setValue("MAS_Status", data.get("MAS_Status"));
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
//			setValue("MRF_FeeDuration", data.get("MRF_FeeDuration"));
//			setValue("MSSF_TypeOfSupportContact", data.get("MSSF_TypeOfSupportContact"));
//			setValue("MSALF_NumberOfStaffs", data.get("MSALF_NumberOfStaffs"));
//			setValue("MSALF_SalesAgentDeskAttachment", data.get("MSALF_SalesAgentDeskAttachment"));
//			setValue("MSALF_SupportLevelDocumentsAttachment", data.get("MSALF_SupportLevelDocumentsAttachment"));
//			setValue("MSALF_Attachment3", data.get("MSALF_Attachment3"));
//			setValue("MSALF_S2_PaymentMethod", data.get("MSALF_S2_PaymentMethod"));
			setValue("MSALF_S2_ChequeNumber", data.get("MSALF_S2_ChequeNumber"));
//			setValue("MSALF_S2_Attachment4", data.get("MSALF_S2_Attachment4"));
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
//			setValue("MLL_LegalLicense", data.get("MLL_LegalLicense"));
			setValue("MTC_TermsConditionsComments", data.get("MTC_TermsConditionsComments"));
			
			test.log(LogStatus.PASS, "User is on Create Merchant - Terms and Conditions screen");
			CaptureScreen();
			
			click("MTC_TermsConditionNextButton");
			Thread.sleep(500);
			
			test.log(LogStatus.PASS, "User is on Create Merchant - Preview screen");
			CaptureScreen();
			
			click("MF_FinishButton");
			Thread.sleep(2000);

			test.log(LogStatus.PASS, "User is on Merchant Listing screen");
			CaptureScreen();
			
			int rowNum = Integer.parseInt(data.get("row"));
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