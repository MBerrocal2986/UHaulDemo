package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoragePage {
	WebDriver driver;
	
	//Location TextBox
	By yourLocation = By.cssSelector("#movingFromInput");
	
	//Unit Size Dropdown
	By selectUnitSize = By.cssSelector("#selectboxUnitSizeInput");
	
	//Type of Storage Dropdown
	By selectTypeOfStorage = By.cssSelector("#selectboxInput");
	
	//Find Storage Button
	By findStorage = By.cssSelector("button[class='expanded']");
	
	//Error Message Label
	By errorMessage = By.cssSelector("div[class='validation-summary-errors'] ul li");
	
	//Unit Size Small option
	By unitSizeSmall = By.cssSelector("label[for='Small']");
	
	//Type of Storage Wine Storage option
	By typeStorageWine = By.cssSelector("label[for='Wine'] span[class='input-icon']");
	
	//Navigation Bar element
	By navigationBar = By.cssSelector("nav[id='mainNavbar'] div[class='grid-container']");
	
	
	public StoragePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//This method enters the location, it can be a ZipCode - City - State - Landmark
	public void enterLocation(String location) {
		
		//Ensure the Element is empty before entering the location
		driver.findElement(yourLocation).clear();
		
		//Set the location send by parameters
		driver.findElement(yourLocation).sendKeys(location);
	}
	
	//This method clicks on Find Storage Button
	public void clickFindStorage() {
		driver.findElement(findStorage).click();
	}
	
	//This method clicks on Unit Size dropDown
	public void clickUnitSize() {
		driver.findElement(selectUnitSize).click();
	}
	
	//This method clicks on Unit Size Small
	public void clickUnitSizeSmall() {
		driver.findElement(unitSizeSmall).click();
	}
	
	//This method clicks on Type of Storage dropDown
	public void clickTypeOfStorage() {
		driver.findElement(selectTypeOfStorage).click();
	}
	
	//This method clicks on Type of Storage Wine Storage
	public void clickTypeOfStorageWine() {
		driver.findElement(typeStorageWine).click();
	}
	
	//This method retrieves the text from the error message label above the elements in the component
	public String getErrorMessage() {
		String error = driver.findElement(errorMessage).getText();
		return error;
	}
	
	//This method navigates through the Menu options and selects a SubMenu
	public void navigateMenuSubMenu(String menu, String subMenu) {
		WebElement menuItem = null;
		WebElement subMenuItem = null;
		
		//Retrieve all the HTML "a" elements
		List<WebElement> elements = driver.findElement(navigationBar).findElements(By.tagName("a"));
		
		//Go through the list of elements to look for the Menu Element
		for (WebElement element : elements) {
			//Compares the element text with the parameter given
			if (element.getAttribute("textContent").trim().equals(menu)) {
				menuItem = element; //Assign the element found for further actions
				break;
			}
		}
		
		//Go through the list of elements to look for the SubMenu Element
		for (WebElement element : elements) {
			//Verifies if the string given by parameters is inside the element text
			if (element.getAttribute("textContent").trim().indexOf(subMenu) != -1) {
				subMenuItem = element; //Assign the element found for further actions
				break;
			}
		}
		Actions action = new  Actions(driver);
		action.moveToElement(menuItem).perform(); //Hover over the Menu option to display the subMenu
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); //Wait for the subMenu to be displayed
		wait.until(ExpectedConditions.visibilityOf(subMenuItem)).click(); //Click on the subMenu requested
	}
}
