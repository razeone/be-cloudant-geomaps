package mx.raze.geomaps;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.ibm.cloud.cloudant.v1.model.Document;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;


@Path("/places")
public class PlaceResource {

    @Inject
    private PlaceService placeService;

    @GET
    @Path("/")
    public List<Object> getAllPlaces() {
        // TODO: implement
        return Collections.emptyList();
    }


    @POST
    @Path("/")
    public Map<String, Object> post() {
        return Collections.emptyMap();
    }

    @GET
    @Path("/{id}")
    public Map<String, Object> get(String id) {
        return placeService.getPlaceById(id).getProperties();
    }


    @PUT
    @Path("/{id}")
    public Map<String, Object> put() {
        //// TODO: put document
        return Collections.emptyMap();
    }

    @DELETE
    @Path("/{id}")
    public Map<String, Object> delete() {
        // TODO: delete document
        return Collections.emptyMap();
    }

}