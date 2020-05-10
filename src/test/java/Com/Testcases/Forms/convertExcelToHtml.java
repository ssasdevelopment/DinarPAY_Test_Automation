package Com.Testcases.Forms;

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

import Com.Testcases.Forms.DataProviderClass;
import Com.base.BaseTest;
import Com.base.CustomRandom;

public class convertExcelToHtml extends BaseTest {
	int i = 0;

	@BeforeSuite
	public void setProperties() {
		propertiesFile = "convertExcelToHtml";
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

	@Test(dataProvider = "Test_convertExcelToHtml", dataProviderClass = DataProviderClass.class)
	public void Test_convertExcelToHtml(Hashtable<String, String> data)
			throws InterruptedException, SQLException, ClassNotFoundException, ParseException {
		try {
			ImplicitWait();
			testCaseName = "Test_convertExcelToHtml";
			Date d = new Date();

			String TestCaseName = data.get("TestcaseName") + "_" + data.get("Environment") + "_"
					+ d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("TestcaseName") + "_" + data.get("Environment");
			String TestCaseComDesc = data.get("TestcaseName") + "_" + data.get("Environment");

			StartReport(TestCaseDesc, TestCaseComDesc);
			isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);

			if (i == 1) {
				test.log(LogStatus.PASS, "User is on Code Beautify Home screen");
				CaptureScreen();

				click("excel2html");

				test.log(LogStatus.PASS, "User is on EXCEL to HTML Converter screen");
				CaptureScreen();
				
				Thread.sleep(1000);
			}
			
			click("browseExcelFile");
			Thread.sleep(100);
			attachFile(data.get("Form_Source_Location"));
			Thread.sleep(2000);

			click("downloadHTML");
			
			Thread.sleep(500);
			renameFormName("C:\\Users\\Windows\\Downloads\\codebeautify.html", data.get("Form_Destination_Location")+".html");
			Thread.sleep(300);

			test.log(LogStatus.PASS, "File downloaded successfully.");
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