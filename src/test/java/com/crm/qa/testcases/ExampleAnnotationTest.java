package com.crm.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

// Explicar diapositiva de 'Before & After Annotations'
public class ExampleAnnotationTest{
	
	
	@BeforeMethod()
	public void beforeMethod() {
		System.out.println("Before TestMethod");
	}
	
	@AfterMethod()
	public void afterMethod() {
	System.out.println("After Method");	
	}
	
	@BeforeClass()
	public void beforeClass() {
		System.out.println("Before Class");
	}
	
	@AfterClass()
	public void afterClass() {
	System.out.println("After Class");	
	}
	
	@BeforeTest()
	public void beforeTest() {
		System.out.println("Before Test");
	}
	
	@AfterTest()
	public void afterTest() {
	System.out.println("After Test");	
	}
	
	@BeforeTest()
	public void beforeSuite() {
		System.out.println("Before Suite");
	}
	
	@AfterTest()
	public void afterSuite() {
	System.out.println("After Suite");	
	}
	
	@Test()
	public void tc01() {
		System.out.println("Metodo 1");
	}

	@Test()
	public void tc02() {
		System.out.println("Metodo 2");		
	}

	@Test()
	public void tc03() {
		System.out.println("Metodo 3");
	}
}
