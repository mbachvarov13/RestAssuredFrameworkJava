package Tests;

import Files.CommonMethods;
import Files.payloads;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    private static String NEW_ADDRESS = "Martin new address";
    HashMap<String, String> placeID = new HashMap<>();

    @Test
    public void createNewPlace() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Create new Place
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payloads.addPlace())
                .when().post("/maps/api/place/add/json")
                .then().statusCode(200).extract().response().asString();

        System.out.println("response is" + response);

        placeID.put("place_id", CommonMethods.rawToJson(response).getString("place_id"));
        System.out.println("placeId is " + placeID.get("place_id"));
    }

    @Test(dependsOnMethods = {"createNewPlace"})
    public void updateCreatedPlace() {
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\r\n" +
                        "\"place_id\":\"" + placeID.get("place_id") + "\",\r\n" +
                        "\"address\":\"" + NEW_ADDRESS + "\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
                when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
    }

    @Test(dependsOnMethods = {"updateCreatedPlace"})
    public void getCreatedPlace() {
        String getAllPlacesResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID.get("place_id"))
                .when().get("/maps/api/place/get/json")
                .then().statusCode(200).extract().response().asString();

        String responseAddress = CommonMethods.rawToJson(getAllPlacesResponse).getString("address");
        System.out.println("new address is: " + responseAddress);
        Assert.assertEquals(responseAddress, NEW_ADDRESS);
    }
}
