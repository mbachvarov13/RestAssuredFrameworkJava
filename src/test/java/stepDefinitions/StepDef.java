package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojos.AddPlace;
import pojos.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDef {
    private RequestSpecification req;
    private ResponseSpecification resBuild;
    private Response response;

    @Given("I have add place payload")
    public void createAddPlacePayload() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLang(33.427362);
        p.setLocation(l);

        RequestSpecification reqBuild = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
        req = given().log().all().spec(reqBuild).body(p);
    }

    @When("I POST a new place")
    public void addNewPlace() {
        resBuild = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = req.when().post("/maps/api/place/add/json").then().spec(resBuild).extract().response();
    }

    @Then("^I got status code (.*)$")
    public void checkStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("^returned (.*) in the response is (.*)$")
    public void checkParams(String parameter, String parameterValue) {
        JsonPath jp = new JsonPath(response.asString());
        Assert.assertEquals(parameterValue, jp.get(parameter).toString());
    }
}
