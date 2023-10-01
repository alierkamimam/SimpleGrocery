package stepDefination;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class GetWeeklyWheather {

    private static RequestSpecification spec;
    Response response;
    final String TOKEN = "apikey 1Oo7Vsutf6itQmtI4VUzKB:0HL1tWR3JbFFzCEjLSrka5";

    @Given("It brings the weekly weather forecast of the {string} according to the given {string}, then verify the {int}")
    public void itBringsTheWeeklyWeatherForecastOfTheCityAccordingToTheGivenLanguage(String city, String language, int statusCode) {

        spec = new RequestSpecBuilder()
                .setBaseUri("https://api.collectapi.com/weather/getWeather")
                .build();

        response = RestAssured.given()
                .spec(spec)
                .header("Authorization", TOKEN)
                .contentType("application/json")
                .queryParams("data.lang", language)
                .queryParams("data.city", city)
                .when()
                .get();
        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode, statusCode);

        System.out.println(city.replace(city.charAt(0), Character.toUpperCase(city.charAt(0))));
        System.out.println(response.asPrettyString());


    }
}
