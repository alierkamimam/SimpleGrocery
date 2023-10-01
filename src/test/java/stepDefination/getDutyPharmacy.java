package stepDefination;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class getDutyPharmacy {

    private static RequestSpecification spec;
    Response response;
    final String TOKEN = "apikey 1Oo7Vsutf6itQmtI4VUzKB:0HL1tWR3JbFFzCEjLSrka5";

    @Given("It brins the duty pharmacy list of {string} and {string}, then verify status code is {int}")
    public void it_brins_the_duty_pharmacy_list_of_and_then_verify_status_code_is(String city, String district, Integer statusCode) {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://api.collectapi.com/health/dutyPharmacy")
                .build();

        response = RestAssured.given()
                .spec(spec)
                .header("Authorization", TOKEN)
                .header("Content-Type", "application/json")
                .queryParam("ilce", district)
                .queryParam("il", city)
                .when()
                .get();

        int responseStatusCode = response.getStatusCode();

        spec = new RequestSpecBuilder()
                .setBaseUri("https://footapi7.p.rapidapi.com/api/img/flag/")
                .build();


    }


}
