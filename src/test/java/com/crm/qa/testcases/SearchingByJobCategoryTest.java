package com.crm.qa.testcases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.CareersAtCognizantPage;
import com.crm.qa.pages.HomePage;


public class SearchingByJobCategoryTest extends TestBase{
	
	CareersAtCognizantPage careersAtCognizantPage;
	HomePage homePage;
	
	public SearchingByJobCategoryTest() {
		super();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		long id = Thread.currentThread().getId();
        System.out.println("Before test-method. Thread id is: " + id);
		initializaton();
		homePage = new HomePage();
		careersAtCognizantPage = homePage.ClickCareersOption();
        
	}
	
	@BeforeClass()
	public void beforeClass(Method method) {
		Long id= Thread.currentThread().getId();
		System.out.println("Test name: " + method.getName() + " Before TC -> Thread is " + id);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		//FormatResult();
		long id = Thread.currentThread().getId();
        System.out.println("After Test Method. Thread id is: " + id);
		tearDownMain();
	}
	
	@AfterClass()
	public void afterClass() {
		long id = Thread.currentThread().getId();
        System.out.println("After test-class. Thread id is: " + id);
	}
	
	@Test(groups = { "Simple", "UI" })
	public void TC001_VerifyHeaderContent() {
		Assert.assertEquals(careersAtCognizantPage.getSearchedJobsMessage(), "Take a look at our job offerings");
	}
	
	@Test(groups = { "Simple", "UI" })
	public void TC002_JobsBrowsingHistoryContent() {
		Assert.assertTrue(careersAtCognizantPage.JobsBrowsingHistoryContainerExists(), getCurrentUrl());
	}
	
	@Parameters({"category","location","expectedMessage"})
	@Test(groups = { "Simple", "Functionality" })
	public void TC003(String category,String location, String expectedMessage) {
		//public void TC002() {
		log.info("tratando de dar click on click search");
		careersAtCognizantPage.searchByCategoryAndLocation(location, category);
		log.info("Searching was made");
		int result=careersAtCognizantPage.getNumberListJobs();
		log.info("number of jobs: "+result);	
		Assert.assertTrue(result > 0, "Not Found any result");
		careersAtCognizantPage.clickOnFirstSearchedJob();
	}
	
	
}
