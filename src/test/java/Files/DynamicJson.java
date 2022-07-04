package Files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

        String response = given().log().all().header("Content-Type", "application/json")
                .body(payloads.addBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println("add book response is: " + response);

        String id = CommonMethods.rawToJson(response).get("ID");
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        return new Object[][]{{"dada", "123456"}, {"dasddasde", "22222345"}, {"frgrga", "3333131"}};
    }

}
