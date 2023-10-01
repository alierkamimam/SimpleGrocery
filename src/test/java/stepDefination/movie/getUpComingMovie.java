package stepDefination.movie;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.junit.Assert;

public class getUpComingMovie extends BaseClass {
    Response response;


    @Given("Get a list of movies that are being released soon, then verify the response code is {int}")
    public void get_a_list_of_movies_that_are_being_released_soon_then_verify_the_response_code_is(int statusCode) {

        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath("/upcoming")
                .addHeader("Authorization", "Bearer " + TOKEN)
                .setAccept("application/json")
                .addParam("language", "en-US")
                .addParam("page", "1")
                .build();

        response = RestAssured.given()
                .spec(spec)
                .when()
                .get();

        Assert.assertEquals(response.statusCode(), statusCode);
        response.getBody().prettyPrint();
    }
}
