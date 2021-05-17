package com.newstart.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.newstart.qa.base.TestBase;
import com.newstart.qa.pages.HomePage;
import com.newstart.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage login;
	HomePage homePage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp1() {
		initDriver();
		login = new LoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(enabled=false)
	public void loginPageTitleTest() {
       String expected=login.validatePageTitle();
       System.out.println("Expected title of the page="+expected);
       String actual="CRMPRO - CRM software for customer relationship management, sales, and support.";
       Assert.assertEquals(expected, actual);
	}
	
	@Test(priority=2)
	public void crmLogoImageTest() {
		boolean result=login.validateCRMImage();
		Assert.assertTrue(result);
	}
	
	@Test(priority=3)
	public void loginTest() {
		homePage=login.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
