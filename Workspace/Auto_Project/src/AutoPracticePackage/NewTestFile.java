package AutoPracticePackage;

import AutoPracticePackage.BaseClass;
import PlatformLayer.Log;
import PlatformLayer.webKeywords;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

//import PlatformLayer.Log4jLog;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Utilities.Constants;
import Utilities.Utils;

import java.lang.Double;

import AutoPracticePackage.BaseClass;

import org.apache.log4j.Logger;

public class NewTestFile 
{  	
	BaseClass bs = new BaseClass();
	String emailId="Reshmi@gmail.com";
	
  @BeforeSuite
  public void beforeSuite() throws IOException {
	  
	  // Configure Log4j
	  bs.ConfigureLog4j();
	  
	  
	  // Load OR
	 // Log4jLog.info("Loading the Object Repository");
	  
	  bs.loadOR();
	//  Log4jLog.info("Loaded the Object Repository successfully");
  }
  
 
  @BeforeTest
  public void beforeTest() 
  {	
	// Load test data
		  try {
			  bs.testDataMap = BaseClass.testDataLoader(Constants.testDataFileName,"createAccount");
			
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  
		  // Launches App
		  System.out.println("Launches Application"); 
		  BaseClass.LaunchApp();
		  
		/*  // Launch Application
	  System.out.println("IN @LaunchApp");
		String baseUrl = "http://automationpractice.com";
	    String driverPath = "C:\\SeleniumJars\\geckodriver.exe";
		  
		System.setProperty("webdriver.firefox.marionette", driverPath);
      BaseClass.driver = new FirefoxDriver();
      BaseClass.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    
      BaseClass.driver.get(baseUrl);
      BaseClass.driver.manage().window().maximize();
		System.out.println("Launched the App");
		System.out.println("OUT @LaunchApp");
		*/
      
  }
    
  @SuppressWarnings("unchecked")
  @Test(enabled = false) 
  public void createAccount() throws InterruptedException
  {	
	  String ZipErrorMsg = "The Zip/Postal code you've entered is invalid. It must follow this format: 00000";
	  
	  Integer RandNo = Utils.CreateRandomNo();
	  emailId = "UserAccount" + RandNo + "@" + "gmail.com";
	  
	  // Screen 1
	  webKeywords.webElement_Click("Obj_Lnk_SignIn");
	  BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty("Obj_Txt_Email"))).sendKeys(emailId);
	  //webKeywords.webElement_Type("Obj_Txt_Email","emailId");
	  webKeywords.webElement_Click("Obj_Btn_CreateAccount");
	  
	  // Screen 2
	  webKeywords.webElement_Click("Obj_Rdo_Mrs");
	  webKeywords.webElement_Type("Obj_Txt_FirstName","firstName");
	  webKeywords.webElement_Type("Obj_Txt_LastName","lastName");
	  webKeywords.webElement_Type("Obj_Txt_Password","passWord");
	  	  
	  // Select DOB
	  webKeywords.webElement_Select("Obj_Sel_Day",19);
	  webKeywords.webElement_Select("Obj_Sel_Month",12);
	  webKeywords.webElement_Select("Obj_Sel_Year",12);
	  	  
	  // Enter data in "Your Address" fields
	  webKeywords.webElement_Type("Obj_Txt_LastNameAdrdess","lastName");
	  webKeywords.webElement_Type("Obj_Txt_CompanyAddress","company");
	  webKeywords.webElement_Type("Obj_Txt_Address","address");
	  webKeywords.webElement_Type("Obj_Txt_CityAddress","city");
	  
	  webKeywords.webElement_SelectVisibleText("Obj_Sel_StateAddress","state");	  	  
	  webKeywords.webElement_SelectVisibleText("Obj_Sel_CountryAddress","country");
	  
	  webKeywords.webElement_Type("Obj_Txt_MobilePhone","mobilePhone");
	  webKeywords.webElement_Type("Obj_Txt_AlternateAddress","aliasAddress");
	  webKeywords.webElement_Click("Obj_Btn_Register");
	  Thread.sleep(5000);
	  
	  // Verify zip error
	  String actualErrorMsg = webKeywords.webElement_getText("Obj_Lbl_ZipError");
	  Assert.assertTrue(actualErrorMsg.contains(ZipErrorMsg));
	  
	  // Enter valid zip
	  webKeywords.webElement_Type("Obj_Txt_ZipAddress","postalCode");
	  webKeywords.webElement_Click("Obj_Btn_Register");
	  	 
	  System.out.println("OUT createAccount"); 	  
  }
  
  @Test(enabled = false) 
  public void SignIn() throws InterruptedException
  {	  
	  webKeywords.webElement_Click("Obj_Lnk_SignIn");
	  BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty("Obj_Txt_EmailSignIn"))).sendKeys(emailId);
	  webKeywords.webElement_Type("Obj_Txt_Password","passWord");
	  Thread.sleep(5000);
	  
	  // SendKeys Tab+Tab+Enter
	  webKeywords.webElement_SendTabTabEnter("Obj_Txt_Password");
  }
  
  @Test(enabled = false) 
  public void addProductToCart(String ProductName)
  {
	  // Search for product
	  webKeywords.webElement_Type("Obj_Txt_Search",ProductName);
	  webKeywords.webElement_Click("Obj_Btn_Search");
	  webKeywords.webElement_Click("Obj_Lnk_List");
	  webKeywords.webElement_Click("Obj_Btn_AddToCart");	  
	  
      webKeywords.webElement_Click("Obj_Btn_CloseCart");      
      
  }
  
  public double addPrice(String price1 , String price2)
  {
	  price1 = price1.replace("$", "");
	  price2 = price2.replace("$", "");
	  
	  double Sum = Double.parseDouble(price1) + Double.parseDouble(price2);
	  System.out.println(price1 + " + " + price2 + " = " + Sum );
	  return Sum;
  }
  
  @Test(enabled = false) 
  public void completeThePurchase() throws InterruptedException
  {
	  // View shopping cart	  
	  webKeywords.webElement_Click("Obj_Lnk_ShowCart");
	  Thread.sleep(5000);
	  
	  webKeywords.webElement_Click("Obj_Lbl_AddOneMore");
	  Thread.sleep(5000);
	  
	  // Fetch product prices
	  String price1 = webKeywords.webElement_getText("Obj_Lbl_ProductPrice1");
	  String price2 = webKeywords.webElement_getText("Obj_Lbl_ProductPrice2");
	  
	  double Sum = addPrice(price1,price2);
	  
	  String TotalProds = webKeywords.webElement_getText("Obj_Lbl_TotalProducts");
	  TotalProds = TotalProds.replace("$", "");
	  
	  double TotalProducts = Double.parseDouble(TotalProds);
	  System.out.println("TotalProducts = " + TotalProducts);
	  System.out.println("Sum = " + Sum);
	  
	  // Put 1 assert here
	  // Verify that TotalProducts should be equal to Sum
	  if (Double.compare(TotalProducts, Sum) == 0) 
	  { 		  
          System.out.println("TotalProducts = Sum"); 
      } 
	  else
	  {
		  System.out.println("TotalProducts <> Sum"); 
	  }
	  
	  String Shipping = webKeywords.webElement_getText("Obj_Lbl_TotalShipping");
	  Shipping = Shipping.replace("$", "");
	 	  
	  String Total = webKeywords.webElement_getText("Obj_Lbl_TotalPriceWithoutTax");
	  Total = Total.replace("$", "");
	  double TotalShipping = Double.parseDouble(Total);
	  
	  Double Total1 = addPrice(Shipping,TotalProds);
	  if (Double.compare(Total1, TotalShipping) == 0) 
	  { 		  
          System.out.println("Shipping total is correct"); 
      } 
	  else
	  {
		  System.out.println("Shipping total is not correct"); 
	  }
	  
	  String PriceWithTax = webKeywords.webElement_getText("Obj_Lbl_TotalPriceWithTax");
	  PriceWithTax = PriceWithTax.replace("$", "");
	  double PriceWithTax1 = Double.parseDouble(PriceWithTax);
	  
	  String Tax = webKeywords.webElement_getText("Obj_Lbl_Tax");
	  Tax = Tax.replace("$", "");	 
	  
	  Double Total2 = addPrice(Tax,Total);
	  //double Tax1= Double.parseDouble(Total2); // Tax + total
	  
	  if (Double.compare(Total2, PriceWithTax1) == 0) 
	  { 		  
          System.out.println("Tax total is correct"); 
      } 
	  else
	  {
		  System.out.println("Tax total is not correct"); 
	  }	  
	  webKeywords.webElement_Click("Obj_Lbl_Tax");
	  Thread.sleep(5000);
	  
	  webKeywords.webElement_Click("Obj_Btn_ProceedToCheckOut");
	  Thread.sleep(10000);
	  
	  // Address page
	  webKeywords.webElement_Click("Obj_Btn_ProceedToCheckOut1");
	 // Thread.sleep(5000);
	  
	  //webKeywords.webElement_Click("Obj_Lbl_MyCarrier");
	 // Thread.sleep(5000);
	  
	  
	  
     // webKeywords.webElement_Click("Obj_Btn_CloseCart");      
     
      
  }
    

  

  

  @AfterSuite
  public void afterSuite() {
  }

}
