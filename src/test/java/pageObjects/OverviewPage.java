package pageObjects;

import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.BasicClass;

public class OverviewPage extends BasicClass {

	// Constructor
	public OverviewPage(WebDriver driver) {
		super(driver);
//        Thread.sleep(1000);
		PageFactory.initElements(driver, this);
	}

	// Selectors
	private final String pageTitleXpath = "//span[@class='title']";
	private final String priceBeforeTaxXpath = "//div[@class='summary_subtotal_label']";
	private final String finishButtonXpath = "//button[@id='finish']";
//	private final String zipCodeInputXpath = "//button[@id='cancel']";

	// WebElements
	@FindBy(how = How.XPATH, using = pageTitleXpath)
	private WebElement pageTitle;

	@FindBy(how = How.XPATH, using = priceBeforeTaxXpath)
	private WebElement priceBeforeTax;

	@FindBy(how = How.XPATH, using = finishButtonXpath)
	private WebElement finishButton;

	// Functions
	public String getPageTitle() {
		return pageTitle.getText();
	}

	public boolean checkPriceBeforeTaxes(String[] prices) {
		System.out.println();
		double totalPriceBeforeTaxes = Double.parseDouble(priceBeforeTax.getText().split(Pattern.quote("$"))[1]);
		System.out.println(totalPriceBeforeTaxes);
		double estimatedPriceBeforeTaxes = 0;
		for(int i = 0; i < prices.length; i++) {
			System.out.println(Double.parseDouble(prices[i].split(Pattern.quote("$"))[1]));
			estimatedPriceBeforeTaxes += Double.parseDouble(prices[i].split(Pattern.quote("$"))[1]);
			System.out.println(estimatedPriceBeforeTaxes);
		}
		if(totalPriceBeforeTaxes == estimatedPriceBeforeTaxes) {
			return true;
		} else {
			return false;
		}
	}
	
	public void finishTheOrder() {
		finishButton.click();
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isPageLoaded() {
		boolean flag = pageTitle.isDisplayed();
		flag = flag && priceBeforeTax.isDisplayed();
		flag = flag && finishButton.isDisplayed();
		return flag;
	}

}
