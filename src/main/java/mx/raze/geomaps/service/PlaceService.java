package mx.raze.geomaps.service;

import java.util.List;

import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;

import mx.raze.geomaps.models.Place;

public interface PlaceService {

    public List<Document> getAllPlaces();
    public Document getPlaceById(String id);
    public DocumentResult createPlace(Place place);
    public Document updatePlace(Place place);
    public void deletePlace(String id);
}
