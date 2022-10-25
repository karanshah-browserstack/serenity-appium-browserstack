package integration.serenitySteps;

import integration.pages.WordPressLoginPage;
import net.thucydides.core.pages.PageObject;

import static org.assertj.core.api.Assertions.assertThat;

public class WordPressLoginSteps extends PageObject {
    WordPressLoginPage loginPage;

    public final void loginPageInvalidDataInput() {
        loginPage.gotoWPLoginPage();
    }

    public final void enterLoginData() {
        loginPage.enterInvalidEmailIdWPLoginPage();
    }

    public final void checkErrorMessage() {
        assertThat(loginPage.isErrorMessageShownWPLoginPage()).isTrue();
    }

}