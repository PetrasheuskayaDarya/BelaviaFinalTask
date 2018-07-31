package by.htp.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import by.htp.entity.PriceValue;

public class OutboundPage extends AbstractPage {
	private List<WebElement> listOfPrices;
	private List<WebElement> listOfDates;
	private List<PriceValue> values = new ArrayList<PriceValue>();
	String currency = "BYN";

	private final String BASE_URL = "https://belavia.by/";

	@FindBy(xpath = "//*[@id='outbound']/div[1]/div/div[2]/a")
	private WebElement seePricesFor7Days;

	@FindBy(xpath = "//*[@id='matrix']/div[1]/div[1]/div[2]/a")
	private WebElement nextSevenDays;

	public OutboundPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);

	}

	public void CalendarView() {
		if (seePricesFor7Days.isDisplayed()) {
			seePricesFor7Days.click();
		}
	}

	public void nextPageSevenDays() {
		nextSevenDays.click();
	}

	public void getPrices() {
		listOfPrices = driver.findElements(By.className("price"));
		listOfDates = driver.findElements(By.name("date"));
		int price;
		PriceValue pv;
		String date = "";

		Iterator<WebElement> dates = listOfDates.iterator();

		for (WebElement we : listOfPrices) {
			if (we.getText().toUpperCase().endsWith(currency)) {
				price = Integer.parseInt(we.getText().substring(0, 3));
				if (dates.hasNext()) {
					date = dates.next().getAttribute("value");
				}
				pv = new PriceValue(price, date);
				values.add(pv);
			}
		}
	}

	public PriceValue findMin() {
		return Collections.min(values);
	}

	public void leavePage() {
		driver.close();
	}

}
