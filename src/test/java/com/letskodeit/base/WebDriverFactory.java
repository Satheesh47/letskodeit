package com.letskodeit.base;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.letskodeitcom.utilities.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
	// Singleton
	// Only one instance of the class can exist at a time
	private static final WebDriverFactory instance = new WebDriverFactory();
	private static final Logger log = LogManager.getLogger(WebDriverFactory.class.getName());
	private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<String> threadedBrowser = new ThreadLocal<String>();

	private WebDriverFactory() {
	}

	public static WebDriverFactory getInstance() {
		return instance;
	}

	public WebDriver getDriver(String browser) {
		WebDriver driver = null;
		setDriver(browser);
		threadedBrowser.set(browser);
		if (threadedDriver.get() == null) {
			try {
				if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions firefoxOptions = setFirefoxOptions();
					driver = new FirefoxDriver(firefoxOptions);
					threadedDriver.set(driver);
					log.info("Firefox browser initiated ...");
				}
				if (browser.equalsIgnoreCase(Constants.CHROME)) {
					ChromeOptions chromeOptions = setChromeOptions();
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(chromeOptions);
					threadedDriver.set(driver);
					log.info("Chrome browser initiated ...");
				}
				if (browser.equalsIgnoreCase(Constants.EDGE)) {
					
					WebDriverManager.edgedriver();
					driver = new EdgeDriver();
					threadedDriver.set(driver);
					log.info("Edge browser initiated ...");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			threadedDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			threadedDriver.get().manage().window().maximize();
		}

		return threadedDriver.get();
	}
	
	public String getBrowser() {
		return threadedBrowser.get();
	}
	
	public void quitDriver() {
		threadedDriver.get().quit();
		threadedDriver.set(null);
	}
	
    private void setDriver(String browser) {
        String driverPath = "";
        String os = Constants.OS_NAME.toLowerCase().substring(0, 3);
        log.info("OS Name from system property :: " + os);
        String directory = Constants.USER_DIRECTORY + Constants.DRIVERS_DIRECTORY;
        String driverKey = "";
        String driverValue = "";

        if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
            driverKey = "webdriver.gecko.driver";
            driverValue = "geckodriver";
        } else if (browser.equalsIgnoreCase(Constants.CHROME)) {
            driverKey = "webdriver.chrome.driver";
            driverValue = "chromedriver";
        } else if (browser.equalsIgnoreCase("ie")) {
            driverKey = "webdriver.ie.driver";
            driverValue = "IEDriverServer";
        } else {
            System.out.println("Browser type not supported");
        }

        driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "");
        log.info("Driver Binary :: " + driverPath);
        System.setProperty(driverKey, driverPath);
    }

    private ChromeOptions setChromeOptions() {
    	ChromeOptions options = new ChromeOptions();
		/*
		 * options.setExperimentalOption("excludeSwitches",
		 * Collections.singletonList("enable-automation"));
		 * options.setExperimentalOption("useAutomationExtension", false);
		 * options.addArguments("--disable-web-security");
		 * options.addArguments("--no-proxy-server"); Map<String, Object> prefs = new
		 * HashMap<String, Object>(); prefs.put("credentials_enable_service", false);
		 * prefs.put("profile.password_manager_enabled", false);
		 * options.setExperimentalOption("prefs", prefs);
		 */
    	return options;		
    }
    
    private FirefoxOptions setFirefoxOptions() {
    	FirefoxOptions options = new FirefoxOptions();
    	options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
    	return options;
    }
	
}
