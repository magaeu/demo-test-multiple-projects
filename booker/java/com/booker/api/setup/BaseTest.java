package setup;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    protected static RequestSpecification request;
    protected static ResponseSpecification response;
    protected static SoftAssertions softly;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = getBaseUrl();
    }

    private static String getBaseUrl() {
        return "https://restful-booker.herokuapp.com";
    }

    @BeforeEach
    public void requestSpec() {
        request = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @BeforeEach
    public void softAssertionsSetup() {
        softly = new SoftAssertions();
    }

    @BeforeEach
    public void responseSpec() {
        response = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();

    }

    @AfterAll
    public static void tearDown() {
        RestAssured.reset();
    }

    @AfterEach
    public void softAssertionsTearDown() {
        softly.assertAll();
    }
}
