import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.auth.AuthenticationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;



public class ADD_TO_Cart {
    @Test
    public void TC1_testGetAllItemsHomePage() {

    Response response = given()
            .baseUri("https://api.demoblaze.com/")
            .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjUwMywiaWF0IjoxNzExNTI1NDQ5LCJleHAiOjE3MTMyNTM0NDl9.asdBhw003cWldYt2KZnm-iCnwGJ6kFqnCI_05TJC-s4")
            .contentType("application/json")
            .body("")
            .when()
            .get("entries")
            .then()
            .log().all()
            .extract().response();

    Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
}
    //TC2_Check  Token user
    @Test
    public void TC2_Check_Token_user() {
        String requestBody = "{\"token\": \"YWhtZWQxMjNAZ21haWwuY29tMTcxMTY3Mg==\"}";
        Response response = given()
                .baseUri("https://api.demoblaze.com/")
                 .contentType("application/json")
                .body(requestBody)
                .when()
                .post("check")
                .then()
                .log().all()
                .extract().response();
        // Assert that status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("ahmed123@gmail.com"), "Error' message");
    }

    //TC3_Get single Product
    @Test
    public void TC3_GetSingleProduct() {
        String requestBody = "{\"id\": \"1\"}";
         Response response = given()
                .baseUri("https://api.demoblaze.com/")
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjUwMywiaWF0IjoxNzExNTI1NDQ5LCJleHAiOjE3MTMyNTM0NDl9.asdBhw003cWldYt2KZnm-iCnwGJ6kFqnCI_05TJC-s4")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("view")
                .then()
                .log().all()
                .extract().response();
        // Assert that status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("The Samsung Galaxy S6"), "The Samsung Galaxy S6' message");
    }
   // TC4_Add one item to Cart is bug as no validation for added product
   @Test
   public void TC4_Add_one_item_to_Cart() {
       File body =new File("src/test/resources/TC4Body.json");
       Response response = given()
               .baseUri("https://api.demoblaze.com/")
               .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjUwMywiaWF0IjoxNzExNTI1NDQ5LCJleHAiOjE3MTMyNTM0NDl9.asdBhw003cWldYt2KZnm-iCnwGJ6kFqnCI_05TJC-s4")
               .contentType("application/json")
               .body(body)
               .when()
               .post("addtocart")
               .then()
               .log().all()
               .extract().response();
       // Assert that status code is 200 (OK)
       Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
       String responseBody = response.getBody().asString();
       Assert.assertTrue(true, "The Samsung Galaxy S6' message");
   }
   //TC5-ADD item not available
   @Test //This critical bug
   public void TC5_ADD_item_not_available () {
       File body =new File("src/test/resources/TC5ADDCart.json");

       Response response = given()
               .baseUri("https://api.demoblaze.com/")
               .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjUwMywiaWF0IjoxNzExNTI1NDQ5LCJleHAiOjE3MTMyNTM0NDl9.asdBhw003cWldYt2KZnm-iCnwGJ6kFqnCI_05TJC-s4")
               .contentType("application/json")
               .body(body)
               .when()
               .post("addtocart")
               .then()
               .log().all()
               .extract().response();
       // Assert that status code is 200 (OK)
       Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
       String responseBody = response.getBody().asString();
       Assert.assertTrue(responseBody.contains("This Items not available"), "Error Items");

   }
   //TC6_ADDTow  Items
   @Test
   public void TC6_ADDTow_Items () {
       File body =new File("src/test/resources/TC6_ADDTow_Items.json");

       Response response = given()
               .baseUri("https://api.demoblaze.com/")
               .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjUwMywiaWF0IjoxNzExNTI1NDQ5LCJleHAiOjE3MTMyNTM0NDl9.asdBhw003cWldYt2KZnm-iCnwGJ6kFqnCI_05TJC-s4")
               .contentType("application/json")
               .body(body)
               .when()
               .post("addtocart")
               .then()
               .log().all()
               .extract().response();
       // Assert that status code is 200 (OK)
       Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
       String responseBody = response.getBody().asString();
       Assert.assertTrue(true, "The Samsung Galaxy S6' message");
   }
   //TC7_Delete item
   @Test // high priority bug
   public void TC7_Delete_item() {
       String requestBody ="{ \"id\": \"9442c83b-9221-b512-52da-1839ad97cf16\",\n" +
               "    \"cookie\": \"YWhtZWQxMjNAZ21haWwuY29tMTcxMTY3Mg==\",\n" +
               "    \"prod_id\": 1,\n" +
               "    \"flag\": true\n" +
               "}";
       Response response = given()
               .baseUri("https://api.demoblaze.com/")
               .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjUwMywiaWF0IjoxNzExNTI1NDQ5LCJleHAiOjE3MTMyNTM0NDl9.asdBhw003cWldYt2KZnm-iCnwGJ6kFqnCI_05TJC-s4")
               .contentType("application/json")
               .body(requestBody)
               .when()
               .delete("addtocart")
               .then()
               .log().all()
               .extract().response();
       // Assert that status code is 200 (OK)
       Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
       String responseBody = response.getBody().asString();
       Assert.assertTrue(true, "The Samsung Galaxy S6 deleted' message");
   }

    @Test
    public void TC8_View_Cart() {
        // Request body
        String requestBody = " {\"cookie\":\"user=ccd59092-de67-4b0e-9ba4-441fce1fa922\",\"flag\":false}\n";

        // Send POST request and capture response
        Response response = given()
                .baseUri("https://api.demoblaze.com/")
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjUwMywiaWF0IjoxNzExNTI1NDQ5LCJleHAiOjE3MTMyNTM0NDl9.asdBhw003cWldYt2KZnm-iCnwGJ6kFqnCI_05TJC-s4")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("viewcart")
                .then()
                .log().all()
                .extract().response();

        // Assert that status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200, "Response status code is not 200");

        // Assert that response body is a JSON object
        assertTrue(response.getBody().asString().startsWith("{"), "Response body is not a JSON object");

        // Assert that 'Items' array exists in the response
        assertFalse(!response.getBody().jsonPath().getList("Items").isEmpty(), "Items array does not exist");

        // Assert that each item in 'Items' array has non-empty 'cookie' and 'id' strings
        response.getBody().jsonPath().getList("Items").forEach(item -> {
            assertTrue(item.toString().contains("cookie") && item.toString().contains("id"), "Item is missing 'cookie' or 'id' fields");
            assertTrue(item.toString().contains("prod_id"), "Item is missing 'prod_id' field");
        });

        // Assert that 'prod_id' is a non-negative integer for each item
        response.getBody().jsonPath().getList("Items").forEach(item -> {
            assertTrue(item.toString().contains("prod_id"), "Item is missing 'prod_id' field");
            int prodId = Integer.parseInt(item.toString().split(":")[1].split(",")[0].trim());
            assertTrue(prodId >= 0, "Prod_id is not a non-negative integer");
        });

        // Assert that Content-Type header is application/json
        assertTrue(response.getContentType().contains("application/json"), "Content-Type is not application/json");
    }
    @Test
    public void TC9_Place_Order() {
        // Request body
        String requestBody = "{\"cookie\": \"ahmed123@gmail.com\"\n}";

        // Send POST request and capture response
        Response response = given()
                .baseUri("https://api.demoblaze.com/")
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjUwMywiaWF0IjoxNzExNTI1NDQ5LCJleHAiOjE3MTMyNTM0NDl9.asdBhw003cWldYt2KZnm-iCnwGJ6kFqnCI_05TJC-s4")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("deletecart")
                .then()
                .log().all()
                .extract().response();

        // Assert that status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200, "Response status code is not 200");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Item deleted."), "Error Items");


    }



}
