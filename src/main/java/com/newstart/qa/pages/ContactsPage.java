package com.newstart.qa.pages;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.newstart.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLable;

	@FindBy(xpath = "//a[@context='contact']")
	List<WebElement> allContact;

	@FindBy(xpath = "(//div[@class='pagination'])[1]//child::a")
	List<WebElement> pages;

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public WebElement selectCheckBoxByName(String name) {
		String fullName = "//a[text()='" + name
				+ "']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']";
		return driver.findElement(By.xpath(fullName));
	}

	public boolean verifyContactLablePresent() {
		return contactsLable.isDisplayed();
	}

	public boolean selectAndPrintName(String contactName) {
		boolean flag = false;
		selectCheckBoxByName(contactName).click();
		if (selectCheckBoxByName(contactName).isSelected()) {
			flag = true;
			String contactFullName = "//a[text()='" + contactName + "']";
			String name = driver.findElement(By.xpath(contactFullName)).getText();
			System.out.println("*****Contact name is==" + name);
		} else {
			flag = false;
		}
		return flag;

	}

	public WebElement alphaWebElement(String name) {
		String trail = name.substring(0, 1).toUpperCase();
		return driver.findElement(By.xpath("//td[text()='" + trail + "']"));
	}

	public boolean selectNameUsingFilter(String input) {
		boolean flag = false;
		alphaWebElement(input).click();
		int count = 0;
		do {
			for (int j = 0; j < allContact.size(); j++) {
				String name = allContact.get(j).getText();
				if (name.equalsIgnoreCase(input)) {
					allContact.get(j).click();
					flag = true;
					break;
				}
			}
			if (flag)
				break;
			if (pages.size() > count) {
				pages.get(count).click();
			} else {
				System.out.println("Contact is not available in list");
				break;
			}
			count++;
		} while (flag != true);
		System.out.println("Final Flag is ***" + flag);
		return flag;
	}

}