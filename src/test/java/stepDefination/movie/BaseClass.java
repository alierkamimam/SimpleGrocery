package stepDefination.movie;

import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;

public class BaseClass {

    public final String TOKEN = ConfigReader.getProperty("api.key");
    public final String BASE_URL = ConfigReader.getProperty("base.url");

    public static RequestSpecification spec;
}
