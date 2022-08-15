package mx.raze.geomaps.service;

import com.ibm.cloud.cloudant.v1.model.AllDocsResult;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;

import mx.raze.geomaps.models.Place;

import java.util.List;
import java.util.Map;

public interface PlaceService {

    AllDocsResult getAllPlaces();
    Document getPlaceById(String id);
    DocumentResult createPlace(Place place);
    DocumentResult updatePlace(Place place);
    void deletePlace(String id);

    List<Map> parseAllDocsResult(AllDocsResult allDocsResult);

    DocumentResult updatePlace(String id, Place place);
}
