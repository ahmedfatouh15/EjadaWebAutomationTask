package StepDefinitions;

import io.cucumber.java.en.*;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LoginPage;
import pageObjects.OrderConfirmPage;
import pageObjects.OverviewPage;
import pageObjects.ProductsPage;
import utilities.Shared;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ConfigReader.ConfigFileReader;

public class PurchaseSteps {
	

	private WebDriver driver;
	private String[] itemsNames;
	private String[] itemsPrices;
	private Shared shared = new Shared();
	
	public PurchaseSteps() throws Exception {
	}
	
	@Given("User is On the Login Screen")
	public void openLoginScreen() {
		ConfigFileReader reader = new ConfigFileReader();
		if(reader.getDriverType().equals("chrome")) {
    		System.setProperty("webdriver.chrome.driver", reader.getDriversDirectory() + "chromedriver.exe");
        	driver = new ChromeDriver();
    	} else {
    		System.setProperty("webdriver.gecko.driver", reader.getDriversDirectory() + "geckodriver.exe");
    		driver = new FirefoxDriver();
    	}
    	
    	driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(reader.getBaseUrl());
	}

	
	
	@When("The User log in using {word} and {word}")
	public void submitEmailPassword(String username, String password) {
		LoginPage loginPage = new LoginPage(this.driver);
		loginPage.isPageLoaded();
		username = username.replace("\"", "");
		password = password.replace("\"", "");
		loginPage.EnterUsername(username);
		loginPage.EnterPassword(password);
		loginPage.clickLoginButton();
	}
	
	@Then("The User should login successfully")
	public void successLogin() {
		ProductsPage productPage = new ProductsPage(driver);
		assertTrue(productPage.isPageLoaded());
		assertEquals(productPage.getPageTitle(), "Products");
	}

	@When("user sort the items by the most Expensive")
	public void user_sort_the_items_by_the_most_Expensive() {
		
	    ProductsPage productsPage = new ProductsPage(this.driver);
	    productsPage.sortByMostExpensive();	    
	}

	@When("add the first two items to cart")
	public void add_the_first_two_items_to_cart() {
		ProductsPage productsPage = new ProductsPage(this.driver);
		int[] orders = {0,1};
		itemsNames = productsPage.getItemsName(orders);
		itemsPrices = productsPage.getItemsPrices(orders);
		productsPage.addToCart(orders);
	}

	@When("navigate to the cart page")
	public void navigate_to_the_cart_page() {
	    ProductsPage productsPage = new ProductsPage(this.driver);
	    productsPage.openCartPage();
	}

	@Then("the items should appear in the cart page")
	public void the_items_should_appear_in_the_cart_page() {
		CartPage cartPage = new CartPage(driver);
		assertTrue(cartPage.isPageLoaded());
		assertTrue(shared.isArraysConcurrent(cartPage.getItemsNames(), itemsNames));
		assertTrue(shared.isArraysConcurrent(cartPage.getItemsPrices(), itemsPrices));
	}
	
	@When("the user checkouts")
	public void the_user_checkouts() {
		CartPage cartPage = new CartPage(driver);
		cartPage.checkOut();
	}

	@Then("checkout page should open")
	public void checkout_page_should_open() {
	    CheckoutPage checkoutPage = new CheckoutPage(driver);
	    assertTrue(checkoutPage.isPageLoaded());
	    assertEquals(checkoutPage.getPageTitle(), "Checkout: Your Information");
	    assertTrue(checkoutPage.getCurrentUrl().contains("checkout-step-one.html"));
	}

	@When("the user submits the form using {word},{word} and {word}")
	public void the_user_submits_the_form(String fName, String lName, String zipCode) {
	    CheckoutPage checkoutPage = new CheckoutPage(driver);
	    checkoutPage.fillCheckoutForm(fName, lName, zipCode);
	    checkoutPage.submitCheckoutForm();
	}

	@Then("overview page should open")
	public void overview_page_should_open() {
	    OverviewPage overviewPage = new OverviewPage(driver);
	    assertTrue(overviewPage.isPageLoaded());
	    assertEquals(overviewPage.getPageTitle(), "Checkout: Overview");
	    assertTrue(overviewPage.getCurrentUrl().contains("checkout-step-two.html"));
	}

	@Then("the money amount should be correct")
	public void the_money_amount_should_be_correct() {
		OverviewPage overviewPage = new OverviewPage(driver);
		assertTrue(overviewPage.checkPriceBeforeTaxes(itemsPrices));
	}

	@Then("the url is correct")
	public void the_url_is_correct() {
	    OverviewPage overviewPage = new OverviewPage(driver);
	    assertEquals(overviewPage.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");
	}

	@When("the user finishes the order")
	public void the_user_finishes_the_order() {
		OverviewPage overviewPage = new OverviewPage(driver);
	    overviewPage.finishTheOrder();
	}

	@Then("thank you and order confirm messages should appear")
	public void thank_you_and_order_confirm_message_should_appear() {
	    OrderConfirmPage orderConfirmPage = new OrderConfirmPage(driver);
	    assertTrue(orderConfirmPage.isPageLoaded());
	    assertTrue(orderConfirmPage.getThankYouMsg().toLowerCase().contains("thank you"));
	    assertTrue(orderConfirmPage.getOrderConfirmMsg().toLowerCase().contains("order has been dispatched"));
		driver.quit();
	}
	
}
