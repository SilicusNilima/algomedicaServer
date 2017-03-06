package com.algomedica.service;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class AlgomedicaTest {
	
	WebDriver driver=null;
	
   @Test
  public void InvokeBrowser() {
	   
	 System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
	 driver= new ChromeDriver();
	 driver.get("http://localhost:8080/AlgomedicaLS/#/");
  }
	 	 @Test
	 public void Login() throws InterruptedException{
	 		 
	
		 WebElement Username = driver.findElement(By.cssSelector("[name='username']"));
	 	 Username.sendKeys("koutuk");
	      
	     
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	      
	      
	     
	    WebElement password = driver.findElement(By.cssSelector("[name='password']"));
	     password.sendKeys("koutuk");
	
	    driver.findElement(By.cssSelector(".btn.btn-lg.btn-success.btn-block")).click();
	      
	 }   	    
	      @Test
	      public void NewCustomerCreation() throws InterruptedException{
	      
	     driver.findElement(By.cssSelector("[class='btn btn-success pull-left']")).click();
	     
	    
	     WebElement orgName = driver.findElement(By.id("orgName"));
	     orgName.sendKeys("Apollo");
	 
	      WebElement personName = driver.findElement(By.id("personName"));
	      personName.sendKeys("rakesh");
	      
	      WebElement personEmailId = driver.findElement(By.id("personEmailId"));
	      personEmailId.sendKeys("rakesh.pandey@silicus.com");
	      
	      WebElement contact1 = driver.findElement(By.id("contact1"));
	      contact1.sendKeys("9873459870");
	      
	      WebElement contact2 = driver.findElement(By.id("contact2"));
	      contact2.sendKeys("9345098789");
	      
	      WebElement country = driver.findElement(By.id("country"));
	      country.sendKeys("India");
	      
	      WebElement city = driver.findElement(By.id("city"));
	      city.sendKeys("Pune");
	      
	      WebElement State = driver.findElement(By.id("State"));
	      State.sendKeys("Maharashtra");
	      
	      WebElement PIN = driver.findElement(By.id("PIN"));
	      PIN.sendKeys("83459");
	      
	      WebElement address1 = driver.findElement(By.id("address1"));
	      address1.sendKeys("RH no #92");
	      
	      WebElement address2 = driver.findElement(By.id("address2"));
	      address2.sendKeys("RH no #93");
	      
	    	      
	      driver.findElement(By.cssSelector("[class='btn btn-success ng-scope']")).click();
	     
	      }
	      
	      @ Test
	      public void NewMachineDetails() throws InterruptedException{
	    
	     driver.findElement(By.cssSelector("[class='btn btn-primary pull-left']")).click(); 
	     	      
	      WebElement MACNo = driver.findElement(By.id("MACNo"));
	      MACNo.sendKeys("985921096716");
	      
	      WebElement DeviceModel= driver.findElement(By.id("Device Model #"));
	      DeviceModel.sendKeys("Dell Thinkpad");
	      
	      WebElement DeviceBrandName= driver.findElement(By.id("Device Brand Name"));
	      DeviceBrandName.sendKeys("Dell");
	      
	     Select lsType= new Select(driver.findElement(By.xpath(".//*[@id='page-wrapper1']/div[3]/div/div[2]/div/div[2]/div[3]/div/form/fieldset/div[2]/div[1]/div/select")));;
	     lsType.selectByVisibleText("Trial");
	    
	     Select LicenseCategory= new Select(driver.findElement(By.xpath(".//*[@id='page-wrapper1']/div[3]/div/div[2]/div/div[2]/div[3]/div/form/fieldset/div[2]/div[2]/div/select")));
	     LicenseCategory.selectByVisibleText("Small");
	      
	     WebElement LicenseValidity= driver.findElement(By.id("License Validity"));
	     LicenseValidity.sendKeys("30");
	      
	     WebElement LicenseCost= driver.findElement(By.id("License Cost"));
	     LicenseCost.sendKeys("99");
	      
	     WebElement salesOpsName= driver.findElement(By.id("Sales Ops Personnel Name"));
	     salesOpsName.sendKeys("rahul");
	      
	     
	     WebElement SendEmailTo= driver.findElement(By.id("Send Email To"));
	     SendEmailTo.sendKeys("rakesh.pandey@silicus.com");
	     
	       
	     driver.findElement(By.cssSelector("[class='btn btn-success ng-scope']")).click(); 
	     
	      Thread.sleep(3000);
	   	  driver.findElement(By.cssSelector("button[class='close']")).click();
	   	  
	   	  Thread.sleep(3000);
	   	  driver.findElement(By.xpath(".//*[@id='wrapper']/nav/ul/li[1]/a")).click();
	   	  
	   	  
	   	  Thread.sleep(3000);
	   	  driver.findElement(By.xpath(".//*[@id='wrapper']/nav/ul/li[2]/a")).click();
	   	  
	   	  
	   	  Thread.sleep(3000);
	   	  driver.close();
	              
	      	 }
	 
		 }
