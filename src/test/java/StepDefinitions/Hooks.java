//package StepDefinitions;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import ConfigReader.ConfigFileReader;
////import org.junit.*;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//
//
//public class Hooks {
//
//    public static WebDriver driver;
//
//    @Before
//    public void openBrowser() throws Exception {
//    	ConfigFileReader reader = new ConfigFileReader();
//    
//    	if(reader.getDriverType().equals("chrome")) {
//    		System.setProperty("webdriver.chrome.driver", reader.getDriversDirectory() + "chromedriver.exe");
//        	driver = new ChromeDriver();
//    	} else {
//    		System.setProperty("webdriver.gecko.driver", reader.getDriversDirectory() + "geckodriver.exe");
//    		driver = new FirefoxDriver();
//
//    	}
//    	
//    	driver.manage().deleteAllCookies();
//        driver.manage().window().maximize();
//        driver.get(reader.getBaseUrl());
//    	
//    }
//    
//    @After
//	public void AfterSteps() {
//		driver.quit();
//	}
//}
