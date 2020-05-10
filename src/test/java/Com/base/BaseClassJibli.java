package Com.base;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.SkipException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Com.base.ExcelUtil;
import Com.base.Xlsx_Reader;

import Com.util.ExtentManager;

public class BaseClassJibli {

	public static WebDriver driver;

	public Properties prop;
	public static Properties prop1;
	static File file;

	public Properties Path;
	public static String ExtentReportName;

	public static int testStartRowNum;
	public static int dataRowNum;
	public static String testCaseName;
	public static String TC_Name;
	public static String propertiesFile;

	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	public static int colSRowNum;
	public static int rows;
	public static String TestStatus = "";

	public static Xlsx_Reader xls = new Xlsx_Reader(System.getProperty("user.dir") + "\\Testdata\\TestCaseData.xlsx");

	/***************** General Functions *********************/

	public void TestAfterMethod() {
		rep.endTest(test);
		rep.flush();
		renameExtentReport();
	}
	
	public boolean isElementPresent(String xpathEle) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		try {
			driver.findElement(By.xpath(GetObjectPath(xpathEle))).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public String GetPropValue(String PName) {
		String x = null;
		try {
			if (prop == null) {
				prop = new Properties();
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\java\\Com\\Source\\Config.properties");
				prop.load(fs);
			}
			x = prop.getProperty(PName);
			// System.out.println(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public String GetObjectPath(String PName) {
		String P = null;
		try {
			if (Path == null) {
				Path = new Properties();
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\java\\Com\\Source\\"+ propertiesFile +".properties");
				Path.load(fs);
			}
			P = Path.getProperty(PName);
			// System.out.println(P);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return P;
	}

	/************************
	 * Start:Custom Extent Report Name
	 *************************/

	/*----------------------------Start the Extent Report------------------------------------*/
	public static void StartReport(String Tname, String data) {
		// test=rep.startTest(Tname);
		test = rep.startTest(Tname, data);
		test.log(LogStatus.INFO, "Starting the testcase-> " + Tname + " :: " + data);
		// System.out.println("Starting the testcase "+Tname + " with TFS ID "+
		// data);
	}

	public static void isTestRunnable(Hashtable<String, String> data) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if (!ExcelUtil.isRunnable(testCaseName, xls) || data.get("Runmode").equals("N")) {
			// xls.setCellData("TestData","Result",dataRowNum,testStartRowNum,
			// "SKIP");
			test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
			TestStatus = "Skip";
			throw new SkipException("Skipping the test as runmode is N");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	/****************** Reporting *****************************/

	public static void reportPass(String Msg) {
		test.log(LogStatus.PASS, Msg);
	}

	public static void reportFail(String Msg) {
		test.log(LogStatus.FAIL, Msg);
	}

	public static void reportSkip(String Msg) {
		test.log(LogStatus.SKIP, Msg);
	}

	public void CaptureScreen() {
		Date d = new Date();

		String filename = testCaseName + "_" + d.toString().replace(":", "_").replace(" ", "_") + "_"
				+ System.currentTimeMillis() + ".jpg";

		String HtmlPath = "../ExtentReports/ScreenShots/" + filename;
		String ImagePath = "./ExtentReports/ScreenShots/" + filename;
		// System.out.println(Path);

		TakesScreenshot oScn = (TakesScreenshot) driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(ImagePath);
		try {
			FileUtils.copyFile(oScnShot, oDest);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		test.log(LogStatus.PASS, "ScreenShot: " + test.addScreenCapture(HtmlPath));
	}
	
	protected static void renameExtentReport() {
		try {
			File file = new File(System.getProperty("user.dir") + "\\ExtentReports\\" + ExtentReportName);
			File newFile = new File(System.getProperty("user.dir") + "\\ExtentReports\\" + TName);
			if (file.renameTo(newFile)) {
				System.out.println("File Renamed successfully");
			} else {
				System.out.println("File Rename failed");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + ", File Rename failed");
		}
	}
	
	protected static void renameFormName(String orgFilePath, String newFilePath) {
		try {
			File file = new File(orgFilePath);
			File newFile = new File(newFilePath);
			if (file.renameTo(newFile)) {
				System.out.println("Form Renamed successfully");
			} else {
				System.out.println("Form Rename failed");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + ", Form Rename failed");
		}
	}

	static void saveProperties(Properties p) throws IOException {
		FileOutputStream fr = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\Com\\Source\\RunTime.properties");
		p.store(fr, "Properties");
		fr.close();
		System.out.println("After saving properties: " + p);
	}

	static void loadProperties(Properties p) throws IOException {
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\Com\\Source\\RunTime.properties");
		p.load(fi);
		fi.close();
		System.out.println("After Loading properties: " + p);
	}

	static String xtc;

	public static String GetPropRunTimeValue(String PName) {
		try {
			if (prop1 == null) {
				prop1 = new Properties();
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\java\\Com\\Source\\RunTime.properties");
				prop1.load(fs);
			}
			prop1.clear();
			prop1.setProperty("TCName", PName);
			saveProperties(prop1);
			loadProperties(prop1);

			xtc = prop1.getProperty("TCName");
			System.out.println(xtc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xtc;
	}

	static String TName;

	public static String customReportName(String TCName) {

		TName = GetPropRunTimeValue(TCName);
		return TName;
	}

	/************************
	 * End:Custom Extent Report Name
	 *************************/

	public String getCurrentDate() {
		DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentdate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentdate);

		String today = dateformat.format(currentdate);

		// c.add(Calendar.DATE, -1);
		// Date prevdate = c.getTime();

		return today;
	}

	public static int RowsCount() {
		return rows;
	}

	public void openBrowser(String bt) {

		String btype = GetPropValue(bt);
		// System.out.println(btype);

		if (btype.equals("Mozilla")) {

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} else if (btype.equals("Chrome")) {

			System.setProperty("webdriver.chrome.driver", GetPropValue("Chrome_Exe"));

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} else if (btype.equals("IE")) {

			System.setProperty("webdriver.ie.driver", GetPropValue("IE_Exe"));
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		}

	}

	/*-----------------------------------------------------------------------*/

	/************************ Navigate to application *************************/
	public void navigate(String URL) {

		driver.get(GetPropValue(URL));
	}

	/*------------------------------------------------------------------------*/


	public static void setClipboardData(String string) {

		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	}

	public static void clearClipboardData() {
		StringSelection stringSelection = new StringSelection("");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static String getClipboardData() throws UnsupportedFlavorException, IOException {

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String x = (String) contents.getTransferData(DataFlavor.stringFlavor);
		// System.out.println(x);
		return x;

	}

	public static void cutPasteScreenShots() {
		File source = new File(System.getProperty("user.dir") + "\\ExtentReports\\ScreenShots");
		File dest = new File("C:\\Users" + System.getProperty("user.name"));
		try {
			FileUtils.copyDirectory(source, dest);
			// FileUtils.forceDelete(source);
			FileUtils.cleanDirectory(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cutPasteHtmlFile() {
		File source = new File(System.getProperty("user.dir") + "\\ExtentReports");
		File dest = new File("C:\\Users" + System.getProperty("user.name"));
		try {
			FileUtils.copyDirectory(source, dest);
			// FileUtils.forceDelete(source);
			// FileUtils.cleanDirectory(source);

			File folder = new File("C:\\Users" + System.getProperty("user.name"));
			File fList[] = folder.listFiles();
			// Searchs .html

			for (File f : fList) {
				if (f.getName().endsWith(".html")) {
					f.delete();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------*/


	/************************ Set Result to Excel ************************/

	public static void Result_to_Xls(Xlsx_Reader xls, String SheetName, int RowNum, String Result,
			String Result_columnname) {

		// int rNum=Integer.parseInt(RowNum);

		xls.setCellData(SheetName, Result_columnname, RowNum, Result, colSRowNum);
	}


	/************************ Waits ******************************************/

	public void ImplicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().timeouts().implicitlyWait(timeSpan.FromSeconds(5));
	}

	public void turnOffImplicitWaits() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	/*----------------------------------------------------------------------*/
}
