package ApiTest;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;



public class TestAPI1 {

    @Test
    public void getAllProductList() {
        String endpoint = "https://automationexercise.com/api/productsList";
        var response = given().when().get(endpoint).then().assertThat().statusCode(200);
        JsonPath jsonPathEvaluator = response.extract().jsonPath();
        String apiTestClass = jsonPathEvaluator.get("products.name[0]");
        assertEquals(apiTestClass,"Blue Top", "GET method - First product check - PASS");
        //response.log().body();
    }

    @Test
    public void createUserAccount() {
        String endpoint = "https://automationexercise.com/api/createAccount";
        String myEmailAddress = "dt" + System.nanoTime() + "@example.com";
        Faker faker = new Faker();

        String name = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String streetAddress = faker.address().streetAddress();

//        System.out.println(myEmailAddress);
        var response =
                given().contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("name", name)
                .formParam("email", myEmailAddress)
                .formParam("password", "test@123")
                .formParam("title", "Ms")
                .formParam("birth_date", "12")
                .formParam("birth_month", "12")
                .formParam("birth_year", "2000")
                .formParam("firstname", firstName)
                .formParam("lastname", lastName)
                .formParam("company", "Belong")
                .formParam("address1", streetAddress)
                .formParam("address2", " ")
                .formParam("country", "Australia")
                .formParam("zipcode", 3150)
                .formParam("state", "VIC")
                .formParam("city", "Melbourne")
                .formParam("mobile_number", "0422342534")
                .when().post(endpoint).then().assertThat()
                .statusCode(200);
                JsonPath jsonPathEvaluator = response.extract().jsonPath();
                int responseCode = jsonPathEvaluator.get("responseCode");
                String message = jsonPathEvaluator.get("message");
                assertEquals(responseCode,201,"PUT method - Response code check - PASS");
                assertEquals(message,"User created!", "PUT method - Message check - PASS");
        //response.log().body();
    }



}
