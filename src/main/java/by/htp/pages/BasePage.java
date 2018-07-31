package by.htp.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
	protected WebDriver driver;

	public abstract void openPage();

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
}
