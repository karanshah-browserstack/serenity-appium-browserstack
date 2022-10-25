package integration.steps;

import com.browserstack.local.Local;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.HashMap;
import java.util.Map;

public class StartLocalStep {
    private static Local bsLocal;

    @BeforeAll
    public static void setUp() throws Exception {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = environmentVariables.getProperty("accessKey");
        }

        String environment = System.getProperty("environment");
        String key = "bstack_local";
        boolean is_local = environmentVariables.getProperty(key) != null
                && environmentVariables.getProperty(key).equals("true");

        if (environment != null && !is_local) {
            key = "environment." + environment + ".local";
            is_local = environmentVariables.getProperty(key) != null
                    && environmentVariables.getProperty(key).equals("true");
        }

        if (is_local) {
            bsLocal = new Local();
            Map<String, String> bsLocalArgs = new HashMap<String, String>();
            bsLocalArgs.put("key", accessKey);
            bsLocal.start(bsLocalArgs);
        }
    }

    @AfterAll
    public static void tearDown() throws Exception {
        if (bsLocal != null) {
            bsLocal.stop();
        }
    }
}
