package tests;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import pages.StoragePage;

public class StorageTests {

	public WebDriver driver;
	
	public StoragePage storage;
	
	@Test(description="This test validates that an user can search for storages using a location, unit size and type of storage")
	public void searchStorageByCityWithUnitSizeAndTypeOfStorage() {
		
		//Navigate on the Menu Bar to Storage and Select "Move-in Online Today!"
		storage.navigateMenuSubMenu("Storage", "Move-In Online Today!");
		
		//Enter a City on Storage Location
		storage.enterLocation("Miami, FL");
		
		//Select a Small Unit
		storage.clickUnitSize();
		storage.clickUnitSizeSmall();
		//Hide Unit Size submenu
		storage.clickUnitSize(); //This just has to be a click outside the submenu could be a label, here is just reusing already created method
		
		//Select a type of Storage as Wine Storage
		storage.clickTypeOfStorage();
		storage.clickTypeOfStorageWine();
		
		//Click on Find a Storage
		storage.clickFindStorage();
		
		//Assert that results for Miami FL storages are displayed
		String expectedURL = "https://www.uhaul.com/Storage/Miami-FL/Results/";
		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL);
	}
	
	@Test (description="This test validates that an error message is displayed when an user enters an invalid ZipCode")
	public void searchStorageInvalidZipCode() {
		//Navigate on the Menu Bar to Storage and Select "Move-in Online Today!"
		storage.navigateMenuSubMenu("Storage", "Move-In Online Today!");

		//Enter an invalid ZipCode on Storage Location
		storage.enterLocation("123456789");

		//Click on Find a Storage
		storage.clickFindStorage();
		
		//Get Error Message Displayed
		String errorMessage = storage.getErrorMessage();
		
		//Validates the Error message is displayed and have the right text (expected error message can be managed on a expected assertions dictionary)
		Assert.assertEquals(errorMessage, "We could not find your location. Please enter your zip/postal code, city or address again.");

	}
	
	@BeforeTest
	public void Initialize() {
		driver = new ChromeDriver();  
	      
	    // Navigate to the initial webpage  
	    driver.navigate().to("https://www.uhaul.com/Storage/");
	    
	    //Create the object for the Storage Page
	    storage = new StoragePage(driver);
	}

	@AfterTest
	public void Teardown() {
		driver.quit();
	}

}
