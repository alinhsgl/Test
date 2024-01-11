package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UsersServiceAPITest {
    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][]{
                {"Alina Spektor", "1122333"},
                {"Lital Spektor", "332211"}
        };
    }
    @DataProvider(name = "updateUserData")
    public Object[][] updateUserData() {
        return new Object[][]{
                {"Sapir Spektor", "445566"},
                {"Gilad Spektor", "998877"}
        };
    }

    //Add user
    @Test(dataProvider = "userData")
    public void testAddUser(String name, String id) {
        // Set the base URI for the API
        RestAssured.baseURI = "http://localhost:5000";

        // Create the user object as a JSON string using the provided data
        String userJson = "{\"name\": \"" + name + "\", \"id\": \"" + id + "\"}";

        // Send the POST request and get the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(userJson)
                .post("/users");

        // Assert the status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        // Extract and validate the response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        System.out.println(userJson);
    }

    //Get user - Returns the user with id
    @Test(dataProvider = "userData")
    public void testGetUser_ReturnsUserWithId(String name, String id) {
        // Set the base URI for the API
        RestAssured.baseURI = "http://localhost:5000";

        // Send the GET request to retrieve the user with the specified ID
        Response response = given()
                .contentType(ContentType.JSON)
                .get("/users/" + id);

        // Assert the status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        // Extract and validate the response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
    }

    //Get user - Returns the users list
    @Test(dataProvider = "userData")
    public void testGetUser_ReturnsUsersList(String name, String id) {
        // Set the base URI for the API
        RestAssured.baseURI = "http://localhost:5000";

        // Send the GET request to retrieve the user with the specified ID
        Response response = given()
                .contentType(ContentType.JSON)
                .get("/users");

        // Assert the status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        // Extract and validate the response body
        // Print the response body (user list)
        String responseBody = response.getBody().asString();
        System.out.println("User List: " + responseBody);
    }

    //Edit user
    @Test(dataProvider = "updateUserData")
    public void testEditUser(String updateUser, String id) {
        // Set the base URI for the API
        RestAssured.baseURI = "http://localhost:5000";

       // String userId = "123457";

        // Create the updated user object as a JSON string
        String updatedUserJson = "{\"name\": \"" + updateUser + "\", \"id\": \"" + id + "\"}";

        // Send the PUT request to update the user with the specified ID
        Response response = given()
                .contentType(ContentType.JSON)
                .body(updatedUserJson)
                .put("/users/" + id);

        // Assert the status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        // Extract and validate the response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        System.out.println(updatedUserJson);
    }

    //Delete user
    @Test(dataProvider = "userData")
    public void testDeleteUser(String name, String id) {
        // Set the base URI for the API
        RestAssured.baseURI = "http://localhost:5000";

        // Send the DELETE request to delete the user with the specified ID
        Response response = given()
                .contentType(ContentType.JSON)
                .delete("/users/" + id);

        // Assert the status code
       Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        // Extract and validate the response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
    }
}