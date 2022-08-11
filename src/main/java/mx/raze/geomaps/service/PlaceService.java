package mx.raze.geomaps.service;

import com.ibm.cloud.cloudant.v1.model.AllDocsResult;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;

import mx.raze.geomaps.models.Place;

import java.util.List;
import java.util.Map;

public interface PlaceService {

    public AllDocsResult getAllPlaces();
    public Document getPlaceById(String id);
    public DocumentResult createPlace(Place place);
    public DocumentResult updatePlace(Place place);
    public void deletePlace(String id);

    List<Map> parseAllDocsResult(AllDocsResult allDocsResult);
}
