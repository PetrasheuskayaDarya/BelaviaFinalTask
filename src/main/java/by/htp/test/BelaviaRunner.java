package by.htp.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.steps.Steps;

public class BelaviaRunner extends BaseTest {

	@Test(description = "Getting the min price from all pricec and dates", groups = { "check min price" })
	public void GetAllData() throws InterruptedException {
		steps.initialBelavia();
	}

}
