package com.HotwireDemo.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.HotwireDemo.Utilities.ReadConfig;

public class BaseClass {

	ReadConfig read_Config = new ReadConfig();

	public String baseurl = read_Config.getApplicationURL();
	
	public WebDriver driver=null;

	public static Logger logger;
	
//this is driver launch code...
	
	@Parameters("browserType")
	@BeforeClass
	public void setup(@Optional String browser) {
		logger = Logger.getLogger("HotwireDemo");
		PropertyConfigurator.configure("log4j.properties");
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", read_Config.getChromePath());
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(baseurl);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		} else if (browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", read_Config.getFirefoxPath());
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(baseurl);
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", read_Config.getInternetExplorerPath());
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}

	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	
	public 	void captureScreen(WebDriver driver, String tname) throws IOException {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
		File target=new File(System.getProperty("user.dir")+"/Screenshots/"+ tname +".png");
		FileHandler.copy(source, target);
		System.out.println("Screenshot has been taken");
		
	}
}
