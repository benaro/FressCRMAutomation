package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class CareersAtCognizantPage extends TestBase {
	
	public CareersAtCognizantPage() {
		PageFactory.initElements(driver, this);
	}
	
		private By searchedJobsMessage = By.xpath("//div[@class='container banner-content']//h4");
		private By jobsBrowsingHistoryContainer = By.cssSelector(".recomBrowsingHstry");
		private By jobCategoryText = By.name("phsKeywords");
		private By jobLocationText = By.id("locationInput");
		private By searchButton = By.cssSelector(".au-target.btn.primary-button.btn-lg.phs-search-submit");
		private By fisrtLinkJob = By.xpath("(//a[@ref='linkEle' and @ph-tevent='job_click'])[1]");
		private By listLinkJobs = By.xpath("//a[@ref='linkEle' and @ph-tevent='job_click']");
	
		public void searchByCategoryAndLocation(String category,String location) {
			type(category, jobCategoryText);
			type(location, jobLocationText);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			click(searchButton);
		}
		
		
		public void clickOnFirstSearchedJob() {
			click(fisrtLinkJob);
		}
		
		public int getNumberListJobs() {
			return finds(listLinkJobs).size();
		}
		
		public String getSearchedJobsMessage() {
			return find(searchedJobsMessage).getText();
		}
		
		
		//Aserts
		
		public Boolean JobsBrowsingHistoryContainerExists() {
			return find(jobsBrowsingHistoryContainer).isDisplayed();
		}
}
