package mx.raze.geomaps.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;

import java.util.Collections;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;

@ApplicationScoped
public class PlaceServiceImpl implements PlaceService {

    @Inject
    Place placeRepository;

    private Document document;
    private DocumentResult documentResult;

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

    public DocumentResult createPlace(Place place) {
        try {

            this.documentResult = placeRepository.postDocument(place.getDocument());
        } catch (Exception e) {

        }
        return this.documentResult;
    }

    @Override
    public DocumentResult createPlace(Document document) {
        try {
            this.documentResult = placeRepository.postDocument(document);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        return this.documentResult;
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
