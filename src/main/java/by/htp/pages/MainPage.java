package by.htp.pages;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import by.htp.entity.PriceValue;

public class MainPage extends AbstractPage {

	private List<WebElement> listOfPrices;
	private List<WebElement> listOfDates;
	private List<PriceValue> values = new ArrayList<PriceValue>();
	
	private JavascriptExecutor executor;
	private String js;

	private final String BASE_URL = "https://belavia.by/";
	
	@FindBy(id = "OriginLocation")
	private WebElement jsOriginLocation;

	@FindBy(xpath = "//*[@id='OriginLocation_Combobox']")
	private WebElement originLocation;
	
	@FindBy(id = "DestinationLocation")
	private WebElement jsDestinationLocation;

	@FindBy(xpath = "//*[@id='DestinationLocation_Combobox']")
	private WebElement destinationLocation;

	@FindBy(xpath = "//*[@id='step-2']/div[1]/div/label[1]")
	private WebElement radioButtonOneWay;

	@FindBy(xpath = "//*[@id='step-2']/div[1]/div/label[2]")
	private WebElement radioButtonReturn;

	@FindBy(xpath = "//*[@id='step-2']/div[2]/div[1]/div/a")
	private WebElement departureDate;

	@FindBy(xpath = "//*[@id='step-2']/div[2]/div[2]/div/a")
	private WebElement returnDate;

	@FindBy(xpath = "//div[@id = 'step-2']/div[4]/div/button")
	private WebElement searchButton;

	@FindBy(xpath = "//*[@id='ibe']/div/form/div[5]/div/button")
	private WebElement searchButton2;

	@FindBy(xpath = "//*[@id='calendar']/div/div[1]/div/div")
	private WebElement getLeftBlockOnCalendar;

	@FindBy(xpath = "//*[@id='calendar']/div/div[2]/div/div")
	private WebElement getRightBlockOnCalendar;

	@FindBy(xpath = "//*[@id='calendar']/div/div[1]/div/a/i")
	private WebElement choosePreviousMonthOnCalendar;

	@FindBy(xpath = "//*[@id='calendar']/div/div[2]/div/a/i")
	private WebElement chooseNextMonthOnCalendar;

	@FindBy(xpath = "//*[@id='calendar']/div/div[2]/table/tbody/tr[1]/td[4]/a")
	private WebElement chooseStartDate;

	@FindBy(xpath = "//*[@id='calendar']/div/div[2]/table/tbody/tr[1]/td[5]/a")
	private WebElement chooseSecondDay;

	@FindBy(xpath = "//*[@id='calendar']/div/div[2]/table/tbody/tr[1]/td[5]/a")
	private WebElement chooseLastDate;

	@FindBy(xpath = "//*[@id='outbound']/div[1]/div/div[2]/a")
	private WebElement seePricesFor7Days;

	@FindBy(xpath = "//*[@id='matrix']/div[1]/div[1]/div[2]/a")
	private WebElement nextSevenDays;

	@FindBy(xpath = "//*[@id='ibe']/form/div[1]/div[1]/div/a/i")
	private WebElement dropDownOriginLocation;

	@FindBy(xpath = "//*[@id='ui-id-2']/li/a")
	private WebElement allOriginLocation;

	@FindBy(xpath = "//*[@id='ui-id-953']")
	private WebElement minsk;

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

	public void enterDataForTwoWaysFirstDay() {
		executor = (JavascriptExecutor) driver;

		js = "var s= document.getElementById('OriginLocation');s.type = 'visible'";
		executor.executeScript(js, this.jsOriginLocation);
		this.jsOriginLocation.sendKeys("MSQ");
		this.originLocation.sendKeys("Минск (MSQ) BY");
		
		js = "var s= document.getElementById('DestinationLocation');s.type = 'visible'";
		executor.executeScript(js, this.jsDestinationLocation);
		this.jsDestinationLocation.sendKeys("RIX");
		this.destinationLocation.sendKeys("Riga (RIX), LV");
		

		radioButtonReturn.click();
		departureDate.click();
		chooseStartDate.click();
		chooseNextMonthOnCalendar.click();
		chooseNextMonthOnCalendar.click();
		chooseLastDate.click();
		//Thread.sleep(5000);
		searchButton.click();
	}

	public void enterDataOneMoreTime() {
		originLocation.sendKeys("Minsk");
		destinationLocation.sendKeys("Riga");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		searchButton2.click();

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
			if (we.getText().toUpperCase().endsWith("BYN")) {
				price = Integer.parseInt(we.getText().substring(0, 3));
				if (dates.hasNext()) {
					date = dates.next().getAttribute("value");
				}
				pv = new PriceValue(price, date);
				values.add(pv);
			}
		}
	}

	public void chooseOriginLocationFromDropDown() {
		List<WebElement> allOriginLocations = driver.findElements(By.xpath("//*[@id='ui-id-2']/li/a/strong"));
		String element;
		for (int i = 0; i < allOriginLocations.size(); i++) {
			element = allOriginLocations.get(i).getText();
			if (element.equals("Minsk (MSQ), BY")) {
				allOriginLocations.get(i).click();
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
