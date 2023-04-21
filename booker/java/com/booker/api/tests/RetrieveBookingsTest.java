package tests;

import com.booker.api.dto.BookingIdDTO;
import com.booker.api.setup.BaseTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Tags(value = {@Tag("booker"), @Tag("retrieve-booking"), @Tag("api")})
@DisplayName("[BOOKER-01] Retrieve bookings")
public class RetrieveBookingsTest extends BaseTest {

    private static final String LENGTH_MSG = "Length should be greater than %s but got %s";

    @Test
    @Tag("smoke")
    @DisplayName("[BOOKER-01.01] Retrieve bookings")
    public void retrieveBookings() {
        BookingIdDTO[] bookings = given()
                .spec(request)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().as(BookingIdDTO[].class);

        int length = bookings.getLength();
        softly.assertThat(length).as(LENGTH_MSG, 1, length).isGreaterThan(1);
    }
}
