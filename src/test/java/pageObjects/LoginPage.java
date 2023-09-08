package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.BasicClass;

public class LoginPage extends BasicClass {
    
    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
//        Thread.sleep(2000);
        PageFactory.initElements(driver, this);
    }

    //Selectors
    private final String usernameInputXpath = "//input[@id='user-name']";
    private final String PasswordInputXpath = "//input[@id='password']";
    public final String ErrorOutputCSS = "h3[data-test='error']";
    private final String LoginButtonXpath = "//input[@id='login-button']";

    //WebElements
    @FindBy(how = How.XPATH, using = usernameInputXpath)
    private WebElement usernameField;

    @FindBy(how = How.XPATH, using = PasswordInputXpath)
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = LoginButtonXpath)
    private WebElement loginButton;

    //Functions
    public void EnterUsername(String username) {
        usernameField.isEnabled();
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void EnterPassword(String password) {
        passwordField.isEnabled();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.isEnabled();
        loginButton.click();
    }
    public String getErrorMessage() {
    	@SuppressWarnings("static-access")
		WebElement errorMessage = super.driver.findElement(By.cssSelector(this.ErrorOutputCSS));
    	return errorMessage.getText();
    }

    public boolean isPageLoaded(){
        boolean flag = usernameField.isDisplayed();
        flag = flag && passwordField.isDisplayed();
        flag = flag && loginButton.isDisplayed();
        return flag;
    }


}
