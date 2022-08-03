package mx.raze.geomaps.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.cloud.cloudant.v1.model.Document;
import java.util.Collections;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;

@ApplicationScoped
public class PlaceServiceImpl implements PlaceService {

    @Inject
    private Place placeRepository;

    private Document document;

    @Override
    public List<Document> getAllPlaces() {
        // TODO Auto-generated method stub
        return Collections.emptyList();
    }

    @Override
    public Document getPlaceById(String id) {
        try {
            this.document = placeRepository.getDocumentById(id);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        return this.document;
    }

    @Override
    public Document createPlace(Document document) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document updatePlace(Document document, String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletePlace(String id) {
        // TODO Auto-generated method stub
        
    }
    
}
