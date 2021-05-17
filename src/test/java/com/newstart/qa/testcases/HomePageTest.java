package com.newstart.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.newstart.qa.base.TestBase;
import com.newstart.qa.pages.ContactsPage;
import com.newstart.qa.pages.HomePage;
import com.newstart.qa.pages.LoginPage;

public class HomePageTest extends TestBase {
	LoginPage login;
	HomePage homePage;
	ContactsPage contact;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initDriver();
		login = new LoginPage();
		homePage = login.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
	@Test(enabled=false,priority=1)
	public void verifyHomePageTitle() {
		Assert.assertEquals(homePage.verifyhomePageTitle(), "CRMPRO", "Hoem page title is not matching");
	}

	
	@Test(enabled=false,priority=2)
	public void verifyUserNameTest() {
		Assert.assertTrue(homePage.verfyUserNameLable());
	}


	@Test(enabled=false,priority=3)
	public void verifyContactLinkTest() {
		contact = homePage.clickonContactsPageLink();
	}
	
	@Test(enabled=true,priority=4)
	public void getContactInfotext() throws InterruptedException {
		homePage.getContactHeader("Combined Form");
	}
	
	
	
	
	
	
	
	
	
	

}
