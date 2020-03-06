package Com.base;



import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Com.base.BaseTest;
import Com.base.ExcelUtil;
import Com.base.Xlsx_Reader;
import com.relevantcodes.extentreports.LogStatus;

public class DataDriven_saparateProvider extends BaseTest{
	@Test(dataProvider="getData")
	public static void TestQuoteStatus(Hashtable<String,String> data)
	{
		try
		{
		test=rep.startTest("TestQuoteStatus");
	//	System.out.println("in test "+dataRowNum);
		int rowNum=Integer.parseInt(data.get("row"));
		
	//	xls.setCellData("TestData","Result",rowNum,testStartRowNum,"PASS");
		
	//	System.out.println(data.get("Runmode")+"---"+data.get("Col1")+"---"+data.get("Col2")+"--------------"+data.get("row"));
		
		if(!ExcelUtil.isRunnable("TestQuoteStatus", xls)||data.get("Runmode").equals("N")){
			//xls.setCellData("TestData","Result",dataRowNum,testStartRowNum, "SKIP");
		//	test.log(LogStatus.SKIP,"Skipping the test as runmode is N");
			test.log(LogStatus.FAIL,"Testcase failed");
		//	throw new SkipException("Skipping the test as runmode is N");
			
			
		}
		//System.out.println("Testcase C");
		
		//xls.setCellData("TestData","Result",dataRowNum,testStartRowNum, "PASS");
		
	}
	
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	}
	@DataProvider
	public static Object[][] getData(){
		
		Xlsx_Reader xls=new Xlsx_Reader("C:\\Users\\RKumar\\Breeze Automation Project Stuff\\Testdata\\TestCaseData.xlsx");
		return ExcelUtil.getTestData(xls, "TestQuoteStatus");
	}
	@AfterMethod
	public void afterMethod(){
		
		rep.endTest(test);
		rep.flush();
	}


}

