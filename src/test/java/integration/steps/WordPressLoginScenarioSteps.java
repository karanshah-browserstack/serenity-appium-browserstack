package integration.steps;


import integration.serenitySteps.WordPressLoginSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class WordPressLoginScenarioSteps {

    WordPressLoginSteps loginSteps;

    @Given("User is on login page")
    public void gotoLoginPage() {
        loginSteps.loginPageInvalidDataInput();
    }

    @When("Enter invalid credentials")
    public void enterInvalidData() {
        loginSteps.enterLoginData();
    }

    @Then("User is shown error message")
    public void checkErrorMessage() {
        loginSteps.checkErrorMessage();
    }


}
