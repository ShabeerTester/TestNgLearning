package com.newstart.qa.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.newstart.qa.base.TestBase;
import com.newstart.qa.pages.ContactsPage;
import com.newstart.qa.pages.HomePage;
import com.newstart.qa.pages.LoginPage;
import com.newstart.qa.pages.NewContactPage;
import com.newstart.qa.utils.DataSource;

public class AddingNewContactPageTest extends TestBase {

	LoginPage login;
	HomePage homePage;
	NewContactPage newContact;

	public AddingNewContactPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp1() throws Exception {
		initDriver();
		login = new LoginPage();
		homePage = login.login(prop.getProperty("username"), prop.getProperty("password"));
		newContact = homePage.getContactHeader("New Contact");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(dataProviderClass = DataSource.class, dataProvider = "dp")
	public void addingNewContact(Map<String, String> data) throws InterruptedException {
		newContact.fillContactDetails(data.get("Title"), data.get("FirstName"), data.get("LastName"),
				data.get("Company"));
		String actualName= data.get("FirstName")+" "+data.get("LastName");
		Assert.assertEquals(actualName, newContact.nameForAssert());
	}

}
