package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef {
    @Given("I have add place payload")
    public void i_have_add_place_payload() {

    }

    @When("I call addPlace")
    public void i_call_add_place() {

    }

    @Then("I got status code {int}")
    public void i_got_status_code(Integer statusCode) {

    }

    @Then("returned (.*) in response is (.*)")
    public void status_in_response_is_ok(String parameter, String parameterValue) {

    }
}
