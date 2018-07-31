package by.htp.steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import by.htp.driver.DriverSingleton;
import by.htp.entity.PriceValue;
import by.htp.pages.MainPage;
import by.htp.pages.OutboundPage;

public class Steps {

	private WebDriver driver;
	private int numberOfIterations;

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver() {
		DriverSingleton.closeDriver();
	}

	public void initialBelavia() throws InterruptedException {

		numberOfIterations = numberOfWeeks("01-08-2018", "01-11-2018");

		MainPage hp = new MainPage(driver);
		OutboundPage op =new OutboundPage(driver);
		hp.openPage();
		hp.enterOriginAndDestinationLocationUsingJS();
		hp.enterDataForTwoWays();
		pause4Loading(8000);
		
		op.CalendarView();
		pause4Loading(5000);

		if (numberOfIterations == 1) {
			op.getPrices();
		} else {
			for (int i = 0; i < numberOfIterations; i++) {
				op.getPrices();
				op.nextPageSevenDays();
				pause4Loading(5000);
			}
		}
		op.leavePage();
		resultOut(op);
	}

	private void pause4Loading(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int numberOfWeeks(String startDate, String endDate) {
		String[] startDateArr = startDate.split("-");
		int startMonth = (int) Integer.parseInt(startDateArr[0]) / 7 + Integer.parseInt(startDateArr[1]) * 4
				+ Integer.parseInt(startDateArr[2]) * 4 * 12;
		String[] endDateArr = endDate.split("-");
		int endMonth = (int) Integer.parseInt(endDateArr[0]) / 7 + Integer.parseInt(endDateArr[1]) * 4
				+ Integer.parseInt(endDateArr[2]) * 4 * 12;
		int result = endMonth - startMonth;
		if (result <= 0) {
			return 1;
		} else
			return result;
	}

	private void resultOut(OutboundPage page) {
		PriceValue result = page.findMin();
		System.out.println(
				"ћинимальна€ цена равна = " + result.getPrice() + " BYN , вылет туда - обратно: " + result.getDate());
	}

}
