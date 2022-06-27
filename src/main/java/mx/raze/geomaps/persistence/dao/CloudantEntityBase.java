package mx.raze.geomaps.persistence.dao;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.cloudant.v1.Cloudant;
import com.ibm.cloud.cloudant.v1.model.AllDocsResult;
import com.ibm.cloud.cloudant.v1.model.DeleteDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentResult;
import com.ibm.cloud.cloudant.v1.model.GetDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.PostAllDocsOptions;
import com.ibm.cloud.cloudant.v1.model.AllDocsQueriesResult;
import com.ibm.cloud.cloudant.v1.model.PostAllDocsQueriesOptions;
import com.ibm.cloud.cloudant.v1.model.PostDocumentOptions;
import com.ibm.cloud.cloudant.v1.model.PutDocumentOptions;

import org.eclipse.microprofile.config.ConfigProvider;

public abstract class CloudantEntityBase implements CloudantDAO {
    

    private IamAuthenticator authenticator;
    private Cloudant cloudantInstance;
    private String serviceUrl;
    private String apiKey;

    protected CloudantEntityBase() {
        setServiceUrl();
        setApiKey();
        setAuthenticator();
        setCloudantInstance();
    }

    @Override
    public void setApiKey() {
        this.apiKey = ConfigProvider.getConfig().getValue("cloudant.apikey", String.class);
    }

    @Override
    public void setServiceUrl() {
        this.serviceUrl = ConfigProvider.getConfig().getValue("cloudant.url", String.class);
    }

    @Override
    public void setAuthenticator() {
        this.authenticator = new IamAuthenticator.Builder()
            .apikey(this.apiKey)
            .build();
    }

    @Override
    public void setCloudantInstance() {
        this.cloudantInstance = new Cloudant(Cloudant.DEFAULT_SERVICE_NAME, this.authenticator);
        this.cloudantInstance.setServiceUrl(this.serviceUrl);
    }

    @Override
    public Cloudant getCloudantInstance() {
        return this.cloudantInstance;
    }


    @Override
    public AllDocsResult getAllDocs(PostAllDocsOptions docsOptions) {
        return this.cloudantInstance.postAllDocs(docsOptions).execute().getResult();
    }

    @Override
    public AllDocsQueriesResult postAllDocsQuery(PostAllDocsQueriesOptions queriesOptions) {
        return this.cloudantInstance.postAllDocsQueries(queriesOptions).execute().getResult();
    }
    @Override
    public DocumentResult postDocument(PostDocumentOptions documentOptions) {
        return this.cloudantInstance.postDocument(documentOptions).execute().getResult();
    }
    
    @Override
    public Document getDocument(GetDocumentOptions getDocumentOptions) {
        return this.cloudantInstance.getDocument(getDocumentOptions).execute().getResult();
    }

    @Override
    public DocumentResult deleteDocument(DeleteDocumentOptions deleteDocumentOptions) {
        return this.cloudantInstance.deleteDocument(deleteDocumentOptions).execute().getResult();
    }

    @Override
    public DocumentResult putDocument(PutDocumentOptions documentOptions) {
        return this.cloudantInstance.putDocument(documentOptions).execute().getResult();
    }

    public abstract DocumentResult postDocument(Document document);
    public abstract Document getDocumentById(String docId);
    public abstract DocumentResult deleteDocument(String docId);
    public abstract DocumentResult putDocument(Document document, String docId);

}
