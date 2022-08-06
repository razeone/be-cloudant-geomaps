package mx.raze.geomaps.service.impl;

import java.util.List;
import java.util.Collections;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;

@ApplicationScoped
public class PlaceServiceImpl implements PlaceService {

    @Inject
    Place placeRepository;

    @Override
    public List<Document> getAllPlaces() {
        // TODO Auto-generated method stub
        return Collections.emptyList();
    }

    @Override
    public Document getPlaceById(String id) {
        return placeRepository.getDocumentById(id);
    }

    @Override
    public DocumentResult createPlace(Place place) {
        place.validateToCreate();
        return placeRepository.postDocument(place.getDocument());
    }

    @Override
    public Document updatePlace(Place place) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletePlace(String id) {
        // TODO Auto-generated method stub
        
    }
    
}
