package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	private By logo = By.cssSelector(".cog-logo");
	private By careers = By.xpath("//a[contains(@title,'Careers')]");
	private By contactUsCointainer = By.cssSelector("#contact-us");
	private By featureWorkContainer = By.xpath("//div[contains(@data-attribute,'featuredWork')]");
	private By industryExperienceContainer = By.xpath("//div[contains(@data-attribute,'industryExperience')]");

// public methods

	public CareersAtCognizantPage ClickCareersOption() {
		click(careers);
		return new CareersAtCognizantPage();
	}

	public Boolean LogoDisplayed() {
		waitForVisibilityOf(featureWorkContainer, 5);
		return find(logo).isDisplayed();
	}
	
	public Boolean ContactUsCointainerDisplayed() {
		waitForVisibilityOf(contactUsCointainer, 5);
		return find(logo).isDisplayed();
	}
	
	public Boolean IndustryExperienceContainerDisplayed() {
		waitForVisibilityOf(industryExperienceContainer, 5);
		return find(logo).isDisplayed();
	}
}
