package ExtentManager;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends BaseTest {
		
		public static ExtentReports getInstance(String testCaseName)
	    {
	     
	     Date d=new Date();
	     String filename= testCaseName + d.toString().replace(":","_").replace(" ","_")+".html";
	     
	     String Path = "./ExtentReports/"+filename;
//	     String Path = System.getProperty("user.dir")+"//ExtentReports//"+filename;
	     
	     //String Path = "./ExtentReport.html";
	     System.out.println(Path);
	     ExtentReports extent = new ExtentReports(Path, false);
	     extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
	     //extent.config()
	        //   .documentTitle("Automation Report")
	        //   .reportName("Regression");

	     return extent;
	     }

	}
		


