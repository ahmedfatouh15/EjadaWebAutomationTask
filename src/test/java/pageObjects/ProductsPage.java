package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.BasicClass;

public class ProductsPage extends BasicClass {
    
    //Constructor
    public ProductsPage(WebDriver driver)  {
        super(driver);
//        Thread.sleep(1000);
        PageFactory.initElements(driver, this);
    }

    //Selectors
    private final String pageTitleXpath = "//span[@class='title']";
    private final String inventoryItemXpath = "//div[@class='inventory_item']";
    private final String sortFeatureButtonXpath = "//select[@data-test='product_sort_container']";
    private final String PriceHigh2LowOptionText = "Price (high to low)";
    private final String addToCartButtonXpath = "//button[@class='btn btn_primary btn_small btn_inventory']";
    private final String cartButtonXpath = "//div[@id='shopping_cart_container']//a";
    private final String itemsNamesXpath = "//div[@class='inventory_item_name']";
	private final String itemsPriceXpath = "//div[@class='inventory_item_price']";


    

    //WebElements
    @FindBy(how = How.XPATH, using = pageTitleXpath)
    private WebElement pageTitle;

    @FindAll(@FindBy(how = How.XPATH, using = inventoryItemXpath))
    private List<WebElement> inventoryItems;

    @FindBy(how = How.XPATH, using = sortFeatureButtonXpath)
    private WebElement sortFeatureButton;
    
    @FindBy(how = How.XPATH, using = cartButtonXpath)
    private WebElement cartButton;

    //Functions
    public String getPageTitle() {
    	return pageTitle.getText();
    }

    public List<WebElement> getInventoryItems() {
        return inventoryItems;
    }

    public void sortByMostExpensive() {
    	Select dropMenu = new Select(sortFeatureButton);
    	dropMenu.selectByVisibleText(PriceHigh2LowOptionText);    	
    }
    
    public void addToCart(int[] itemsOrder) {
    	for (int i = 0; i<itemsOrder.length; i++) {
			WebElement addToCartButton = driver.findElements(By.xpath(addToCartButtonXpath)).get(itemsOrder[i]-i);
			addToCartButton.click();
		}
    }
    
    public String[] getItemsName(int[] itemsOrder) {
    	String[] itemNames = new String[itemsOrder.length];
    	for (int i = 0; i<itemsOrder.length; i++) {
			WebElement itemName = driver.findElements(By.xpath(itemsNamesXpath)).get(itemsOrder[i]);
			itemNames[i] = itemName.getText();
		}
    	return itemNames;
    }
    
    public String[] getItemsPrices(int[] itemsOrder) {
		String[] itemsPrices = new String[itemsOrder.length];
		for (int i = 0; i<itemsOrder.length; i++) {
			WebElement itemPrice = driver.findElements(By.xpath(itemsPriceXpath)).get(itemsOrder[i]);
			itemsPrices[i] = itemPrice.getText();
		}
		return itemsPrices;
	}

    
    

    public void openCartPage(){
        cartButton.click();
    }
    
    public boolean isPageLoaded(){
        boolean flag = pageTitle.isDisplayed();
        flag = flag && inventoryItems.get(0).isDisplayed();
        flag = flag && cartButton.isDisplayed();
        return flag;
    }


}
