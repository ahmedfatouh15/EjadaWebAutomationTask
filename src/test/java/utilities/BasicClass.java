package utilities;

import org.openqa.selenium.WebDriver;

public abstract class BasicClass {
    public static WebDriver driver;

    public BasicClass(WebDriver driver){
        BasicClass.driver = driver;
    }

}
