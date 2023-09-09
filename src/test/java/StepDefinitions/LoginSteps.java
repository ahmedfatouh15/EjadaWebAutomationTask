package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ConfigReader.ConfigFileReader;

public class LoginSteps {
	
	
	private WebDriver driver;
	private final String invalidUserErrorMessage = "Username and password do not match";
	private final String userNameMandatoryMessage = "Username is required";
	private final String passwordMandatoryMessage = "Password is required";

	public LoginSteps() throws Exception {
	}

	@Given("The User is On the Login Screen")
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
		LoginPage loginPage = new LoginPage(this.driver);
//		loginPage.isPageLoaded();
	}

	@When("User log in using {word} and {word}")
	public void submitEmailPassword(String username, String password) {
		LoginPage loginPage = new LoginPage(this.driver);
		loginPage.isPageLoaded();
		username = username.replace("\"", "");
		password = password.replace("\"", "");
		loginPage.EnterUsername(username);
		loginPage.EnterPassword(password);
		loginPage.clickLoginButton();
	}

	@Then("Login should fail with displaying error message of invalid combination")
	public void invalidCredentials() {
		LoginPage loginPage = new LoginPage(driver);
		assertTrue(loginPage.getErrorMessage().contains(invalidUserErrorMessage));
		driver.quit();
	}

	@Then("Login should fail with displaying error message of username required")
	public void usernameRequired() {
		LoginPage loginPage = new LoginPage(driver);
		assertTrue(loginPage.getErrorMessage().contains(userNameMandatoryMessage));
		driver.quit();
	}

	@Then("Login should fail with displaying error message of password required")
	public void passwordRequired() {
		LoginPage loginPage = new LoginPage(driver);
		assertTrue(loginPage.getErrorMessage().contains(passwordMandatoryMessage));
		driver.quit();
	}
	
	@Then("User should login successfully")
	public void successLogin() {
		ProductsPage productPage = new ProductsPage(driver);
		assertTrue(productPage.isPageLoaded());
		assertEquals(productPage.getPageTitle(), "Products");
		driver.quit();
	}
	
	
//	@Then("User should be navigated to the products page")
//	public void user_should_be_navigated_to_the_products_page() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
}
