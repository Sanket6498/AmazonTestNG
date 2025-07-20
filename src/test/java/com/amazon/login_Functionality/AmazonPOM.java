package com.amazon.login_Functionality;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonPOM {

	@FindBy(name = "email")
	WebElement emailId;

	@FindBy(id = "continue")
	WebElement continueBtn;

	public AmazonPOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void setEmail(String email) {
		emailId.sendKeys(email);
	}

	public void continueButton() {
		continueBtn.click();
	}
}
