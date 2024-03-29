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

    static final String PLACE1 = "{\n" +
            "  \"name\": \"Place1\",\n" +
            "  \"description\": \"Place1 description\",\n" +
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
    static final String EXISTING_DOC_ID = "33c84e2c4259915b106d82c7eb1eb3dc";
    static final String NOT_FOUND = "not_found";

    @Test
    public void testPlacesResourceGetAllDocs() throws Exception {
        given()
          .when().get(PATH)
          .then()
             .statusCode(200)
             .body(startsWith("["));
    }

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

    @Test
    public void testPlacesResourceGetById() throws Exception {
        given()
                .when().get(PATH + "/" + EXISTING_DOC_ID)
                .then()
                .statusCode(200)
                .body(startsWith("{"));
    }

    @Test
    public void testPlacesResourceGetByIdNotFound() throws Exception {
        given()
                .when().get(PATH + "/1")
                .then()
                .statusCode(404)
                .body(containsString(NOT_FOUND));
    }

    @Test
    public void testPlacesResourcePut() throws Exception {
        given()
                .header(HEADER_NAME, CONTENT_TYPE)
                .body(PLACE1)
                .when().put(PATH + "/" + EXISTING_DOC_ID)
                .then()
                .statusCode(200)
                .body(containsString("\"id\":"));
    }

    @Test
    public void testPlacesResourcePutNotFound() throws Exception {
        given()
                .header(HEADER_NAME, CONTENT_TYPE)
                .body(PLACE1)
                .when().put(PATH + "/1")
                .then()
                .statusCode(404)
                .body(containsString(NOT_FOUND));
    }
    
}
