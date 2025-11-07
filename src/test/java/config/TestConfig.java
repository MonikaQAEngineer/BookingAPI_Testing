package config;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class TestConfig {
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    public static final String VALID_TOKEN = "YWRtaW46cGFzc3dvcmQxMjM=";

    @Before
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
    
    public static String getBasicAuth() {
        return VALID_TOKEN;
    }
}