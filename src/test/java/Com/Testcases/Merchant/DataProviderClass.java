package Com.Testcases.Merchant;

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

	@DataProvider(name = "Test_CreateMerchant")
	public static Object[][] Test_CreateMerchant() {

		return ExcelUtil.getTestData(xls, "Test_CreateMerchant");
	}

}
