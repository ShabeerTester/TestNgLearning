package com.newstart.qa.pages;

import java.util.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.newstart.qa.base.TestBase;
import com.newstart.qa.utils.Utility;

public class NewContactPage extends TestBase {
	Utility util = new Utility();

	@FindBy(name = "title")
	WebElement titleDropDown;

	@FindBy(xpath = "//input[@id='first_name']")
	WebElement fstNameEle;

	@FindBy(xpath = "//input[@id='surname']")
	WebElement lstNameEle;

	@FindBy(name = "suffix")
	WebElement suffixDropDown;

	@FindBy(xpath = "//input[@name='client_lookup']")
	WebElement compName;

	@FindBy(name = "nickname")
	WebElement nickName;

	@FindBy(xpath = "//input[@value='Load From Company']//following-sibling::input[@value='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//td[@align='left' and @class='tabs_header']")
	WebElement contactName;

	public NewContactPage() {
		PageFactory.initElements(driver, this);
	}
    public String nameForAssert() {
    	return contactName.getText();
    }
	public void fillContactDetails(String title,String fstName,String lstName,String companyName) throws InterruptedException {
		util.selectUsingText(titleDropDown, title);
        if(Objects.nonNull(fstName)){
        fstNameEle.sendKeys(fstName);
        }else {
        	System.out.println("The value from data provider is null");
        }
		lstNameEle.sendKeys(lstName);
		compName.sendKeys(companyName);
		saveBtn.click();
		
	}

}
