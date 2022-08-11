package mx.raze.geomaps.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.cloud.cloudant.v1.model.AllDocsResult;
import com.ibm.cloud.cloudant.v1.model.DocsResultRow;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;

import mx.raze.geomaps.models.Place;
import mx.raze.geomaps.service.PlaceService;

import java.util.ArrayList;
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
    public void deletePlace(String id) {
        // TODO Auto-generated method stub
        
    }

    public List<Map> parseAllDocsResult(AllDocsResult allDocsResult) {
        List<Map> result = new ArrayList<>();
        allDocsResult.getRows().stream().forEach((d) -> result.add(d.getDoc().getProperties()));
        return result;
    }
    
}
