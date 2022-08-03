package mx.raze.geomaps.service;

import java.util.List;

import com.ibm.cloud.cloudant.v1.model.Document;

public interface PlaceService {

    public List<Document> getAllPlaces();
    public Document getPlaceById(String id);
    public Document createPlace(Document document);
    public Document updatePlace(Document document, String id);
    public void deletePlace(String id);
}
