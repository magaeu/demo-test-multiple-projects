package tests;

import com.api.dto.CatQuoteDTO;
import com.api.setup.BaseTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.api.constants.Messages.FACT_MSG;
import static com.api.constants.Messages.LENGTH_MSG;
import static io.restassured.RestAssured.given;

@Tags(value = {@Tag("cat-quote"), @Tag("retrieve-quote"), @Tag("api")})
@DisplayName("[CATQUOTE-01] Retrieve quote")
public class RetrieveQuoteTest extends BaseTest {

    @Test
    @Tag("smoke")
    @DisplayName("[CATQUOTE-01.01] Retrieve quote without parameters")
    public void retrieveQuoteWithoutParameters() {
        CatQuoteDTO catQuote = given()
                .spec(request)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().as(CatQuoteDTO.class);

        String fact = catQuote.getFact();
        int length = catQuote.getLength();
        softly.assertThat(fact)
                .as(FACT_MSG, fact, null).isNotNull();
        softly.assertThat(length).as(LENGTH_MSG, 1, length).isGreaterThan(1);
    }
}
