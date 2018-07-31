package by.htp.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import by.htp.steps.Steps;

public class BaseTest {
	Steps steps = new Steps();

	@BeforeMethod(description = "Init browser")
	public void setUp() {
		steps = new Steps();
		steps.initBrowser();
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser() {
		steps.closeDriver();
	}
}
