package com.newstart.qa.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.newstart.qa.base.TestBase;

public class Utility extends TestBase {
	Actions action;
	Select select;

	public Utility() {
		action = new Actions(driver);
	}

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	public void mouseHoverAction(WebElement element) {
		action.moveToElement(element).perform();
	}
	
	public void selectUsingText(WebElement element,String text) {
		select=new Select(element);
		select.selectByVisibleText(text);
	}
	
	public static void takeScreenshotForFailTC() throws IOException {
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir+ "/screenshot/" +System.currentTimeMillis()+ ".png"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
