package Files;

import io.restassured.path.json.JsonPath;

public class CommonMethods {
    public static JsonPath rawToJson(String response) {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }
}
