package integration.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WordPressLoginPage extends MobilePageObject {

    @AndroidFindBy(id = "org.wordpress.android:id/continue_with_wpcom_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Log In']")
    private WebElement WPLogInButton;
    @AndroidFindBy(id = "org.wordpress.android:id/input")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Email address\"]")
    private WebElement WPEmailAddressField;
    @AndroidFindBy(id = "org.wordpress.android:id/primary_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Next Button\"]")
    private WebElement WPLogInPageNextButton;
    @AndroidFindBy(id = "org.wordpress.android:id/textinput_error")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'not registered')]")
    private WebElement WPLogInPageInvalidEmailErrorMessage;
    @FindBy(id = "com.flipkart.android:id/btn_mlogin")
    private WebElement existingUsersignIn;
    @FindBy(id = "com.flipkart.android:id/mobileNo")
    private WebElement userId;
    @FindBy(id = "com.flipkart.android:id/et_password")
    private WebElement password;
    @FindBy(id = "com.flipkart.android:id/btn_mlogin")
    private WebElement login_Button;
    @FindBy(id = "com.flipkart.android:id/pageLevelError")
    private WebElement error_text;

    public WordPressLoginPage(WebDriver driver) {
        super(driver);
    }

    public void gotoWPLoginPage() {
        element(WPLogInButton).click();
    }

    public void enterInvalidEmailIdWPLoginPage() {
        WPEmailAddressField.sendKeys("vikram@invalid.com");
    }

    public boolean isErrorMessageShownWPLoginPage() {
        WPLogInPageNextButton.click();
        return WPLogInPageInvalidEmailErrorMessage.getText().contains("is not registered on WordPress.com");
    }


    public void gotoLoginPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/btn_mlogin")));
        element(existingUsersignIn).click();
    }

    public void enterInvalidCredentials() {
        element(userId).sendKeys("dummyName");
        element(password).sendKeys("invalidPwd");
        element(login_Button).click();
    }

    public boolean isErrorMessageShown() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/pageLevelError")));
        return element(error_text).getText().contentEquals("Invalid login details");
    }

}
