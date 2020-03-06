package Com.base;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//import java.sql.Statement;
import java.text.DateFormat;
//import java.text.DecimalFormat;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.System;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.Spring;

//import java.util.function.Predicate;
import java.awt.AWTException;
import java.awt.KeyEventPostProcessor;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import org.openqa.selenium.support.ui.Select;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.usermodel.DateAndTime;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
//import com.gargoylesoftware.htmlunit.javascript.host.event.InputEvent;

import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jna.platform.win32.WinUser.INPUT;

import Com.util.ExtentManager;

public class BaseTest {

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

	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	public static int colSRowNum;
	public static String str1, str2, str3;
	public static int rows;
	public static String TestStatus = "";

	public static Xlsx_Reader xls = new Xlsx_Reader(System.getProperty("user.dir")+"\\Testdata\\TestCaseData.xlsx");

	/***************** General Functions *********************/

	public void TestBeforeMethod() throws AWTException, InterruptedException {
		openBrowser("Browser_Name");
		navigate("DinarPayURL_QA");
		if (isElementPresent("SiteCantReach")) {
			driver.navigate().refresh();
		}
	}

	public void TestAfterMethod() {
		rep.endTest(test);
		rep.flush();
		driver.quit();
		renameExtentReport();
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
						System.getProperty("user.dir") + "\\src\\test\\java\\Com\\Source\\merchant.properties");
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


	public WebElement getLocator(String locator) {

		WebElement element = null;

		String[] loc = locator.split("@@");

		switch (loc[0]) {

		case "ID":
			element = driver.findElement(By.id(loc[1]));
			break;
		case "Name":
			element = driver.findElement(By.name(loc[1]));
			break;
		case "CSS":
			element = driver.findElement(By.cssSelector(loc[1]));
			break;
		case "LinkText":
			element = driver.findElement(By.linkText(loc[1]));
			break;
		case "PartialLinkText":
			element = driver.findElement(By.partialLinkText(loc[1]));
			break;
		case "TagName":
			element = driver.findElement(By.tagName(loc[1]));
			break;
		case "Class":
			element = driver.findElement(By.className(loc[1]));
			break;
		case "Xpath":
			element = driver.findElement(By.xpath(loc[1]));
			break;
		default:
		}
		
		System.out.println("Element Locator "+loc[0]+" : "+element);
		return element;
	}

	/*****************************
	 * Click on Object
	 ******************************/
	public void click(String xpathEle) {

		getLocator(GetObjectPath(xpathEle)).click();

	}

	/*****************************
	 * Enter value in Object
	 ******************************/
	public void setValue(String xpathEle, String data) {

		getLocator(GetObjectPath(xpathEle)).sendKeys(data);

	}

	public void Scrollbyrobotclass() throws AWTException {
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_PAGE_DOWN);
		rb.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	public void mouseHoverbyActionclass(String xpathEle) {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(GetObjectPath(xpathEle)));
		action.moveToElement(element).build().perform();
	}

	public void KeyPress(String Key) throws AWTException {
		Robot rb = new Robot();
		switch (Key) {

		case "f5":
		case "F5":
			rb.keyPress(KeyEvent.VK_F5);
			rb.keyRelease(KeyEvent.VK_F5);
			break;
		case "ESCAPE":
		case "Escape":
		case "escape":
			rb.keyPress(KeyEvent.VK_ESCAPE);
			rb.keyRelease(KeyEvent.VK_ESCAPE);
			break;
		case "ENTER":
		case "Enter":
		case "enter":
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
			break;
		case "SPACE":
		case "Space":
		case "space":
			rb.keyPress(KeyEvent.VK_SPACE);
			rb.keyRelease(KeyEvent.VK_SPACE);
			break;
		case "DOWN":
		case "Down":
		case "down":
			rb.keyPress(KeyEvent.VK_DOWN);
			rb.keyRelease(KeyEvent.VK_DOWN);
			break;
		case "UP":
		case "Up":
		case "up":
			rb.keyPress(KeyEvent.VK_UP);
			rb.keyRelease(KeyEvent.VK_UP);
			break;
		case "LEFT":
		case "Left":
		case "left":
			rb.keyPress(KeyEvent.VK_LEFT);
			rb.keyRelease(KeyEvent.VK_LEFT);
			break;
		case "RIGHT":
		case "Right":
		case "right":
			rb.keyPress(KeyEvent.VK_RIGHT);
			rb.keyRelease(KeyEvent.VK_RIGHT);
			break;
		case "TAB":
		case "Tab": // Enter Tab to press tab key from the virtual keyboard.
		case "tab":
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			break;
		case "PageUp":
		case "pageUp":
		case "pageup":
		case "Pageup":
			rb.keyPress(KeyEvent.VK_PAGE_UP);
			rb.keyRelease(KeyEvent.VK_PAGE_UP);
			break;
		case "PageDown":
		case "Pagedown":
		case "pageDown":
		case "pagedown":
			rb.keyPress(KeyEvent.VK_PAGE_DOWN);
			rb.keyRelease(KeyEvent.VK_PAGE_DOWN);
			break;
		case "Copy":
		case "copy":
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_C);
			rb.keyRelease(KeyEvent.VK_C);
			rb.keyRelease(KeyEvent.VK_CONTROL);
			break;
		case "backspace":
		case "backSpace":
		case "BackSpace":
		case "Backspace":
			rb.keyPress(KeyEvent.VK_BACK_SPACE);
			rb.keyRelease(KeyEvent.VK_BACK_SPACE);
			break;
		case "closeBrowser":
		case "closebrowser":
		case "CloseBrowser":
		case "Closebrowser":
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_W);
			rb.keyRelease(KeyEvent.VK_W);
			rb.keyRelease(KeyEvent.VK_CONTROL);
		case "altdown":
		case "altDown":
		case "Altdown":
		case "AltDown":
			rb.keyPress(KeyEvent.VK_ALT);
			rb.keyPress(KeyEvent.VK_DOWN);
			rb.keyRelease(KeyEvent.VK_DOWN);
			rb.keyRelease(KeyEvent.VK_ALT);
		case "controlback":
		case "controlBack":
		case "Controlback":
		case "ControlBack":
			rb.keyPress(KeyEvent.VK_SHIFT);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_SHIFT);
		default:
			System.out.println("Unknown Key Press.");
		}

	}

	public void mouseHover() throws AWTException {

		Robot robot = new Robot();
		robot.mouseMove(300, 300);
	}

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
		File source = new File(System.getProperty("user.dir")+"\\ExtentReports\\ScreenShots");
		File dest = new File("C:\\Users"+System.getProperty("user.name"));
		try {
			FileUtils.copyDirectory(source, dest);
			// FileUtils.forceDelete(source);
			FileUtils.cleanDirectory(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cutPasteHtmlFile() {
		File source = new File(System.getProperty("user.dir")+"\\ExtentReports");
		File dest = new File("C:\\Users"+System.getProperty("user.name"));
		try {
			FileUtils.copyDirectory(source, dest);
			// FileUtils.forceDelete(source);
			// FileUtils.cleanDirectory(source);

			File folder = new File("C:\\Users"+System.getProperty("user.name"));
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

	public void moveToElement(String xpathEle) {
		WebElement obj = driver.findElement(By.xpath(GetObjectPath(xpathEle)));
		Actions action = new Actions(driver);
		action.moveToElement(obj).build().perform();
	}

	public void moveAndClick(String xpathEle) {
		WebElement obj = driver.findElement(By.xpath(GetObjectPath(xpathEle)));
		Actions action = new Actions(driver);
		action.moveToElement(obj);
		action.click().build().perform();
	}

	public void Selectvalue(String xpathEle, String data) {
		Select select = new Select(driver.findElement(By.xpath(GetObjectPath(xpathEle))));
		select.selectByVisibleText(data);
	}

	public int SizeofList(String xpathEle) {
		List<WebElement> allLinks = driver.findElements(By.xpath(GetObjectPath(xpathEle)));
		int size = allLinks.size();
		// System.out.println(size);
		return size;
	}

	public void SelectVisibleText_byID(String ID, String data) {
		Select select = new Select(driver.findElement(By.id(GetObjectPath(ID))));
		select.selectByVisibleText(data);

	}

	public String ReturnVisibleText_byID(String ID, String data) {
		Select select = new Select(driver.findElement(By.id(GetObjectPath(ID))));
		select.selectByVisibleText(data);
		return data;

	}

	public void SelectVisibleText_byName(String Name, String data) {
		Select select = new Select(driver.findElement(By.name(GetObjectPath(Name))));
		select.selectByVisibleText(data);

	}

	public String SelectValue_byID(String ID, String data) {
		String value = driver.findElement(By.id(GetObjectPath(ID))).getAttribute("value");
		return value;
	}

	public String SelectValue_byName(String Name, String data) {
		String value = driver.findElement(By.name(GetObjectPath(Name))).getAttribute("value");
		return value;
	}

	public void Selectvalue_byID(String ID, String data) {
		Select select = new Select(driver.findElement(By.id(GetObjectPath(ID))));
		select.selectByValue(data);
	}

	public int SizeofList_byID(String ID) {
		List<WebElement> allLinks = driver.findElements(By.id(GetObjectPath(ID)));
		int size = allLinks.size();
		// System.out.println(size);
		return size;
	}

	public int SizeofListbytagname(String xpathEle) {
		WebElement list = driver.findElement(By.xpath(xpathEle));
		List<WebElement> allLinks = list.findElements(By.tagName("li"));
		int size = allLinks.size();
		// System.out.println(size);
		return size;
	}

	public void SelectDDLValue(String str1, String str3, String data, int nofvalues) {

		for (int i = 1; i <= nofvalues; i++) {
			String str = str1 + i + str3;
			// System.out.println(str);
			WebElement obj = driver.findElement(By.xpath(str));
			if (obj.getText().equals(data)) {
				obj.click();
				break;
			}
		}
	}

	public void SelectDDLValue_containsfunction(String str1, String str3, String data, int size) {

		for (int i = 1; i <= size; i++) {
			String str = str1 + data + str3;
			// System.out.println(str);
			// System.out.println(getTextByXpath(str));
			if (getTextByXpath(str).contains(data)) {
				click(str);
				break;
			}
		}
	}

	public WebElement getElementsWithAlteratingNames(String PartialName, String Tag, String Attribute) {
		try {
			List<WebElement> Elements = driver.findElements(By.tagName(Tag));
			String[] ElementStrings = new String[Elements.size()];
			// System.out.println(Elements.size());
			for (int LoopCounter = 0; LoopCounter < Elements.size(); LoopCounter++) {
				ElementStrings[LoopCounter] = Elements.get(LoopCounter).getAttribute(Attribute).toString();
				if (ElementStrings[LoopCounter].contains(PartialName)) {
					// System.out.println(ElementStrings[LoopCounter]);
					return Elements.get(LoopCounter);
				}
			}

		} catch (Exception e) {
			System.out.println("Could not generate a list of elements");
		}
		return null;
	}

	public void dragAndDropBy(String xpathEle) {
		WebElement obj = driver.findElement(By.xpath(GetObjectPath(xpathEle)));
		Actions action = new Actions(driver);
		action.dragAndDropBy(obj, 0, 200).build().perform();
	}

	public void dragAndDrop(String xpathEle1, String xpathEle2) {
		WebElement fromWebElement = driver.findElement(By.xpath(GetObjectPath(xpathEle1)));
		WebElement toWebElement = driver.findElement(By.xpath(GetObjectPath(xpathEle2)));
		Actions builder = new Actions(driver);
		builder.dragAndDrop(fromWebElement, toWebElement);
	}

	public void dragAndDrop_Method2(String xpathEle1, String xpathEle2) {
		WebElement fromWebElement = driver.findElement(By.xpath(GetObjectPath(xpathEle1)));
		WebElement toWebElement = driver.findElement(By.xpath(GetObjectPath(xpathEle2)));
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(fromWebElement).moveToElement(toWebElement).release(toWebElement)
				.build();
		dragAndDrop.perform();
	}

	public boolean IsEmpty(String xpathEle) {
		String text = driver.findElement(By.xpath(GetObjectPath(xpathEle))).getAttribute("value");
		if (text.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void clickbyJavascriptExecutor(String xpathEle) {
		
		WebElement element = getLocator(GetObjectPath(xpathEle));
//		WebElement element = driver.findElement(By.xpath(GetObjectPath(xpathEle)));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

	}

	public void typebyJavascriptExecutor(String xpathEle, String data) {
		WebElement element = getLocator(GetObjectPath(xpathEle));
//		WebElement element = driver.findElement(By.xpath(GetObjectPath(xpathEle)));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].value='" + data + "';", element);

	}

	public void typebyJavascriptExecutor_ID(String id, String data) {
		WebElement element = driver.findElement(By.id(GetObjectPath(id)));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].value='" + data + "';", element);

	}

	public void scrollingOfPage(int startindex, int lastindex) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy("+startindex+","+lastindex+")","");
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

	}

	public void scrollingOfPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void clear(String xpathEle) {
		getLocator(GetObjectPath(xpathEle)).clear();
	}

	public void highlightElement_ID(String ID) throws InterruptedException {
		WebElement element = driver.findElement(By.id(GetObjectPath(ID)));
		for (int i = 0; i < 3; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style,'border: solid 2px red'');", element);
			// js.executeScript("arguments[0].setAttribute('style',
			// arguments[1]);", element, "color: yellow; border: 2px solid
			// yellow;");
			Thread.sleep(200);
			// js.executeScript("arguments[0].setAttribute('style',
			// arguments[1]);", element, "");
			js.executeScript("arguments[0].setAttribute('style,'border: solid 0px white'');", element);
			Thread.sleep(200);
		}
	}

	public void highlightElement(String xpath) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(GetObjectPath(xpath)));
		for (int i = 0; i < 3; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style,'border: solid 2px red'');", element);
			// js.executeScript("arguments[0].setAttribute('style',
			// arguments[1]);", element, "color: yellow; border: 2px solid
			// yellow;");
			Thread.sleep(200);
			// js.executeScript("arguments[0].setAttribute('style',
			// arguments[1]);", element, "");
			js.executeScript("arguments[0].setAttribute('style,'border: solid 0px white'');", element);
			Thread.sleep(200);
		}
	}

	/*-------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------*/

	/*****************************
	 * Click on Object
	 ******************************/
	public String getText(String xpathEle) {
		String x = driver.findElement(By.xpath(GetObjectPath(xpathEle))).getText();
		return x;

	}

	public String getTextByID(String ID) {
		String x = driver.findElement(By.id(GetObjectPath(ID))).getText();
		// System.out.println(x);
		return x;

	}

	public static String getTextByXpath(String xpathEle) {
		String x = driver.findElement(By.xpath(xpathEle)).getText();
		// System.out.println(x);
		return x;

	}

	// public static String getValue(String xpathEle) {
	// String x=driver.findElement(By.xpath(xpathEle)).getAttribute("value");
	//// System.out.println(x);
	// return x;
	// }

	public String getValue(String xpathEle) {
		String x = driver.findElement(By.xpath(GetObjectPath(xpathEle))).getAttribute("value");
		// System.out.println(x);
		return x;
	}

	public void getValueDirect() {
		String x = driver
				.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolder1_FinancialsContent_m_tbDescription']"))
				.getAttribute("value");
		System.out.println(x);
	}

	/*-------------------------------------------------------------------------*/

	public boolean IscheckboxSelected(String xpathEle) {
		WebElement Checkbox = driver.findElement(By.xpath(GetObjectPath(xpathEle)));
		if (!Checkbox.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public void SelectfromListOfElements(String xpathEle, String data) {
		List<WebElement> list = driver.findElements(By.xpath(GetObjectPath(xpathEle)));
		for (WebElement element : list) {
			element.click();
			element.sendKeys(data);
			break;
		}
	}

	public String getAttribute(String xpathEle) {
		return (driver.findElement(By.xpath(GetObjectPath(xpathEle))).getAttribute("value"));

	}

	public void enter(String xpathEle, String data) throws AWTException {

	}
	/*-------------------------------------------------------------------------*/

	/*----------------------------------------------------------------------*/

	/************************ Waits ******************************************/
	public void ExplicitWaitForElement(String xpathEle) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GetObjectPath(xpathEle))));
	}

	public void waitForElementToBeClickable(String xpathEle) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(getLocator(GetObjectPath(xpathEle))));
//		getLocator(GetObjectPath(xpathEle)).click();

	}

	public void waitfortextPresentInElement(String xpathEle, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement obj = driver.findElement(By.xpath(GetObjectPath(xpathEle)));
		wait.until(ExpectedConditions.textToBePresentInElement(obj, text));
	}

	public void ImplicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().timeouts().implicitlyWait(timeSpan.FromSeconds(5));
	}

	public void turnOffImplicitWaits() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	/*----------------------------------------------------------------------*/

	/********************** Window Switching **********************************/

	public void closeAllBrowsersButOrg() {
		String OrgHandle = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(OrgHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
		driver.switchTo().window(OrgHandle);
	}

	public void closeCurrentBrowser() {
		String CurHandle = driver.getWindowHandle();
		driver.switchTo().window(CurHandle);
		driver.close();
	}

	public void SwitchToNewWindow() {
		Set<String> windIDs = driver.getWindowHandles();
		System.out.println("Total browsers are" + windIDs.size());
		Iterator<String> it = windIDs.iterator();
		String main_window = it.next();
		String tab_window = it.next();
		driver.switchTo().window(tab_window);
		// System.out.println(driver.getWindowHandles());
	}

	public void SwitchToNewerWindow() {
		Set<String> windIDs = driver.getWindowHandles();
		System.out.println("Total browsers are" + windIDs.size());
		Iterator<String> it = windIDs.iterator();
		String main_window = it.next();
		String tab_window = it.next();
		String tab1_window = it.next();
		driver.switchTo().window(tab1_window);
		// System.out.println(driver.getWindowHandles());
	}

	public void SwitchToLatestWindow() {
		Set<String> windIDs = driver.getWindowHandles();
		System.out.println("Total browsers are" + windIDs.size());
		Iterator<String> it = windIDs.iterator();
		String main_window = it.next();
		String tab_window = it.next();
		String tab1_window = it.next();
		String tab2_window = it.next();
		driver.switchTo().window(tab2_window);
		// System.out.println(driver.getWindowHandles());
	}

	public void switchToOldWindow() {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = itr.next();
		String w2 = itr.next();
		// driver.close();
		driver.switchTo().window(w1);
	}

	public void switchToLatestOldWindow() {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = itr.next();
		String w2 = itr.next();
		// driver.close();
		driver.switchTo().window(w2);
	}

	public void switchToOlderWindow() {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = itr.next();
		String w2 = itr.next();
		String w3 = itr.next();
		// driver.close();
		driver.switchTo().window(w1);
	}

	public String switchwindow(String object, String data) {
		try {

			// String winHandleBefore = driver.getWindowHandle();

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
		} catch (Exception e) {
			return // Constants.KEYWORD_FAIL+ "Unable to Switch Window" +//
			e.getMessage();
		}
		return data;

	}

	/**************
	 * ClaimShield Login Function
	 * 
	 * @throws InterruptedException
	 ***********************/
	public void login() throws InterruptedException {
		clickbyJavascriptExecutor("username");
		clear("username");
		Thread.sleep(5000);
		setValue("username", GetPropValue("username"));
		Thread.sleep(5000);
		clickbyJavascriptExecutor("passwd");
		Thread.sleep(5000);
		setValue("passwd", GetPropValue("passwd"));
		Thread.sleep(5000);
		clickbyJavascriptExecutor("Login");
		Thread.sleep(5000);
	}

	/**
	 * <h1>switchToFrame_byName!</h1> This method will helps us to switch to a
	 * Frame. Here you need to pass name of the frame
	 */
	public void switchToFrame_byID(String frameID) {
		driver.switchTo().frame(frameID);

	}

	/**
	 * <h1>switchToFrame_byIndex!</h1> This method will helps us to switch to a
	 * Frame. Here you need to pass number of the frame
	 */

	public void switchToFrame_byIndex(int frameValue) {
		driver.switchTo().frame(frameValue);
	}

	public void switchToFrame_byWebElement(String frameName) throws Exception {
		WebElement webelement = driver.findElement(By.tagName(frameName));
		try {
			driver.switchTo().frame(webelement);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Delete all cookies present in the application
	 */
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();

	}

	/*******************
	 * Database Connectivity functions
	 * 
	 * @return
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *********/

	public static Connection oracleconnectivity() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@JRGI-ORAUAT-01.jrgi.com:1521/UAT.ORACLE", "uat",
				"uatjr");
		return con;

	}

	/****************** Validation Functions ******************/

	public void verifyTitle(String et) {
		try {
			String A_title = driver.getTitle();
			String E_Title = GetPropValue(et);
			// Assert.assertEquals(E_title, A_title);
			if (A_title.equals(E_Title)) {
				System.out.println("Title testcase pass");

			} else {
				System.out.println("Title is not the same");
			}

		} catch (Exception e) {

			Assert.fail("Title is not the same");
		}
	}

	public boolean isElementPresentCheckUsingJavaScriptExecutor(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			Object obj = jse.executeScript("return typeof(arguments[0]) != 'undefined' && arguments[0] != null;",
					element);
			if (obj.toString().contains("true")) {
				System.out.println("isElementPresentCheckUsingJavaScriptExecutor: SUCCESS");
				return true;
			} else {
				System.out.println("isElementPresentCheckUsingJavaScriptExecutor: FAIL");
			}

		} catch (NoSuchElementException e) {
			System.out.println("isElementPresentCheckUsingJavaScriptExecutor: FAIL");
		}
		return false;
	}

	public boolean isElementEnable(String xpathEle) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		try {
			driver.findElement(By.xpath(GetObjectPath(xpathEle))).isEnabled();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
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

	public boolean isElementPresent_CSS_Selector(String CSSselector) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		try {
			driver.findElement(By.cssSelector(GetObjectPath(CSSselector))).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public boolean isElementPresent_ID(String ID) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		try {
			if (driver.findElement(By.id(GetObjectPath(ID))).isDisplayed())
				return true;
			else
				return false;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public boolean verifyText(String xpathEle, String Expected_text) {
		try {
			String Actual_text = driver.findElement(By.xpath(GetObjectPath(xpathEle))).getText();
			Assert.assertEquals(Actual_text, Expected_text, "Message is not the same");
			if (Actual_text.equals(Expected_text)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "testcase Failed");
			Assert.fail("Message is not the same");
			return false;
		}

	}

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

	/************************ Set Result to Excel ************************/

	public static void Result_to_Xls(Xlsx_Reader xls, String SheetName, int RowNum, String Result,
			String Result_columnname) {

		// int rNum=Integer.parseInt(RowNum);

		xls.setCellData(SheetName, Result_columnname, RowNum, Result, colSRowNum);
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

	// public static void TransactionHistorydatbasevalidation(String
	// newClaimNo,Hashtable<String,String> data ) throws ClassNotFoundException,
	// SQLException{
	// try{ Connection con = oracleconnectivity();
	//
	// StringBuilder sb = new StringBuilder();
	// sb.append(" Select ct.h53_ndate as EntryDate, ct.u10_clmnum as ClaimUnit,
	// na.b27_name1 as Claimant, ")
	// .append(" tt.d32_description as TransactionDescription, ")
	// .append(" Case When ct.d32_transact in ('LO','EX','UA') and
	// ct.d32_transact_act in ('OP','RO','MD','CL') Then ct.d64_amount End as
	// Reserve, ")
	// .append(" Case When ct.d32_transact in ('LO','EX','UA') and
	// ct.d32_transact_act in ('PT','FI','SP','VD') Then ct.d64_amount*-1 End as
	// Payment, ")
	// .append("Case When ct.d32_transact in ('DE','SA','SU','CO') and
	// ct.d32_transact_act in ('OP','RO','MD','CL') Then ct.d64_amount End as
	// Recoverable, ")
	// .append(" Case When ct.d32_transact in ('DE','SA','SU','CO') and
	// ct.d32_transact_act in ('PT','FI','SP','VD') Then ct.d64_amount * -1 End
	// as NetRecovery, ")
	// .append(" ct.b27_pay_to as PayTo1ReceiveFrom, ct.d61_check_date as
	// CheckDate, ")
	// .append(" ct.d62_check_number as Check# , mc.manual_check_type_desc as
	// ManualCheckType, ct.original_claim_number as OriginatingClaim#,
	// ct.f16_reason as Comments, ct.memo as Memo, ct.f58_userid as UserID, ")
	// .append(" Case When ct.f16_approved = '0' or ct.f16_approved is null then
	// 'No'Else 'Yes'End as Approved,ct.f58_approval_userid as ApprovedBy, ")
	// .append(" rs.risk_share_type_description as RiskShareType from
	// CTRANSACTIONS ct ")
	// .append(" join creserve cr on ct.b69_claim_occur = cr.b69_claim_occur and
	// ct.u10_clmnum = cr.u10_clmnum ")
	// .append(" join name_address na on cr.e04_claimant_num = na.e04_orignum ")
	// .append(" join LD32_TRANSACT_TYPE tt on ct.d32_transact = tt.d32_transact
	// and ct.d32_transact_act = tt.d32_transact_act and ct.d32_reserve =
	// tt.d32_reserve ")
	// .append(" left join cpayee_addresses pa on ct.b69_claim_occur =
	// pa.b69_claim_occur and ct.t91_ctrans_key = pa.t91_ctrans_key ")
	// .append(" left join manual_check_types mc on ct.manual_check_type =
	// mc.MANUAL_CHECK_TYPE_ID left join risk_share_types rs on
	// ct.risk_share_type = rs.risk_share_type ")
	// .append(" where ct.B69_CLAIM_OCCUR = '"+newClaimNo+"' and na.e04_next is
	// null order by ct.h53_ndate asc, ct.t91_ctrans_key ");
	//
	//
	// PreparedStatement ps = con.prepareStatement(sb.toString());
	// ResultSet rs= ps.executeQuery();
	// ResultSetMetaData rsmd = rs.getMetaData();
	// int i=1;
	// while(rs.next()){
	//
	// int column=rsmd.getColumnCount();
	//
	// for(int col=1;col<=column;col++)
	// {
	// System.out.println(rs.getString(col));
	// str1="//div[@id='section_transactionHistorySection']//div[3]//tbody//tr[";
	// str2="]//td[";
	// str3="]";
	// String xpath=str1+(i+1)+str2+(col+1)+str3;
	// String actual = getTextByXpath(xpath);
	// System.out.println(actual);
	//
	// if(col==1 && rs.getString(col)!=null){
	// try{
	// String EntrydateString= rs.getString(col);
	// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date EntryDatefromdatabase = df.parse(EntrydateString);
	//
	// df = new SimpleDateFormat("MM/dd/yyyy");
	// Date EntrydatefromApp= df.parse(actual) ;
	//
	//
	// DateFormat df_target = new SimpleDateFormat("dd/MM/yyyy");
	// String newEntrydatefromdatabase=df_target.format(EntryDatefromdatabase);
	// String newEntrydatefromApp=df_target.format(EntrydatefromApp);
	//
	// // System.out.println(newEntrydatefromdatabase);
	// // System.out.println(newEntrydatefromApp);
	//
	// Assert.assertEquals(newEntrydatefromApp, newEntrydatefromdatabase);
	// test.log(LogStatus.INFO, "Application 'Entrydate' is matching with
	// Database 'EntryDate' from Transaction History Detail for the transaction
	// record" +i);
	//
	// }
	// catch (ParseException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// else if (col==1 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'Entrydate' is matching with
	// Database 'EntryDate' from Transaction History Detail for the transaction
	// record" +i);
	// }
	// else if(col==2 && rs.getString(col)!=null){
	//
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'ClaimUnit#' is matching with
	// Database 'ClaimUnit#' from Transaction History Detail for the transaction
	// record" +i);
	// }
	// else if (col==2 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'ClaimUnit#' is matching with
	// Database 'ClaimUnit#' from Transaction History Detail for the transaction
	// record" +i);
	// }
	// else if(col==3 && rs.getString(col)!=null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'Claimant' is matching with
	// Database 'Claimant' from Transaction History Detail for the transaction
	// record" +i);
	// }
	// else if (col==3 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'Claimant' is matching with
	// Database 'Claimant' from Transaction History Detail for the transaction
	// record" +i);
	// }
	// else if(col==4 && rs.getString(col)!=null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'Transaction Description' is
	// matching with Database 'Transaction Description' from Transaction History
	// Detail for the transaction record" +i);
	// }
	// else if (col==4 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'Transaction Description' is
	// matching with Database 'Transaction Description' from Transaction History
	// Detail for the transaction record" +i);;
	// }
	// else if(col==5 && rs.getString(col) != null){
	// double reserve=Double.parseDouble(rs.getString(col));
	//
	// DecimalFormat df = new DecimalFormat("0.00");
	// df.setMaximumFractionDigits(3);
	// String reservefromDB =df.format(reserve);
	// System.out.println(reservefromDB);
	//
	// //String[] s= actual.split("\\s+");
	// String[] s= actual.split("\\$");
	// String reservefromApp=s[0]+s[1].replaceAll(",", "").trim();
	// System.out.println(reservefromApp);
	//
	// Assert.assertEquals(reservefromApp, reservefromDB);
	// test.log(LogStatus.INFO, "Application 'Reserve' is matching with Database
	// 'Reserve' from Transaction History Detail for the transaction record"
	// +i);
	// }
	// else if (col==5 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'Reserve' is matching with Database
	// 'Reserve' from Transaction History Detail for the transaction record"
	// +i);
	// }
	// else if(col==6 && rs.getString(col) != null){
	// double payment=Double.parseDouble(rs.getString(col));
	//
	// DecimalFormat df = new DecimalFormat("0.00");
	// df.setMaximumFractionDigits(3);
	// String paymentfromDB =df.format(payment);
	// System.out.println(paymentfromDB);
	//
	// String[] s= actual.split("\\s+");
	// String paymentfromApp=s[1].replaceAll(",", "").trim();
	// System.out.println(paymentfromApp);
	//
	// Assert.assertEquals(paymentfromApp, paymentfromDB);
	// test.log(LogStatus.INFO, "Application 'Payment' is matching with Database
	// 'Payment' from Transaction History Detail for the transaction record"
	// +i);
	// }
	// else if (col==6 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'Payment' is matching with Database
	// 'Payment' from Transaction History Detail for the transaction record"
	// +i);
	// }
	// else if(col==7){
	// String RecoverablefromDB="";
	// if(rs.getString(col) != null)
	// {
	// double payment=Double.parseDouble(rs.getString(col));
	//
	// DecimalFormat df = new DecimalFormat("0.00");
	// df.setMaximumFractionDigits(3);
	// String recoverablefromDB =df.format(payment);
	// System.out.println(recoverablefromDB);
	// if(actual.startsWith("-")){
	// String[] s= actual.split("\\$");
	// String recoverablefromapp=s[0]+s[1].replace(",", "").trim();
	// Assert.assertEquals(recoverablefromDB, recoverablefromapp);
	// }
	// else{
	// String[] s= actual.split("\\s+");
	//
	// String recoverablefromApp=s[1].replaceAll(",", "").trim();
	// System.out.println(recoverablefromApp);
	//
	// Assert.assertEquals(recoverablefromDB, recoverablefromApp);
	//
	// }
	// }
	//
	// test.log(LogStatus.INFO, "Application 'Recoverable' is matching with
	// Database 'Recoverable' from Transaction History Detail for the
	// transaction record" +i);
	// }
	// else if (col==7 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'Recoverable' is matching with
	// Database 'Recoverable' from Transaction History Detail for the
	// transaction record" +i);
	// }
	//
	// else if(col==8){
	// String NetRecoveryfromDB="";
	// if(rs.getString(col) != null)
	// {
	// double payment=Double.parseDouble(rs.getString(col));
	//
	// DecimalFormat df = new DecimalFormat("0.00");
	// df.setMaximumFractionDigits(3);
	// String NetrecoverablefromDB =df.format(payment);
	// System.out.println(NetrecoverablefromDB);
	//
	// String[] s= actual.split("\\s+");
	// String NetrecoverablefromApp=s[1].replaceAll(",", "").trim();
	// System.out.println(NetrecoverablefromApp);
	// //NetRecoveryfromDB=rs.getString(col);
	//
	// Assert.assertEquals(NetrecoverablefromDB, NetrecoverablefromApp);
	// test.log(LogStatus.INFO, "Application 'NetRecovery' is matching with
	// Database 'NetRecovery' from Transaction History Detail for the
	// transaction record" +i);
	// }
	// }
	// else if (col==8 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'NetRecovery' is matching with
	// Database 'NetRecovery' from Transaction History Detail for the
	// transaction record" +i);
	// }
	//
	// else if(col==9 && rs.getString(col) != null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'PayTo1ReceiveFrom' is matching
	// with Database 'PayTo1ReceiveFrom' from Transaction History Detail for the
	// transaction record" +i);
	//
	// }
	// else if (col==9 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'PayTo1ReceiveFrom' is matching
	// with Database 'PayTo1ReceiveFrom' from Transaction History Detail for the
	// transaction record" +i);
	// }
	//
	// else if(col==10 && rs.getString(col) != null){
	// try{
	// String CheckdateString= rs.getString(col);
	// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date CheckDatefromdatabase = df.parse(CheckdateString);
	//
	// df = new SimpleDateFormat("MM/dd/yyyy");
	// Date CheckdatefromApp= df.parse(actual) ;
	//
	//
	// DateFormat df_target = new SimpleDateFormat("dd/MM/yyyy");
	// String newCheckdatefromdatabase=df_target.format(CheckDatefromdatabase);
	// String newCheckdatefromApp=df_target.format(CheckdatefromApp);
	//
	// // System.out.println(newEntrydatefromdatabase);
	// // System.out.println(newEntrydatefromApp);
	//
	// Assert.assertEquals(newCheckdatefromApp, newCheckdatefromdatabase);
	// test.log(LogStatus.INFO, "Application 'CheckDate' is matching with
	// Database 'CheckDate' from Transaction History Detail for the transaction
	// record" +i);
	//
	// }
	// catch (ParseException e) {
	// e.printStackTrace();
	// }
	//
	//
	// }
	// else if (col==10 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'CheckDate' is matching with
	// Database 'CheckDate' from Transaction History Detail for the transaction
	// record" +i);
	// }
	// else if(col==11 && rs.getString(col) == null){
	//
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'Check#' is matching with Database
	// 'Check#' from Transaction History Detail for the transaction record" +i);
	// }
	// else if(col==11 && rs.getString(col) != null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'Check#' is matching with Database
	// 'Check#' from Transaction History Detail for the transaction record" +i);
	// }
	//
	// else if (col==12 && rs.getString(col) == null){
	//
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'ManualCheckType' is matching with
	// Database 'ManualCheckType' from Transaction History Detail for the
	// transaction record" +i);
	// }
	// else if(col==12 && rs.getString(col) != null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'ManualCheckType' is matching with
	// Database 'ManualCheckType' from Transaction History Detail for the
	// transaction record" +i);
	// }
	// else if(col==13 && rs.getString(col) == null ) {
	// Assert.assertTrue(actual.isEmpty() && rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'OriginatingClaim#' is matching
	// with Database 'OriginatingClaim#' from Transaction History Detail for the
	// transaction record" +i);
	// }
	// else if(col==13 && rs.getString(col)!=null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'OriginatingClaim#' is matching
	// with Database 'OriginatingClaim#' from Transaction History Detail for the
	// transaction record" +i);
	// }
	// else if(col==14 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'For/Comment' is matching with
	// Database 'For/Comment' from Transaction History Detail for the
	// transaction record" +i);
	//
	// }
	// else if(col==14 && rs.getString(col) != null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'For/Comment' is matching with
	// Database 'For/Comment' from Transaction History Detail for the
	// transaction record" +i);
	// }
	// else if (col==15 && rs.getString(col) != null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'MEMO' is matching with Database
	// 'MEMO' from Transaction History Detail for the transaction record" +i);
	//
	// }
	// else if (col==15 && rs.getString(col) == null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'MEMO' is matching with Database
	// 'MEMO' from Transaction History Detail for the transaction record" +i);
	// }
	//
	// else if (col==16 && rs.getString(col)==null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'USERID' is matching with Database
	// 'USERID' from Transaction History Detail for the transaction record" +i);
	//
	// }
	// else if (col==16 && rs.getString(col)!=null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'USERID' is matching with Database
	// 'USERID' from Transaction History Detail for the transaction record" +i);
	// }
	// else if(col==17 && rs.getString(col)==null){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'APPROVED' is matching with
	// Database 'APPROVED' from Transaction History Detail for the transaction
	// record" +i);
	//
	// }
	// else if(col==17 && rs.getString(col)!=null){
	// if(actual.equals("Y")){
	// Assert.assertTrue(actual.equals("Y") && rs.getString(col).equals("Yes"));
	// test.log(LogStatus.INFO, "Application 'APPROVED' is matching with
	// Database 'APPROVED' from Transaction History Detail for the transaction
	// record" +i);
	// }
	// else if(actual.equals("N")){
	// Assert.assertTrue(actual.equals("N") && rs.getString(col).equals("No"));
	// test.log(LogStatus.INFO, "Application 'APPROVED' is matching with
	// Database 'APPROVED' from Transaction History Detail for the transaction
	// record" +i);
	// }
	// }
	// else if(col==18 && rs.getString(col)== null ){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'APPROVED BY' is matching with
	// Database 'APPROVED BY' from Transaction History Detail for the
	// transaction record" +i);
	// //
	// }
	// else if (col==18 && rs.getString(col)!=null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'APPROVED BY' is matching with
	// Database 'APPROVED BY' from Transaction History Detail for the
	// transaction record" +i);
	// }
	// else if(col==19 && rs.getString(col)==null ){
	// Assert.assertTrue(actual.isEmpty()&& rs.getString(col) == null);
	// test.log(LogStatus.INFO, "Application 'RISKSHARETYPE' is matching with
	// Database 'RISKSHARETYPE' from Transaction History Detail for the
	// transaction record" +i);
	// }
	// else if (col==19 && rs.getString(col)!=null){
	// Assert.assertEquals(actual, rs.getString(col));
	// test.log(LogStatus.INFO, "Application 'RISKSHARETYPE' is matching with
	// Database 'RISKSHARETYPE' from Transaction History Detail for the
	// transaction record" +i);
	// }
	//
	// }
	// }
	// }
	// catch(AssertionError ex){
	// reportFail(ex.getMessage());
	// int rowNum2=Integer.parseInt(data.get("row"));
	// Result_to_Xls(xls,"TestData",rowNum2,"Fail","Result");
	// org.testng.Assert.fail("Expected and actual value dont match");
	// }
	//
	//
	// }
}
