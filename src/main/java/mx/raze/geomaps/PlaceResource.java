package mx.raze.geomaps;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ibm.cloud.cloudant.v1.Cloudant;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;
import com.ibm.cloud.cloudant.v1.model.PutDocumentOptions;
import com.ibm.cloud.sdk.core.service.exception.NotFoundException;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;


@Path("/places")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceResource {

    @Inject
    PlaceService placeService;

    private HashMap<String, String> error = new HashMap<>();
    private final static String GENERIC_CLIENT_ERROR = "Bad request, check parameters";
    private final static String GENERIC_SERVER_ERROR = "Internal server error, if the error persists, notify your administrator";

    @GET
    public Response getAllPlaces() {
        try {
            return Response.status(Status.OK).entity(placeService.parseAllDocsResult(placeService.getAllPlaces())).build();
        }
        catch (Exception e) {
            setError(GENERIC_SERVER_ERROR);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
    }


    @POST
    @Transactional
    public Response post(Place place) {
        try {
            return Response.status(Status.CREATED).entity(placeService.createPlace(place)).build();
        }
        catch (IllegalArgumentException e) {
            setError(e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        }
        catch (Exception e) {
            setError(GENERIC_SERVER_ERROR);
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
            setError(GENERIC_CLIENT_ERROR);
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        }
        catch (NotFoundException e) {
            setError(e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(getError()).build();
        }
        catch (Exception e) {
            setError(GENERIC_SERVER_ERROR);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
    }


    @PUT
    @Transactional
    @Path("/{id}")
    public Response put(@PathParam("id") String id, Place place) {
        Cloudant service = Cloudant.newInstance();
        Document eventDoc = new Document();
        eventDoc.put("type", "event");
        eventDoc.put("userid", "abc123");
        eventDoc.put("eventType", "addedToBasket");
        eventDoc.put("productId", "10000442");
        eventDoc.put("date", "2019-01-28T10:44:22.000Z");
        PutDocumentOptions documentOptions =
                new PutDocumentOptions.Builder()
                        .db("places")
                        .docId("06ff2e6fe6c8806702b722a2d8891983")
                        .document(eventDoc)
                        .build();
        DocumentResult response =
                service.putDocument(documentOptions).execute()
                        .getResult();
        System.out.println(response);
        return Response.status(Status.OK).entity(response).build();
        /*
        return Response.status(Status.OK).entity(placeService.updatePlace(id, place)).build();

        try {
            return Response.status(Status.OK).entity(placeService.updatePlace(id, place)).build();
        }
        catch (IllegalArgumentException e) {
            setError(GENERIC_CLIENT_ERROR);
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        }
        catch (Exception e) {
            setError(GENERIC_SERVER_ERROR);
            System.out.println(e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
        */
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