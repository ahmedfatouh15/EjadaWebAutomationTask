package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.BasicClass;

public class OrderConfirmPage extends BasicClass {

	// Constructor
	public OrderConfirmPage(WebDriver driver) {
		super(driver);
//        Thread.sleep(1000);
		PageFactory.initElements(driver, this);
	}

	// Selectors
	private final String pageTitleXpath = "//span[@class='title']";
	private final String thankYouMsgXpath = "//h2[@class='complete-header']";
	private final String orderConfirmMsgXpath = "//div[@class='complete-text']";
//	private final String zipCodeInputXpath = "//button[@id='back-to-products']";

	// WebElements
	@FindBy(how = How.XPATH, using = pageTitleXpath)
	private WebElement pageTitle;

	@FindBy(how = How.XPATH, using = thankYouMsgXpath)
	private WebElement thankYouMsg;

	@FindBy(how = How.XPATH, using = orderConfirmMsgXpath)
	private WebElement orderConfirmMsg;

	// Functions
	public String getPageTitle() {
		return pageTitle.getText();
	}
	
	public String getThankYouMsg() {
		return thankYouMsg.getText();
	}
	
	public String getOrderConfirmMsg() {
		return orderConfirmMsg.getText();
	}

	

	public boolean isPageLoaded() {
		boolean flag = pageTitle.isDisplayed();
		flag = flag && thankYouMsg.isDisplayed();
		flag = flag && orderConfirmMsg.isDisplayed();
		return flag;
	}

}
