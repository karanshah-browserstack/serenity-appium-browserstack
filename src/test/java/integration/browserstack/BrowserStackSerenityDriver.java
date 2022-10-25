package integration.browserstack;

import io.appium.java_client.android.AndroidDriver;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

public class BrowserStackSerenityDriver implements DriverSource {

    @Override
    public AndroidDriver newDriver() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();


        String username = GetInfoBrowserStack.getName();

        String accessKey = GetInfoBrowserStack.getKey();
        String environment = System.getProperty("environment");
        System.out.println("environment is " + environment);
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bStackOptionsMap = new HashMap<>();
        Iterator it = environmentVariables.getKeys().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();

            if (key.equals("userName") || key.equals("accessKey")
                    || key.equals("browserstack.server")) {
                continue;
            } else if (key.startsWith("bstack_")) {
                if (key.equalsIgnoreCase("bstack_app")) {
                    capabilities.setCapability(key.replace("bstack_", ""), environmentVariables.getProperty(key));
                } else {
                    bStackOptionsMap.put(key.replace("bstack_", ""), environmentVariables.getProperty(key));
                }
            } else if (environment != null && key.startsWith("environment." + environment)) {
                if (key.equalsIgnoreCase("environment." + environment + ".app")) {
                    capabilities.setCapability(key.replace("environment." + environment + ".", ""),
                            environmentVariables.getProperty(key));
                } else {
                    bStackOptionsMap.put(key.replace("environment." + environment + ".", ""), environmentVariables.getProperty(key));
                }
                capabilities.setCapability(key.replace("environment." + environment + ".", ""),
                        environmentVariables.getProperty(key));
            }
        }
        try {
            AndroidDriver driver = new AndroidDriver(new URL("https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), capabilities);
            return driver;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

}
