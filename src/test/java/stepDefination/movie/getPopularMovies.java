package stepDefination.movie;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utilities.ConfigReader;

public class getPopularMovies extends BaseClass{

    Response response;

    /**
     * This method is used to get a list of movies ordered by popularity
     * Then verify the status code is 200
     *
     * @param statusCode    expected status code
     */
    @Given("get a list of movies ordered by popularity, then verify the status code is {int}")
    public void get_a_list_of_movies_ordered_by_popularity_then_verify_the_status_code_is(int statusCode) {

        /*
              --request GET \
              --url 'https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1' \
              --header 'Authorization: Bearer {{TOKEN}}' \
              --header 'accept: application/json'
              --queryParams 'language=en-US'
              --queryParams 'page=1'

         */

        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath("/popular")
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
