package mx.raze.geomaps;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;


@Path("/places")
public class PlaceResource {

    @Inject
    PlaceService placeService;

    private HashMap<String, String> error = new HashMap<>();

    @GET
    public List<Object> getAllPlaces() {
        // TODO: implement search feature
        return Collections.emptyList();
    }


    @POST
    @Transactional
    public Response post(Place place) {
        try {
            return Response.status(Status.OK).entity(placeService.createPlace(place)).build();
        }
        catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        }
        catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        try {
            return Response.status(Status.OK).entity(placeService.getPlaceById(id).getProperties()).build();
        }
        catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        }
        catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
    }


    @PUT
    @Path("/{id}")
    public Response put(@PathParam("id") String id) {
        try {
            //// TODO: put document implemented on service layer
            return Response.status(Status.OK).entity(placeService.getPlaceById(id).getProperties()).build();
        }
        catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        }
        catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            //// TODO: delete document implemented on service layer
            return Response.status(Status.OK).entity(placeService.getPlaceById(id).getProperties()).build();
        }
        catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        }
        catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
    }

    public void setError(String error) {
        this.error.put("error", error);
    }


    public Map<String, String> getError() {
        return error;
    }

}