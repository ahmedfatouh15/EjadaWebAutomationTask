package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.BasicClass;

public class CheckoutPage extends BasicClass {

	// Constructor
	public CheckoutPage(WebDriver driver) {
		super(driver);
//        Thread.sleep(1000);
		PageFactory.initElements(driver, this);
	}

	// Selectors
	private final String pageTitleXpath = "//span[@class='title']";
	private final String firstNameInputXpath = "//input[@id='first-name']";
	private final String lastNameInputXpath = "//input[@id='last-name']";
	private final String zipCodeInputXpath = "//input[@id='postal-code']";
	private final String continueButtonXpath = "//input[@id='continue']";
//	private final String cancelButton = "//button[@id='cancel']";

	// WebElements
	@FindBy(how = How.XPATH, using = pageTitleXpath)
	private WebElement pageTitle;

	@FindBy(how = How.XPATH, using = firstNameInputXpath)
	private WebElement firstNameInput;

	@FindBy(how = How.XPATH, using = lastNameInputXpath)
	private WebElement lastNameInput;

	@FindBy(how = How.XPATH, using = zipCodeInputXpath)
	private WebElement zipCodeInput;
	
	@FindBy(how = How.XPATH, using = continueButtonXpath)
	private WebElement continueButton;

	// Functions
	public String getPageTitle() {
		return pageTitle.getText();
	}

	public void fillCheckoutForm(String fName, String lName, String zipCode) {
		firstNameInput.sendKeys(fName);
		lastNameInput.sendKeys(lName);
		zipCodeInput.sendKeys(zipCode);
	}
	
	public void submitCheckoutForm() {
		continueButton.click();
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isPageLoaded() {
		boolean flag = pageTitle.isDisplayed();
		flag = flag && firstNameInput.isDisplayed();
		flag = flag && lastNameInput.isDisplayed();
		flag = flag && zipCodeInput.isDisplayed();
		flag = flag && continueButton.isDisplayed();
		return flag;
	}

}
