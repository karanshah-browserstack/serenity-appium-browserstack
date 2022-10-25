package integration.browserstack;

import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;

public class GetInfoBrowserStack {
    public static String getName() {
        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = getEnvironmentVariables().getProperty("userName");
        }
        return username;
    }

    public static String getKey() {
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        
        if (accessKey == null) {
            accessKey = getEnvironmentVariables().getProperty("accessKey");
        }
        return accessKey;
    }

    public static EnvironmentVariables getEnvironmentVariables() {
        return SystemEnvironmentVariables.createEnvironmentVariables();
    }
}
