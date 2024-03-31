import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Sign_UP_TCS {
    @Test
    public void TC1_Signup_New_Valid_Data() {
        // Make a POST request
        Response response = given()
                .contentType("application/json")
                .body("{ \"username\": \"Asmaa123\", \"password\": \"123123\" }")
                .post(" https://api.demoblaze.com/signup\n");

        // Assert the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Sign up successful."), "Response does not contain 'Sign up successful.' message");


    }

    @Test
    public void TC2_SignUp_Valid_Data_Already_exit() {
        // Make a POST request
        Response response = given()
                .contentType("application/json")
                .body("{ \"username\": \"Asmaa123\", \"password\": \"123123\" }")
                .post(" https://api.demoblaze.com/signup\n");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("This user already exist"), "Response does not contain 'Sign up successful.' message");
    }
    @Test
    public void TC3_Signup_WithoutPassword () {
        // Make a POST request

        Response response = given()
                .contentType("application/json")
                .body("{ \"username\": \"Reeeem\" }")
                .post(" https://api.demoblaze.com/signup\n");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("missing username or password"), "Response does not contain 'missing username or password.' message");
    }

    @Test
    public void TC4_Sign_UP_without_name() {
        // Request body without username
        String requestBody = "{\"password\": \"123456852\"}";

        // Send POST request and capture response
        Response response = given()
                .baseUri("https://api.demoblaze.com/")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("signup")
                .then()
                .log().all()
                .extract().response();

        // Assert that status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        // Get response body as String
        String responseBody = response.getBody().asString();

        // Assert that response contains the message indicating a missing username or password
        Assert.assertTrue(responseBody.contains("missing username or password"), "Response does not contain 'missing username or password' message");
    }
    @Test //  High Bug in APP
    public void TC5_Sign_UP_without_Data() {
        // Request body without username
        String requestBody = "{ \"username\": \"\", \"password\": \"\" }";

        // Send POST request and capture response
        Response response = given()
                .baseUri("https://api.demoblaze.com/")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("signup")
                .then()
                .log().all()
                .extract().response();

        // Assert that status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        // Get response body as String
        String responseBody = response.getBody().asString();

        // Assert that response contains the message indicating a missing username or password
        Assert.assertTrue(responseBody.contains("missing username or password"), "Response does not contain 'missing username or password' message");
    }

}

