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
    //static final String NOT_FOUND_RESPONSE = "CardTransaction 999 not found";
    //static final String INVALID_CARD_TRANSACTION_TO_UPDATE = "Invalid CardTransaction to update";
    
    
    @Test
    public void testCardTransactionController() throws Exception {
        given()
          .when().get(PATH)
          .then()
             .statusCode(200)
             .body(startsWith("["));
    }

    @Test
    public void testCardTransactionControllerGet() throws Exception {
        given()
          .when().get(PATH + "/9")
          .then()
             .statusCode(200)
             .body(containsString("\"id\":9"));
    }
    
}