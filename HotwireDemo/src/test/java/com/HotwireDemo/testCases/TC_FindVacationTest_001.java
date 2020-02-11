package com.HotwireDemo.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.HotwireDemo.pageObject.FindVacationPage;

public class TC_FindVacationTest_001 extends BaseClass {

	@Test
	public void find_VacationTest_001() throws InterruptedException, IOException {
		FindVacationPage obj = new FindVacationPage(driver);
		obj.find_Vacationfun();

		// validate the Test case pass or Fail
		Assert.assertTrue(obj.toValidatePage());
		// to check the validation using page title
		if (driver.getTitle().equals("Los Angeles (and vicinity) Hotel Search Results | Hotwire!!!")) {
			Assert.assertTrue(true);
			logger.info("find_VacationTest_001 Passed");
		} else {
			captureScreen(driver, "find_VacationTest_001");
			Assert.assertTrue(false);
			logger.info("find_VacationTest_001 Failed");
			
		}
	}
	
	@Test
	public void find_VacationTest_002() throws InterruptedException, IOException {
		FindVacationPage obj = new FindVacationPage(driver);
		obj.find_Vacationfun();
}
}
