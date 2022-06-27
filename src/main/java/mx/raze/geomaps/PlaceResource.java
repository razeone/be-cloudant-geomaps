package mx.raze.geomaps;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.ibm.cloud.cloudant.v1.model.Document;

import mx.raze.geomaps.models.Place;


@Path("/places")
public class PlaceResource {

    

    @GET
    @Path("/{id}")
    public Map<String, Object> get() {
        Place place = new Place("places");
        Document result = place.getDocumentById("152b04ab806c5b58bdd89258f78db3a1");
        return result.getProperties();
    }

}