package com.newstart.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.newstart.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	 public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@class='form-control']")
	List<WebElement> userNameAndPass;
	
	@FindBy(xpath="//input[@class='btn btn-small']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement signupBtn;
	
	@FindBy(xpath="(//img[contains(@class,'img-responsive')])[1]")
	WebElement crmLogo;
	
	
	public String validatePageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	
	public HomePage login(String user,String pass) {
		userNameAndPass.get(0).sendKeys(user);
		userNameAndPass.get(1).sendKeys(pass);
		loginBtn.click();
		
		return new HomePage();

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
