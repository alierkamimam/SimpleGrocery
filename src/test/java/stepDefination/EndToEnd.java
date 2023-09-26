package stepDefination;

import com.google.gson.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;

public class EndToEnd {
    private static RequestSpecification spec;
    String token;
    Response response;
    String cartId;
    String itemId;
    List<Integer> ids;
    String orderId;

    @Given("Register new a client with {string} and {string}, then verify status code {int}")
    public void register_new_a_client_with_name_and_email_then_verify_status_code(String clientName, String clientEmail, int statusCode) {

        spec = new RequestSpecBuilder().setBaseUri("https://simple-grocery-store-api.glitch.me").build();

        String requestBody = "{\n" +
                "    \"clientName\":\"" + clientName + "\",\n" +
                "    \"clientEmail\":\"" + clientEmail + "\"\n" +
                "}";

        response = RestAssured.given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api-clients");

        JsonPath body = response.jsonPath();
        token = body.getString("accessToken");

        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode, statusCode);


    }

    @When("Get all products, then verify status code {int}")
    public void getAllProductsThenVerifyStatusCode(int statusCode) {
        response = RestAssured.given()
                .spec(spec)
                .when()
                .get("/products");

        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode, statusCode);
        System.out.println(response.asPrettyString());


    }

    @And("Create a new cart, then verify status code {int}")
    public void createANewCartThenVerifyStatusCode(int statusCode) {
        response = RestAssured.given()
                .spec(spec)
                .when()
                .post("/carts");

        int responseStatusCode = response.getStatusCode();

        JsonPath body = response.jsonPath();
        cartId = body.getString("cartId");

        Assert.assertEquals(responseStatusCode, statusCode);


    }

    @And("Add three items {int}, {int}, {int} to Cart, then verify status code {int}")
    public void addThreeItemsToCartThenVerifyStatusCode(int id01, int id02, int id03, int statusCode) {
        ids = List.of(id01, id02, id03);

        for (Integer id : ids) {

            String json = "{\n" +
                    "    \"productId\":" + id + "\n" +
                    "}";

            response = RestAssured.given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .body(json)
                    .when()
                    .post("/carts/" + cartId + "/items");
        }

        JsonPath body = response.jsonPath();
        System.out.println(response.asPrettyString());
        itemId = body.getString("itemId");

        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode, statusCode);

    }

    @Then("Check if the products you added to the cart are correct, then verify status code {int}")
    public void checkIfTheProductsYouAddedToTheCartAreCorrectThenVerifyStatusCode(int statusCode) {
        response = RestAssured.given()
                .spec(spec)
                .when()
                .get("/carts/" + cartId + "/items");

        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode, statusCode);

        JsonPath body = response.jsonPath();
        List<Integer> productId = body.getList("productId");

        for (Integer id : ids) {
            Assert.assertTrue(productId.contains(id));
        }

        System.out.println(response.asPrettyString());

    }

    @And("Let's cancel purchasing a product and remove it from the cart, then verify status code {int}")
    public void letSCancelPurchasingAProductAndRemoveItFromTheCart(int StatusCode) {
        response = RestAssured.given()
                .spec(spec)
                .when()
                .delete("/carts/" + cartId + "/items/" + itemId);

    }

    @Then("Check if the product you removed from the cart are correct")
    public void checkIfTheProductYouRemovedFromTheCartAreCorrect() {
        response = RestAssured.given()
                .spec(spec)
                .when()
                .get("/carts/" + cartId);

        List<Integer> itemsId = response.jsonPath().getList("items.id");
        Assert.assertFalse(itemsId.contains(itemId));


        System.out.println(response.asPrettyString());
    }

    @And("Create an new order with name {string}, then verify status code {int}")
    public void createAnNewOrder(String customerName, int statusCode) {

        String body = "{\n" +
                "    \"cartId\": \"" + cartId + "\",\n" +
                "    \"customerName\": \"" + customerName + "\"\n" +
                "}";

        response = RestAssured.given()
                .spec(spec)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/orders");

        int responseStatusCode = response.getStatusCode();
        orderId = response.jsonPath().getString("orderId");
        Assert.assertEquals(responseStatusCode, statusCode);
    }

    @Then("Check the order {string} have created, then verify status code {int}")
    public void checkTheOrderYouHaveCreated(String customerName, int statusCode) {
        response = RestAssured.given()
                .spec(spec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/orders/" + orderId);

        JsonPath body = response.jsonPath();
        String name = body.getString("customerName");

        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode, statusCode);
        Assert.assertEquals(name, customerName);


        System.out.println(response.asPrettyString());
    }

    @And("Delete an order, then verify status code {int}")
    public void deleteAnOrder(int statusCode) {
        response = RestAssured.given()
                .spec(spec)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/orders/" + orderId);

        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode, statusCode);
    }
}
