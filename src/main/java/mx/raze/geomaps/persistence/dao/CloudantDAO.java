package mx.raze.geomaps.persistence.dao;


import com.ibm.cloud.cloudant.v1.model.AllDocsResult;
import com.ibm.cloud.cloudant.v1.model.DeleteDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;
import com.ibm.cloud.cloudant.v1.model.GetDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.PostAllDocsOptions;
import com.ibm.cloud.cloudant.v1.Cloudant;
import com.ibm.cloud.cloudant.v1.model.AllDocsQueriesResult;
import com.ibm.cloud.cloudant.v1.model.PostAllDocsQueriesOptions;
import com.ibm.cloud.cloudant.v1.model.PostDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.PutDocumentOptions;


public interface CloudantDAO {

    void setApiKey();
    void setServiceUrl();
    void setAuthenticator();
    void setCloudantInstance();
    Cloudant getCloudantInstance();
    AllDocsResult getAllDocs(PostAllDocsOptions docsOptions);
    AllDocsQueriesResult postAllDocsQuery(PostAllDocsQueriesOptions queriesOptions);
    DocumentResult postDocument(PostDocumentOptions documentOptions);
    Document getDocument(GetDocumentOptions getDocumentOptions);
    DocumentResult deleteDocument(DeleteDocumentOptions deleteDocumentOptions);
    DocumentResult putDocument(PutDocumentOptions documentOptions);
}
