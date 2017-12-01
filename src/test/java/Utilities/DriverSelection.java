package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverSelection {

    public WebDriver getDriver(String specifiedDriver) {
        WebDriver driver;
        switch (specifiedDriver) {
            case "Edge" : driver = new EdgeDriver();
                break;
            case "Chrome" : driver = new ChromeDriver();
                break;
            case "Firefox" : driver = new FirefoxDriver();
                break;
            default: driver = new ChromeDriver();
        }
        return driver;
    }
}
