package TestCases;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import ApplicationLayer.ApplicationFun;
import AutoPracticePackage.BaseClass;
import PlatformLayer.webKeywords;
import Utilities.Constants;

import ApplicationLayer.ApplicationFun;

public class TestWebApp 
{
	
	  BaseClass bs = new BaseClass();
	  String emailId="Reshmi@gmail.com";
	
	  @BeforeSuite
	  public void beforeSuite() throws IOException  
	  {
		  System.out.println("========== In beforeSuite ==========");
		  // Configure Log4j
		  bs.ConfigureLog4j();		  
		  
		  // Load OR		  
		  bs.loadOR();	
		  System.out.println("========== Out beforeSuite ==========");
	  }
	  
	  @BeforeTest
	  public void beforeTest() 
	  {	
		  System.out.println("========== In beforeTest ==========");
		  
		  // Load test data
		  try 
		  {
			  bs.testDataMap = BaseClass.testDataLoader(Constants.testDataFileName,"createAccount");
			
		  } catch (FilloException e) 
		  {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  } 
			  
		  // Launches App
		  System.out.println("Launches Application"); 
		  BaseClass.LaunchApp();	
		  
		  System.out.println("========== Out beforeTest ==========");
	  }
  
  
	  @Test
	  public void TestScenario() throws InterruptedException 
	  {
		  System.out.println("========== In Test ==========");
		  
		  // Creates an account with incorrect ZIP
		  BaseClass.testDataMap.put("postalCode", "123");
		  ApplicationFun.createAccount();
	  
		  // Verify zip error
		  String actualErrorMsg = webKeywords.webElement_getText("Obj_Lbl_ZipError");
		  Assert.assertTrue(actualErrorMsg.contains(ApplicationFun.ZipErrorMsg));
		  
		  // Creates an account with correct ZIP
		  BaseClass.testDataMap.put("postalCode", "66546");
		  ApplicationFun.createAccount();
		 
		  // Add first product
		  ApplicationFun.addProductToCart("Product1");
		  
		  // Add second product
		  ApplicationFun.addProductToCart("Product2");
		  
		  // Complete the purchase
		  ApplicationFun.completeThePurchase();
		  
		  System.out.println("========== Out Test ==========");
		  
	  }
  
  @AfterTest
  public void afterTest() 
  {
	  
	  System.out.println("========== In afterTest ==========");
	//  driver.close();
	  System.out.println("========== Out afterTest ==========");
	  
  }
}


