package stepDefination;

import bsh.Token;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetDieselPrice {
    private static RequestSpecification spec;
    Response response;
    final String TOKEN = "apikey 1Oo7Vsutf6itQmtI4VUzKB:0HL1tWR3JbFFzCEjLSrka5";

    @Given("It brings the diesel prices of {string} and {string}")
    public void it_brings_the_diesel_prices_of_city_and_district(String city, String district) {

        spec = new RequestSpecBuilder()
                .setBaseUri("https://api.collectapi.com/gasPrice/europeanCountries")
                .build();

        response = RestAssured.given()
                .spec(spec)
                .header("Authorization", TOKEN)
                .header("Content-Type", "application/json")
                .queryParam("district", district)
                .queryParam("city", city)
                .when()
                .get();

        System.out.println(city + " -> ," + district);
        System.out.println(response.asPrettyString());
    }
}
