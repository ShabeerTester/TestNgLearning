package com.newstart.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.newstart.qa.base.TestBase;
import com.newstart.qa.utils.Utility;

public class HomePage extends TestBase {

	Utility util = new Utility();

	@FindBy(xpath = "//td[contains(text(),'User: group automation')]")
	WebElement userNameLable;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]//following-sibling::ul//child::li//a")
	List<WebElement> contactHeader;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactPageLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealPageLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement taskPageLink;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyhomePageTitle() {
		return driver.getTitle();
	}

	public String getHomePageTitle() {
		return driver.getTitle();
	}

	public ContactsPage clickonContactsPageLink() {
		util.switchToFrame("mainpanel");
		contactPageLink.click();
		return new ContactsPage();
	}

	public DealsPage clickonDealsPageLink() {
		util.switchToFrame("mainpanel");
		dealPageLink.click();
		return new DealsPage();
	}

	public TasksPage clickonTasksPageLink() {
		util.switchToFrame("mainpanel");
		taskPageLink.click();
		return new TasksPage();
	}

	public boolean verfyUserNameLable() {
		util.switchToFrame("mainpanel");
		return userNameLable.isDisplayed();
	}
	
	
	public NewContactPage getContactHeader(String headerName) {
		selectHomePageHeader(headerName);
		return new NewContactPage();
	}

	public void selectHomePageHeader(String header) {
		util.switchToFrame("mainpanel");
		util.mouseHoverAction(contactPageLink);
		System.out.println("List size" + contactHeader.size());
		for (int i = 0; i < contactHeader.size(); i++) {
			String contactText = contactHeader.get(i).getText();
			System.out.println("Header name==" + contactText);
			if (contactText.equalsIgnoreCase(header)) {
				contactHeader.get(i).click();
				break;
			}
		}
	}

}
