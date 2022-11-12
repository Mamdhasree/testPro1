package TestCasesPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import seleniumConcepts.Contacts_Page;
import seleniumConcepts.Login_Page;

public class contactsTag_Testcase {
	WebDriver driver;

	Login_Page objLoginPage;
	Contacts_Page objContactsPage;

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.chrome.driver",
				"/home/mamdhasree/Downloads/chromedriver_linux64 (5)/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("http://vtiger.ws/website/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Website";
		Assert.assertNotEquals("Title matched",ExpectedTitle, ActualTitle);
//		if (ActualTitle ==ExpectedTitle) {
//			System.out.println("Title matched");
//		} else {
//			System.out.println("Title not matched");
//		}
	}

	@Test()
	public void verifyTagIsAddedOnRecords() throws InterruptedException {
		// Create Login Page object
		objLoginPage = new Login_Page(driver);

		// login to application
		objLoginPage.LoginToVtiger("mamdhasree.m@vtiger.com", "Mammu@11", "sreemamdha@gmail.com", "Mammu@11");

		// go the next page->contacts page object
		objContactsPage = new Contacts_Page(driver);
		objContactsPage.createTagsInContactModule("t1");

		// verify tag is added or not
		objContactsPage.selectAnyRecordInListView();
		objContactsPage.clickonAddedTagIcon();		
		String expectedMessage = "t1";
		String ActualTag = objContactsPage.verifyTagText().getText();
		Assert.assertNotEquals("Tag is created",ActualTag, expectedMessage);
		objContactsPage.clickBackBtn();
		
}
//	@AfterTest
//	public void Logout() throws InterruptedException {
//		driver.quit();
//	}
}
