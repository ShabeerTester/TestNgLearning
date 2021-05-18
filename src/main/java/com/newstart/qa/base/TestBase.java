package com.newstart.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.newstart.qa.utils.TestWait;
import com.newstart.qa.utils.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream input = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/newstart/qa/config/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public String getProp(String key) throws Exception{ String value="";
	 * Properties properties=new Properties(); FileInputStream input = new
	 * FileInputStream( System.getProperty("user.dir") +
	 * "/src/main/java/com/newstart/qa/config/config.properties");
	 * properties.load(input); value=properties.getProperty(key); if
	 * (Objects.isNull(value)){ throw new
	 * Exception("Property name"+key+" is not found"); } return value;
	 * 
	 * }
	 */

	public static void initDriver() {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		/*e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;*/
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestWait.PAGELOAD_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestWait.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

	/*
	 * @BeforeTest public void setUp() throws Exception { initDriver(); }
	 * 
	 * @AfterTest public void tearDown() { if (driver != null) { driver.quit(); } }
	 */

}
