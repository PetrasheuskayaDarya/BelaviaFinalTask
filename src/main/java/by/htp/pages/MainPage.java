package by.htp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {
	private final String BASE_URL = "https://belavia.by/";

	@FindBy(xpath = "//*[@id='OriginLocation_Combobox']")
	private WebElement originLocation;

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
	
	@FindBy(xpath = "//*[@id='step-2']/div[4]/div/button")
	private WebElement submitButton;
	
	@FindBy(xpath = "//*[@id='calendar']/div/div[1]/div/div")
	private WebElement getLeftBlockOnCalendar;
	
	@FindBy(xpath = "//*[@id='calendar']/div/div[2]/div/div")
	private WebElement getRightBlockOnCalendar;
	
	@FindBy(xpath = "//*[@id='calendar']/div/div[1]/div/a/i")
	private WebElement choosePreviousMonthOnCalendar;
	
	@FindBy(xpath = "//*[@id='calendar']/div/div[2]/div/a/i")
	private WebElement chooseNextMonthOnCalendar;		
	
	@FindBy(xpath = "//*[@id='calendar']/div/div[2]/div/a/i")
	private WebElement chooseStartDate;	

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

//	public void login(String username, String password) {
//		driver.switchTo().frame(iframeOnLoginPage);
//		inputLogin.sendKeys(username);
//		inputPassword.sendKeys(password);
//		buttonSubmit.click();
//	}

//	public String getTextUserName() {
//		return LoggedInUserName.getText();
//
//	}

}
