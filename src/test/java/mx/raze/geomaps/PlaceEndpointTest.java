package mx.raze.geomaps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PlaceEndpointTest {

    static final String PATH = "/places";
    static final String HEADER_NAME = "Content-Type";
    static final String CONTENT_TYPE = "application/json";
    static final String PLACE = "{\n" +
            "  \"name\": \"Place\",\n" +
            "  \"description\": \"Place description\",\n" +
            "  \"phoneNumber\": \"50505050\",\n" +
            "  \"address\": \"This is my address\",\n" +
            "  \"serviceTime\": \"Lun-Vie 7-17\",\n" +
            "  \"geometry\": {\n" +
            "    \"type\": \"Point\",\n" +
            "    \"coordinates\": [\n" +
            "      -99.133209,\n" +
            "      19.432608\n" +
            "    ]\n" +
            "  }\n" +
            "}";
    //static final String NOT_FOUND_RESPONSE = "CardTransaction 999 not found";
    //static final String INVALID_CARD_TRANSACTION_TO_UPDATE = "Invalid CardTransaction to update";
    
    /*
    @Test
    public void testCardTransactionController() throws Exception {
        given()
          .when().get(PATH)
          .then()
             .statusCode(200)
             .body(startsWith("["));
    }
    */

    @Test
    public void testPlacesResourcePost() throws Exception {
        given()
          .header(HEADER_NAME, CONTENT_TYPE)
          .body(PLACE)
          .when().post(PATH)
          .then()
             .statusCode(201)
             .body(containsString("\"id\":"));
    }
    
}
