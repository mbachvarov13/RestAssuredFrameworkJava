package Tests;

import Files.payloads;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(payloads.CoursePrice());
        //print number of courses
        int numberOfCourses = jsonPath.getInt("courses.size()");
        System.out.println("number of courses is :" + numberOfCourses);
        //print purchase amount
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println("purchase amount is: " + purchaseAmount);
        //print first course title
        String firstCourseTitle = jsonPath.getString("courses[0].title");
        System.out.println("first course title is: " + firstCourseTitle);
        //print all courses titles and prices
        for (int i = 0; i < numberOfCourses; i++) {
            System.out.println(jsonPath.getString("courses[" + i + "].title"));
            System.out.println(jsonPath.get("courses[" + i + "].price").toString());
            if (jsonPath.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")) {
                System.out.println("Copies of RPA course are: " + jsonPath.get("courses[" + i + "].copies").toString());
                break;
            }
        }
    }
}
