package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDef extends Utils {
    private Response response;
    TestDataBuild testDataBuild = new TestDataBuild();
    public static String placeId;

    @Given("^I am creating add place payload (.*), (.*), (.*)$")
    public void createPlacePayload(String name, String address, String language) throws IOException {
        req = given().spec(requestSpecification()).body(testDataBuild.createPlacePayload(name, address, language));
    }

    @Given("^I am creating delete place payload$")
    public void createDeletePlacePayload() throws IOException {
        req = given().spec(requestSpecification()).body(testDataBuild.createDeletePlacePayload(placeId));
    }

    @When("^I call (.*) with (.*) http request$")
    public void createHttpRequest(String resource, String httpMethod) {
        APIResources apiResources = APIResources.valueOf(resource);

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = req.when().post(apiResources.getURLPath());

        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = req.when().get(apiResources.getURLPath());
        }
    }

    @Then("^I got status code (.*)$")
    public void checkStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("^returned (.*) in the response is (.*)$")
    public void checkParams(String parameter, String parameterValue) {
        Assert.assertEquals(parameterValue, getJsonPath(response, parameter));
    }

    @Then("^verify (.*) returned by (.*) is the same as in the POST request$")
    public void checkName(String name, String resource) throws IOException {
        placeId = getJsonPath(response, "place_id");
        req = given().spec(requestSpecification()).queryParam("place_id", placeId);
        createHttpRequest(resource, "GET");

        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(name, actualName);
    }
}
