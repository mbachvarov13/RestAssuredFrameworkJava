package Tests;

import Files.payloads;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourses() {
        int sumOfAll = 0;
        //mock response
        JsonPath jsonPath = new JsonPath(payloads.CoursePrice());

        int numberOfCourses = jsonPath.getInt("courses.size()");

        for (int i = 0; i < numberOfCourses; i++) {
            sumOfAll += jsonPath.getInt("courses[" + i + "].copies") * jsonPath.getInt("courses[" + i + "].price");
        }
        System.out.println("sum of all courses is: " + sumOfAll);

        Assert.assertEquals(sumOfAll, jsonPath.getInt("dashboard.purchaseAmount"));
    }
}
