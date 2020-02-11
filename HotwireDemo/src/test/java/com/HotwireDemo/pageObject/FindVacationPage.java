package com.HotwireDemo.pageObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindVacationPage {
	WebDriver driver;
	// DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	// Calendar cal = Calendar.getInstance();
	LocalDate today = LocalDate.now();

	@FindBy(xpath = ("//*[@id='header']/nav/div/ul/li[4]/a"))
	WebElement Click_Vacation;

	@FindBy(xpath = "//button[contains(text(),'Flight')]")
	WebElement chk_Flight;

	@FindBy(xpath = "//button[contains(text(),'Hotel')]")
	WebElement chk_Hotel;

	@FindBy(xpath = "//button[contains(text(),'Car')]")
	WebElement chk_Car;
	@FindBy(xpath = "//input[@id='farefinder-package-origin-location-input']")
	WebElement ddl_Flyfrom;
	@FindBy(css = "#farefinder-package-destination-location-input")
	WebElement ddl_FlyTo;

	@FindBy(css = "#farefinder-package-startdate-input")
	WebElement cal_DepartingDate;
	@FindBy(css = "select[name='farefinder-package-pickuptime-input']")
	WebElement sel_DepartingTime;

	@FindBy(css = "#farefinder-package-enddate-input")
	WebElement cal_ReturningDate;

	@FindBy(css = "#farefinder-package-dropofftime-input")
	WebElement sel_ReturningTime;

	@FindBy(xpath = "//button[@id='farefinder-package-search-button']")
	WebElement btn_FindDeal;
	
	@FindBy(xpath = "//span[@class='msi-label-refresh'][contains(text(),'Choose your hotel')]")
	WebElement validatePage;

	public FindVacationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void find_Vacationfun() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Click_Vacation.click();
		chk_Flight.click();
		chk_Hotel.click();
		chk_Car.click();
		// select Departing location
		ddl_Flyfrom.sendKeys("SFO");
		Thread.sleep(2000);
		ddl_Flyfrom.sendKeys(Keys.ENTER);
		// select destination location
		Thread.sleep(2000);
		ddl_FlyTo.sendKeys("LAX");
		Thread.sleep(2000);
		ddl_FlyTo.sendKeys(Keys.DOWN);
		ddl_FlyTo.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		// Select Departing Date and Time

		String departDate = today.plusDays(1).toString();
		System.out.println(departDate + "Next day Date");
		cal_DepartingDate.clear();
		cal_DepartingDate.sendKeys(departDate);
		sel_DepartingTime.sendKeys("Evening");
		Select sel_DepartTime = new Select(sel_DepartingTime);
		sel_DepartTime.selectByVisibleText("Evening");

		// Returning date and time

		LocalDate cal_week = today.plus(3, ChronoUnit.WEEKS);
		LocalDate next3Week = cal_week.plusDays(1);
		String returndate = next3Week.toString();

		System.out.println("returndate is : " + returndate);

		cal_ReturningDate.clear();
		cal_ReturningDate.sendKeys(returndate);
		Select sel_ReturnTime = new Select(sel_ReturningTime);
		sel_ReturnTime.selectByVisibleText("Morning");
		btn_FindDeal.click();
	WebDriverWait wait=new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='msi-label-refresh'][contains(text(),'Choose your hotel')]")));
		System.out.println("Validate Page : "+validatePage.getText());
		System.out.println("Page Tilte is  "+ driver.getTitle());
	}
	
	public boolean toValidatePage()
	{
		if(validatePage.getText().equalsIgnoreCase("Choose your hotel"))

		{
			return true;
		}
		else
		{
			return false;
		}
	
		
	}

}