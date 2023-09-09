package ConfigReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath= "config//config.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBaseUrl() {
        String url = properties.getProperty("baseUrl");
        if(url != null) return url;
        else throw new RuntimeException("Please Add the baseUrl to the config file.");
    }

    public String getDriverType() {
        String driverType = properties.getProperty("driverType");
        if(driverType != null) return driverType;
        else throw new RuntimeException("Please Add the driverType to the config file.");
    }
    public Long getImplicitWait() {
        String implicitWait = properties.getProperty("implicitWait");
        if(implicitWait != null) return Long.parseLong(implicitWait);
        else throw new RuntimeException("Please Add the implicitWait to the config file.");
    }

    public String isHeadlessTest() {
        String isHeadless = properties.getProperty("isHeadless");
        if(isHeadless != null) return isHeadless;
        else throw new RuntimeException("Please Add the isHeadless to the config file.");
    }
    
    public String getDriversDirectory() {
        String driversDirectory = properties.getProperty("driversDirectory");
        if(driversDirectory != null) return driversDirectory;
        else throw new RuntimeException("Please Add the driversDirectory to the config file.");
    }


}
