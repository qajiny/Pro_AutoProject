package PlatformLayer;

import AutoPracticePackage.BaseClass;
import Utilities.Constants;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.asserts.*;

public class webKeywords {
	
	// Clicks on WebElement
	public static void webElement_Click(String webElement)
	{
		JavascriptExecutor js = (JavascriptExecutor) BaseClass.driver;
		WebElement we = BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty(webElement)));
		js.executeScript("arguments[0].scrollIntoView();", we);
		
		we.click();
		System.out.println("Clicked on " + webElement);
	}
	
	// SendKeys to WebElement
	public static void webElement_Type(String webElement,String sText)
	{
		WebElement we = BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty(webElement)));
		we.clear();
		we.sendKeys(BaseClass.testDataMap.get(sText));
		System.out.println("Typed in " + webElement + " : " + sText);
		
		String sText1 = we.getText();
		System.out.println("Actual value in " + webElement + " : " + sText1);
	}
	
	// Find text of WebElement
	public static String webElement_getText(String webElement)
	{
		WebElement we = BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty(webElement)));
				
		String sText = we.getText();
		System.out.println("Returned value in " + webElement + " : " + sText);
		return sText;
	}
		
	// Select from Select element using index
	public static void webElement_Select(String webElement,Integer index)
	{
		Select select = new Select(BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty(webElement))));	 
	    select.selectByIndex(index);
	    System.out.println("Selected from " + webElement + " : " + index);
	}
	
	// Select from Select element using text
	public static void webElement_SelectVisibleText(String webElement,String sText)
	{
		Select select = new Select(BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty(webElement))));	 
	    select.selectByVisibleText(BaseClass.testDataMap.get(sText));
	}
	
	// Verify WebElement Text , not working
	public static void webElement_VerifyText(String webElement,String expectedText)
	{
		System.out.println("expectedText" + expectedText);
		WebElement we  = BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty("webElement")));
		System.out.println("1111");
		String ActualText = we.getAttribute("href");
		System.out.println("22222");
		ActualText =ActualText.trim();
		System.out.println("33333");
		expectedText = expectedText.trim();
		
		System.out.println("ActualText" + ActualText);
		assertEquals(expectedText, ActualText);
		Assert.assertTrue(ActualText.equalsIgnoreCase("expectedText"));
	}
		
	// Verify an element is displayed
	public static Boolean webElement_ObjectDisplayed(String objectName)
	{

		WebElement we = BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty(objectName)));
		
		boolean flag = we.isDisplayed();
		return flag;
	}
	
	public static void webElement_SendTabTabEnter(String objectName)
	{
		WebElement we = BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty(objectName)));
		we.sendKeys(Keys.TAB);
		we.sendKeys(Keys.TAB);
		we.sendKeys(Keys.ENTER);
	}
	
	public static void webElement_SendTabEnter(String objectName) throws InterruptedException
	{
		WebElement we = BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty(objectName)));
		we.sendKeys(Keys.TAB);
		Thread.sleep(5000);
		
		we.sendKeys(Keys.ENTER);
	}
	
	public static void webElement_IsDisplayed(String objectName)
	{
		WebElement we = BaseClass.driver.findElement(By.xpath(BaseClass.objRepo.getProperty(objectName)));
		boolean flag = we.isDisplayed();
		
	}
	
}
	


