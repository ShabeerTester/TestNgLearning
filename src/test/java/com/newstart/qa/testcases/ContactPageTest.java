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

public class ContactPageTest extends TestBase {

	LoginPage login;
	HomePage homePage;
	ContactsPage contactPage;

	public ContactPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp1() throws Exception {
		initDriver();
		login = new LoginPage();
		// homePage = login.login(prop.getProperty("username"),
		// prop.getProperty("password"));
		homePage = login.login(prop.getProperty("username"), prop.getProperty("password"));
		contactPage = homePage.clickonContactsPageLink();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(enabled=false)
	public void verifyContactLableDisplay() {
		Assert.assertTrue(contactPage.verifyContactLablePresent(), "contacts label is missing on the page");
	}

	@Test(enabled=false)
	public void verifyIfContactIsSelected() {
		Assert.assertTrue(contactPage.selectAndPrintName("Ajay Sharma"), "The mentioned contact is not selected");
	}
	
	@Test(enabled=true)
	public void verifyWhetherValidContactPresent() throws InterruptedException {
	Assert.assertTrue(contactPage.selectNameUsingFilter("Tome Peter"));
	}
	
	@Test(enabled=true)
	public void verifyWhetherInvalidContactnotPresent() throws InterruptedException {
	Assert.assertFalse(contactPage.selectNameUsingFilter("Tomeffff Peter"));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
