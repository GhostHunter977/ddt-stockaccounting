package commonFunctions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.PropertyFileUtil;
public class FunctionLibrary {
	public static WebDriver driver;
	public static String expectedValue ="";
//method for browser launching
	public static WebDriver startBrowser()throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D://testmind//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\testmind\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else
		{
		System.out.println("Browser value is Not Matching");	
		}
		return driver;
	}
	//method for launching url
	public static void openUrl(WebDriver driver)throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("Url"));
	}
	//method for wait for element
	public static void waitForElement(WebDriver driver,String LocatorType,String LocatorValue,String testData)
	{
		WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(testData));
		if(LocatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		}
		else
		{
			System.out.println("Unable to execute Wait for element method");
		}
	}
	//method for textbox
	public static void typeAction(WebDriver driver,String Locatortype,String Locatorvalue,String testdata)
	{
		if(Locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locatorvalue)).clear();
			driver.findElement(By.xpath(Locatorvalue)).sendKeys(testdata);
		}
		else if(Locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locatorvalue)).clear();
			driver.findElement(By.name(Locatorvalue)).sendKeys(testdata);	
		}
		else if(Locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(Locatorvalue)).clear();
			driver.findElement(By.id(Locatorvalue)).sendKeys(testdata);
		}
		else
		{
			System.out.println("Unable to execute type action method");
		}
	}
	//method for buttons
	public static void clickAction(WebDriver driver,String LocatorType,String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
		else
		{
			System.out.println("Unable to Execute clickaction method");
		}
	}
	//method for title validation
	public static void validateTitle(WebDriver driver,String expectedtitle)
	{
		String actualtitle=driver.getTitle();
		//try 
		{
		System.out.println("acturla title is"+actualtitle+ "expected titile" +expectedtitle );
		Assert.assertEquals(actualtitle, expectedtitle,"Title not match Test fail");
		}
		//catch(Throwable t)
		//{
		//	System.out.println(t.getMessage());
		//}
	//close browser
		
	}
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	//method for capture data
	public static void captureData(WebDriver driver,String LocatorType,String LocatorValue)
	{
		
		if(LocatorType.equalsIgnoreCase("name"))
		{
			expectedValue =driver.findElement(By.name(LocatorValue)).getAttribute("value");
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			expectedValue =driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
		}
		System.out.println(expectedValue);
	}
	//method supplier table
	public static void supplierTable(WebDriver driver)throws Throwable
	{
		if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox"))).isDisplayed())
		Thread.sleep(3000);
		//if not displayed click search panel
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-panel"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox"))).sendKeys(expectedValue);
		Thread.sleep(3000);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-button"))).click();
		Thread.sleep(3000);
		String actualvalue =driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
//		WebElement wb =  driver.findElement(By.id("tbl_a_supplierslist"));
//		List<WebElement> tr = wb.findElements(By.tagName("tr"));
//		for (int i = 1; i < tr.size(); i++) 
//		{
//			List<WebElement> td= tr.get(i).findElements(By.tagName("td"));
//			for (int j = 0; j < td.size(); j++) 
//			{
//			String data	=td.get(j).getText();
//			System.out.println(data);
//				
//			}
//			
//		}
		
		System.out.println(expectedValue+"      "+actualvalue);
		Assert.assertEquals(actualvalue, expectedValue,"customer no not match test fail....");
		
	}
	
	//method customers table
		public static void customerTable(WebDriver driver)throws Throwable
		{
			if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox"))).isDisplayed())
			Thread.sleep(3000);
			//if not displayed click search panel
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-panel"))).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox"))).sendKeys(expectedValue);
			Thread.sleep(3000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-button"))).click();
			Thread.sleep(3000);
			String actualvalue =driver.findElement(By.xpath("//table[@id='tbl_a_customerslist']/tbody/tr[1]/td[5]/div/span/span")).getText();
			
			//String actualvalue =driver.findElement(By.xpath("//tr[@id='r1_a_customers']/td[5]/div/span/span")).getText();
			
			
			System.out.println(expectedValue+"      "+actualvalue);
			Assert.assertEquals(actualvalue, expectedValue,"customer no not match test fail....");
			
		}
		
		public static void mouseMove(WebDriver driver,String LocatorType,String LocatorValue)
		{
			if (LocatorType.equalsIgnoreCase("xpath")) 
			{
				Actions ac = new Actions(driver);
				ac.moveToElement(driver.findElement(By.xpath(LocatorValue))).perform();		
				
			}
			else {
				System.out.println("insety locator only xpath avail...");
			}

		}
		
		public static void mouseClick(WebDriver driver, String LocatorType, String LocatorValue)
		{
			if (LocatorType.equalsIgnoreCase("xpath")) 
			{
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath(LocatorValue)));
				act.click();
				act.build().perform();
			}
			else {
				System.out.println("insety locator only xpath avail...");
			}
		}
		
		public static void stockTable(WebDriver driver,String expecteddata) throws Throwable 
		{
			if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox"))).isDisplayed())
				Thread.sleep(3000);
				//if not displayed click search panel
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-panel"))).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox"))).sendKeys(expecteddata);
				Thread.sleep(3000);
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-button"))).click();
				Thread.sleep(3000);
				String actualdata = driver.findElement(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr[1]/td[4]/div/span/span")).getText();
				System.out.println(expecteddata+"      "+actualdata);
				Assert.assertEquals(actualdata, expecteddata,"stock categories is not match test fail....");
				
				
		}
		
		public static String generateDate()
		{
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("YYYY_MM__DD HH__MM__SS");
			return df.format(date);
			
			
		}
	
	
	
	
	
	
	
	
	
}










