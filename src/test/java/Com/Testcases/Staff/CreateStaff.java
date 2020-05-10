package Com.Testcases.Staff;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Com.base.BaseTest;
import Com.base.CustomRandom;

public class CreateStaff extends BaseTest {
	int i = 0;
	
	@BeforeSuite
	public void setProperties(){
		propertiesFile = "CreateStaff";
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

	@Test(dataProvider = "Test_CreateStaff", dataProviderClass = DataProviderClass.class)
	public void Test_CreateStaff(Hashtable<String, String> data)	throws InterruptedException, SQLException, ClassNotFoundException, ParseException {
		try {
			ImplicitWait();
			testCaseName = "Test_CreateStaff";
			Date d = new Date();

			String TestCaseName = data.get("TestcaseName") + "_" + data.get("Environment") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("TestcaseName") + "_" + data.get("Environment");
			String TestCaseComDesc = data.get("TestcaseName") + "_" + data.get("Environment");

			StartReport(TestCaseDesc, TestCaseComDesc);
			isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);

			typebyJavascriptExecutor("admin_username", data.get("Username"));
			typebyJavascriptExecutor("admin_password", data.get("Password"));

			test.log(LogStatus.PASS, "User is on the login screen");
			CaptureScreen();

			click("admin_loginBtn");
			
			test.log(LogStatus.PASS, "User is on the OTP screen");
			CaptureScreen();

			waitForElementToBeClickable("StaffManagementLink");
			Thread.sleep(500);

			test.log(LogStatus.PASS, "User is on the Dashboard screen");
			CaptureScreen();

			click("StaffManagementLink");
			Thread.sleep(500);

			test.log(LogStatus.PASS, "User is on the Staff Management screen");
			CaptureScreen();

			click("NewStaffLink");
			Thread.sleep(500);

//			String containsEnd = "')]";
//			String equalEnd = "]";
			String CssEqualEnd = ")";

			
			/*******************************************************/
			/****************** Create New Staff *******************/
			/****************** Personal Details *******************/
			/*******************************************************/
			
			setValue("SPPD_FirstName", data.get("SPPD_FirstName"));
			setValue("SPPD_MiddleName", data.get("SPPD_MiddleName"));
			setValue("SPPD_LastName", data.get("SPPD_LastName"));
			setValue("SPPD_NickName", data.get("SPPD_NickName"));
			setValue("SPPD_ContactAddress", data.get("SPPD_ContactAddress"));
			
			click("SPPD_Nationality_DDL");
			Thread.sleep(200);
			String Nationality = "CSS@@ul#select2-countries-results > li:nth-of-type(";
			SelectDropdown(Nationality, data.get("SPPD_Nationality"), CssEqualEnd, SizeofList("SPPD_Nationality_List"));
			Thread.sleep(200);
			
			click("SPPD_Gender_DDL");
			Thread.sleep(200);
			String Gender = "CSS@@ul#select2-staffGenderCode-results > li:nth-of-type(";
			SelectDropdown(Gender, data.get("SPPD_Gender"), CssEqualEnd, SizeofList("SPPD_Gender_List"));
			Thread.sleep(200);
			
			
			/*******************************************************/
			/****************** Contact Details ********************/
			/*******************************************************/
			
//			setValue("SPCD_ContactCode", data.get("SPCD_ContactCode"));
			setValue("SPCD_ContactMobile", data.get("SPCD_ContactMobile"));
//			setValue("SPCD_LandlineCode", data.get("SPCD_LandlineCode"));
			setValue("SPCD_LandlineNumber", data.get("SPCD_LandlineNumber"));
			setValue("SPCD_ContactEmail", data.get("SPCD_ContactEmail"));
//			setValue("SPCD_FAXCode", data.get("SPCD_FAXCode"));
			setValue("SPCD_FAXNumber", data.get("SPCD_FAXNumber"));
			
			
			/*******************************************************/
			/*************** Social Media Contacts *****************/
			/*******************************************************/
			
			setValue("SPCD_WhatsappNumber", data.get("SPCD_WhatsappNumber"));
			setValue("SPCD_TwitterAddress", data.get("SPCD_TwitterAddress"));
			setValue("SPCD_FacebookAddress", data.get("SPCD_FacebookAddress"));
			
			
			/*******************************************************/
			/*********** Department and Section Details ************/
			/*******************************************************/
			
			click("SPDSD_Department_DDL");
			Thread.sleep(200);
			String Department = "CSS@@ul#select2-departments-results > li:nth-of-type(";
			SelectDropdown(Department, data.get("SPDSD_Department"), CssEqualEnd, SizeofList("SPDSD_Department_List"));
			Thread.sleep(200);

			click("SPDSD_Designation_DDL");
			Thread.sleep(200);
			String Designation = "CSS@@ul#select2-designations-results > li:nth-of-type(";
			SelectDropdown(Designation, data.get("SPDSD_Designation"), CssEqualEnd, SizeofList("SPDSD_Designation_List"));
			Thread.sleep(200);
			
			click("SPDSD_EmploymentStartDate");
			KeyPress("Enter");
			
//			setValue("SPDSD_EmploymentStartDate", data.get("SPDSD_EmploymentStartDate"));
			setValue("SPDSD_Location", data.get("SPDSD_Location"));
			
			click("SPDSD_Section_DDL");
			Thread.sleep(200);
			String Section = "CSS@@ul#select2-sections-results > li:nth-of-type(";
			SelectDropdown(Section, data.get("SPDSD_Section"), CssEqualEnd, SizeofList("SPDSD_Section_List"));
			Thread.sleep(200);
			
			click("SPDSD_ReportingManager");
			Thread.sleep(200);
			String reportingManager = data.get("SPDSD_ReportingManager");
			setValue("SPDSD_ReportingManager_SearchBox", reportingManager);
			Thread.sleep(200);
			KeyPress("Enter");
			
			Thread.sleep(200);
			
			setValue("SPDSD_ContactAddress", data.get("SPDSD_ContactAddress"));
			
			
			/*******************************************************/
			/***************** Access Details **********************/
			/*******************************************************/
//			click("SPAD_AccessRoleRights");
			
			Random random = new Random();
			int staff_id = random.nextInt(89999) + 10000;
			String UserName = data.get("SPDSD_Designation").replace(" ", "_") + "_" + staff_id;
			setValue("SPAD_UserName", UserName);
			int rowNum = Integer.parseInt(data.get("row"));
			Result_to_Xls(xls, "TestData", rowNum, UserName, "SPAD_UserName");
			System.out.println("UserName:: " + UserName);
			
			String sp_password = CustomRandom.randomAlphaNumeric(14);
			setValue("SPAD_Password", sp_password);
			rowNum = Integer.parseInt(data.get("row"));
			Result_to_Xls(xls, "TestData", rowNum, sp_password, "SPAD_Password");
			System.out.println("Password:: " + sp_password);
			
			setValue("SPAD_Re_enterPassword", sp_password);
			rowNum = Integer.parseInt(data.get("row"));
			Result_to_Xls(xls, "TestData", rowNum, sp_password, "SPAD_Re_enterPassword");
			System.out.println("Confirm Password:: " + sp_password);
			
			click("SPAD_Status_DDL");
			Thread.sleep(200);
			String Status = "CSS@@ul#select2-generalStatuses-results > li:nth-of-type(";
			SelectDropdown(Status, data.get("SPAD_Status"), CssEqualEnd, SizeofList("SPAD_Status_List"));
			Thread.sleep(200);
			
			
			/*******************************************************/
			/******************** Attachments **********************/
			/*******************************************************/
			attachFile(data.get("SPA_Photo"));
			attachFile(data.get("SPA_NationalID"));
			attachFile(data.get("SPA_System_usageAgreements"));
			attachFile(data.get("SPA_OtherSupportiveDocuments"));
			
			
			setValue("SPA_Notes", data.get("SPA_Notes"));
			
			test.log(LogStatus.PASS, "User is on Create Staff Profile screen");
			CaptureScreen();
			
			click("CreateStaffButton");
			
			test.log(LogStatus.PASS, "User is on Staff Listing screen");
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