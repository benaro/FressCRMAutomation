package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;


public class HomePageTest extends TestBase {
	
	HomePage homePage;
	
	public HomePageTest() {
		super();
	}
	
	/*
	 * @BeforeMethod(alwaysRun = true) public void setUp(Method method) {
	 * initializaton(); homePage = new HomePage(); Long id=
	 * Thread.currentThread().getId(); System.out.println("Test name: " +
	 * method.getName() + " Thread is " + id); }
	 * 
	 * @AfterMethod(alwaysRun = true) public void tearDown() { //FormatResult();
	 * long id = Thread.currentThread().getId();
	 * System.out.println("After test-class. Thread id is: " + id); tearDownMain();asdsadaasdasd
	 * }
	 */
	
	//Explicacion de dispositiva @Test
	@Test()
	public void TC01_VerifyLogoisDisplayed() {
		initializaton();
		homePage = new HomePage();
		Assert.assertTrue(homePage.LogoDisplayed(), "The element is not present");
		tearDownMain();
	}

	@Test()
	public void TC02_VerifyContactUsCointainerisDisplayed() {
		Assert.assertTrue(homePage.ContactUsCointainerDisplayed(), "The element is not present");
	}

	@Test()
	public void TC03_VerifyIndustryExperienceContainerDisplayed() {
		Assert.assertTrue(homePage.IndustryExperienceContainerDisplayed(), "The element is not present");
	}
}
