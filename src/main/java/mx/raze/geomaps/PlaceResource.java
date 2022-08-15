package mx.raze.geomaps;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ibm.cloud.sdk.core.service.exception.ConflictException;
import com.ibm.cloud.sdk.core.service.exception.NotFoundException;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;

import org.jboss.logging.Logger;


@Path("/places")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceResource {

    private static final Logger LOG = Logger.getLogger(PlaceResource.class);

    @Inject
    PlaceService placeService;

    private HashMap<String, String> error = new HashMap<>();
    private final static String GENERIC_CLIENT_ERROR = "Bad request, check parameters";
    private final static String GENERIC_SERVER_ERROR = "Internal server error, if the error persists, notify your administrator";

    @GET
    public Response getAllPlaces() {
        LOG.info("Getting all places");
        try {
            return Response.status(Status.OK).entity(placeService.parseAllDocsResult(placeService.getAllPlaces())).build();
        }
        catch (Exception e) {
            LOG.error(e);
            LOG.error(e.getMessage());
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
            LOG.error(e);
            LOG.error(e.getMessage());
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
        try {
            return Response.status(Status.OK).entity(placeService.updatePlace(id, place)).build();
        }
        catch (IllegalArgumentException | ConflictException e) {
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

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            placeService.deletePlace(id);
            return Response.status(Status.NO_CONTENT).build();
        }
        catch (NotFoundException e) {
            setError(e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(getError()).build();
        }
        catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        }
        catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            int code = 400;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }

    public void setError(String error) {
        this.error.put("error", error);
    }


    public Map<String, String> getError() {
        return error;
    }

}