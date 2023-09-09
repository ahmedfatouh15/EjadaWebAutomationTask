package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.BasicClass;

public class CartPage extends BasicClass {

	// Constructor
	public CartPage(WebDriver driver) {
		super(driver);
//        Thread.sleep(1000);
		PageFactory.initElements(driver, this);
	}

	// Selectors
	private final String pageTitleXpath = "//span[@class='title']";
	private final String checkOutButtonXpath = "//button[@id='checkout']";
	private final String continueShoppingButtonXpath = "//button[@id='continue-shopping']";
	private final String cartItemsXpath = "//div[@class='cart_item']";
	private final String itemsNamesXpath = "//div[@class='inventory_item_name']";
	private final String itemsPriceXpath = "//div[@class='inventory_item_price']";
//	private final String itemsRemoveButtonXpath = "//button[@class='btn btn_secondary btn_small cart_button']";

	// WebElements
	@FindBy(how = How.XPATH, using = pageTitleXpath)
	private WebElement pageTitle;

	@FindBy(how = How.XPATH, using = checkOutButtonXpath)
	private WebElement checkOutButton;

	@FindBy(how = How.XPATH, using = continueShoppingButtonXpath)
	private WebElement continueShoppingButton;

	@FindAll(@FindBy(how = How.XPATH, using = cartItemsXpath))
	private List<WebElement> cartItems;

	// Functions
	public String getPageTitle() {
		return pageTitle.getText();
	}

	public List<WebElement> getCartItems() {
		return cartItems;
	}

	public boolean checkAllItemsExists(String[] items) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < cartItems.size(); j++) {
				WebElement itemName = cartItems.get(j).findElement(By.xpath(itemsNamesXpath));
				if (items[i].equals(itemName.getText()))
					break;
				else if (j == cartItems.size() - 1)
					return false;
			}
		}
		return true;
	}

	public String[] getItemsPrices() {
		String[] itemsPrices = new String[cartItems.size()];
		for (int i = 0; i < cartItems.size(); i++) {
			WebElement itemPrice = cartItems.get(i).findElement(By.xpath(itemsPriceXpath));
			itemsPrices[i] = itemPrice.getText();
		}
		return itemsPrices;
	}

	public String[] getItemsNames() {
		String[] itemsNames = new String[cartItems.size()];
		for (int i = 0; i < cartItems.size(); i++) {
			WebElement itemName = cartItems.get(i).findElement(By.xpath(itemsNamesXpath));
			itemsNames[i] = itemName.getText();
		}
		return itemsNames;
	}

	public void checkOut() {
		checkOutButton.click();
	}

	public boolean isPageLoaded() {
		boolean flag = pageTitle.isDisplayed();
		flag = flag && checkOutButton.isDisplayed();
		flag = flag && continueShoppingButton.isDisplayed();
		flag = flag && cartItems.get(0).isDisplayed();
		return flag;
	}

}
