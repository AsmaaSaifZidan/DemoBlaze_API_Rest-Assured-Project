import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogIn_TCS {
        @Test
        public void TC1_LogIn_With_Valid_Data () {
            // Make a POST request
            Response response = given()
                    .contentType("application/json")
                    .body("{ \"username\": \"Asmaa123\", \"password\": \"123123\" }")
                    .post(" https://api.demoblaze.com/login\n\n");

            // Assert the status code is 200
            Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
            String responseBody = response.getBody().asString();
            Assert.assertTrue(responseBody.contains("Auth_token"), "Response does not contain 'Auth_token' message");


        }
    @Test
    public void TC2_Wrong_Password() {
         String requestBody ="{ \"username\": \"Asmaa123\", \"password\": \"1231230000\" }";
         Response response = given()
                .baseUri("https://api.demoblaze.com/")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .log().all()
                .extract().response();
        // Assert that status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        // Get response body as String
        String responseBody = response.getBody().asString();
         Assert.assertTrue(responseBody.contains("Wrong password."), "Response does not contain 'Wrong password.' message");
    }
    @Test // High Bug in App
    public void TC3_login_withoutData() {
        String requestBody ="{ \"username\": \"\", \"password\": \"\" }";
        Response response = given()
                .baseUri("https://api.demoblaze.com/")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .log().all()
                .extract().response();
        // Assert that status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        // Get response body as String
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Please fill out Username and Password."), "Response does not contain 'Please fill out Username and Password..' message");
    }
    @Test //
    public void TC4_User_not_Exit() {
        String requestBody ="{ \"username\": \"HamadaEzzo2024@gmail.com\", \"password\": \"12312345\" }";
        Response response = given()
                .baseUri("https://api.demoblaze.com/")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .log().all()
                .extract().response();
        // Assert that status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        // Get response body as String
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("User does not exist."), "Response does not contain 'User does not exist.' message");
    }

}
