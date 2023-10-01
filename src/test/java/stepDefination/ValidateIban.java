package stepDefination;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Assert;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidateIban {
    static RequestSpecification spec;
    Response response;
    final String API_KEY = "02ab9dd4d8dc92cbb09bd251316154a509fe0e9c";

    @Given("{string} is valid, then verify the status code {int}")
    public void isValidThenVerifyTheStatusCode(String iban, int statusCode) {

        spec = new RequestSpecBuilder()
                .setBaseUri("https://api.ibanapi.com/v1/validate-basic")
                .build();

        response = RestAssured.given()
                .spec(spec)
                .formParam("iban", iban)
                .formParam("api_key", API_KEY)
                .when()
                .post();

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, actualStatusCode);

        System.out.println(response.asPrettyString());

    }
}
