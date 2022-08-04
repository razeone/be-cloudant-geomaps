package mx.raze.geomaps;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.ibm.cloud.cloudant.v1.model.Document;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;


@Path("/places")
public class PlaceResource {

    @Inject
    PlaceService placeService;

    @GET
    public List<Object> getAllPlaces() {
        // TODO: implement
        return Collections.emptyList();
    }


    @POST
    //@Transactional
    public Map<String, Object> post(Place place) {
        System.out.println("Im here");
        //System.out.println("posting place: " + place.getGeometry().getCoordinates().toString());
        return Collections.emptyMap();
    }

    @GET
    @Path("/{id}")
    public Map<String, Object> get(@PathParam("id") String id) {
        return placeService.getPlaceById(id).getProperties();
    }


    @PUT
    @Path("/{id}")
    public Map<String, Object> put(@PathParam("id") String id) {
        //// TODO: put document
        return Collections.emptyMap();
    }

    @DELETE
    @Path("/{id}")
    public Map<String, Object> delete(@PathParam("id") String id) {
        // TODO: delete document
        return Collections.emptyMap();
    }

}