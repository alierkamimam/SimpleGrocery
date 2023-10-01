package stepDefination.movie;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Value;
import org.apache.http.HttpStatus;
import org.asynchttpclient.HttpResponseStatus;
import org.asynchttpclient.RequestBuilder;
import org.junit.Assert;
import utilities.ConfigReader;

public class getNowPlayingMovies extends BaseClass {

    Response response;

    @Given("get a list of movies that are currently in theatres, then verify the response code is {int}")
    public void get_a_list_of_movies_that_are_currently_in_theatres(int statusCode) {

        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL + "/now_playing")
                .build();

        response = RestAssured.given()
                .spec(spec)
                .header("Authorization", "Bearer " + TOKEN)
                .header("Accept", "application/json")
                .queryParam("language", "en-US")
                .queryParams("page", "1")
                .get();

        Assert.assertEquals(response.statusCode(), statusCode);

        System.out.println(response.asPrettyString());


    }

}
