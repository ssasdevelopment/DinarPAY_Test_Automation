package Com.util;

import java.io.File;
import java.util.Date;
import com.relevantcodes.extentreports.ExtentReports;
import Com.base.BaseTest;

public class ExtentManager extends BaseTest {
		
	
		public static ExtentReports getInstance()
	    {
	     
	     Date d=new Date();
	     String filename= d.toString().replace(":","_").replace(" ","_")+".html";
	     ExtentReportName = filename;
	     
	     String Path = "./ExtentReports/"+filename;
//	     String Path = System.getProperty("user.dir")+"//ExtentReports//"+filename;
	     
	     //String Path = "./ExtentReport.html";
	     System.out.println("Path: "+Path);
	     ExtentReports extent = new ExtentReports(Path, false);
	     extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
//	     extent.config()
//	           .documentTitle("Automation Report")
//	           .reportName("Regression");

	     return extent;
	     }
	}