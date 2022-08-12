package mx.raze.geomaps.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.cloud.cloudant.v1.model.AllDocsResult;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class PlaceServiceImpl implements PlaceService {

    @Inject
    Place placeRepository;

    private static final Long DEFAULT_LIMIT = Long.valueOf(10);

    @Override
    public AllDocsResult getAllPlaces() {
        return getAllPlaces(DEFAULT_LIMIT);
    }

    public AllDocsResult getAllPlaces(Long limit) {
        if(limit > 0) {
            return placeRepository.getAllDocs(limit);
        }
        return placeRepository.getAllDocs(DEFAULT_LIMIT);
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
    public DocumentResult updatePlace(Place place) {
        place.validateToUpdate();
        return placeRepository.putDocument(place.getDocument(), place.getDocId());
    }

    @Override
    public DocumentResult updatePlace(String docId, Place place) {
        place.validateToUpdate();
        return placeRepository.putDocument(place.getDocument(), docId);
    }

    @Override
    public void deletePlace(String id) {
        // TODO Auto-generated method stub
        
    }

    public List<Map> parseAllDocsResult(AllDocsResult allDocsResult) {
        List<Map> result = new LinkedList<>();
        allDocsResult.getRows().stream().forEach((d) -> {
            Map<String, Object> doc = d.getDoc().getProperties();
            doc.put("_id", d.getId());
            doc.put("_rev", d.getValue().getRev());
            result.add(doc);
        });
        return result;
    }
    
}
