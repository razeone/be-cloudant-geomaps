package mx.raze.geomaps.persistence;

import com.ibm.cloud.cloudant.v1.model.*;

import mx.raze.geomaps.persistence.dao.CloudantEntityBase;

public abstract class CloudantEntity extends CloudantEntityBase {

    private String dbName;
    private GetDocumentOptions getDocumentOptions;
    private PostDocumentOptions postDocumentOptions;
    private PutDocumentOptions putDocumentOptions;
    private DeleteDocumentOptions deleteDocumentOptions;
    private PostAllDocsOptions postAllDocsOptions;
    
    protected CloudantEntity() {
        super();
    }

    protected CloudantEntity(String dbName) {
        super();
        this.dbName = dbName;
    }


    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public GetDocumentOptions getGetDocumentOptions() {
        return this.getDocumentOptions;
    }

    public void setGetDocumentOptions(GetDocumentOptions getDocumentOptions) {
        this.getDocumentOptions = getDocumentOptions;
    }

    public void setGetDocumentOptions(String docId) {
        this.getDocumentOptions = new GetDocumentOptions.Builder()
        .db(this.dbName)
        .docId(docId)
        .build();
    }

    public PostDocumentOptions getPostDocumentOptions() {
        return this.postDocumentOptions;
    }

    public void setPostDocumentOptions(PostDocumentOptions postDocumentOptions) {
        this.postDocumentOptions = postDocumentOptions;
    }

    public void setPostAllDocsOptions(Long limit) {
        this.postAllDocsOptions = new PostAllDocsOptions.Builder()
                .db(this.dbName)
                .includeDocs(true)
                .limit(limit)
                .build();
    }

    public void setPostAllDocsOptions(Long limit, String startKey) {
        this.postAllDocsOptions = new PostAllDocsOptions.Builder()
                .db(this.dbName)
                .includeDocs(true)
                .startKey(startKey)
                .limit(limit)
                .build();
    }

    public void setPostDocumentOptionsEnt(Document document) {
        this.postDocumentOptions = new PostDocumentOptions.Builder()
        .db(this.dbName)
        .document(document).build();
    }

    
    public PutDocumentOptions getPutDocumentOptions() {
        return this.putDocumentOptions;
    }

    public void setPutDocumentOptions(PutDocumentOptions putDocumentOptions) {
        this.putDocumentOptions = putDocumentOptions;
    }

    public void setPutDocumentOptions(String docId, Document document) {
        this.putDocumentOptions = new PutDocumentOptions.Builder()
            .db(this.dbName)
            .docId(docId)
            .document(document)
            .build();
    }

    public DeleteDocumentOptions getDeleteDocumentOptions() {
        return this.deleteDocumentOptions;
    }

    public void setDeleteDocumentOptions(DeleteDocumentOptions deleteDocumentOptions) {
        this.deleteDocumentOptions = deleteDocumentOptions;
    }

    public void setDeleteDocumentOptions(String docId, String rev) {
        this.deleteDocumentOptions = new DeleteDocumentOptions.Builder()
            .db(this.dbName)
            .docId(docId)
            .rev(rev)
            .build();
    }

    public AllDocsResult getAllDocs(Long limit) {
        setPostAllDocsOptions(limit);
        return getAllDocs(this.postAllDocsOptions);
    }

    @Override
    public DocumentResult postDocument(Document document) {
        setPostDocumentOptionsEnt(document);
        return this.getCloudantInstance().postDocument(this.postDocumentOptions).execute().getResult();
    }

    @Override
    public Document getDocumentById(String docId) {
        setGetDocumentOptions(docId);
        return this.getCloudantInstance().getDocument(this.getDocumentOptions).execute().getResult();
    }

    @Override
    public DocumentResult deleteDocument(String docId, String rev) {
        setDeleteDocumentOptions(docId, rev);
        return this.getCloudantInstance().deleteDocument(this.deleteDocumentOptions).execute().getResult();
    }


    @Override
    public DocumentResult putDocument(Document document, String docId) {
        setPutDocumentOptions(docId, document);
        System.out.println(getPutDocumentOptions());
        return this.getCloudantInstance().putDocument(this.putDocumentOptions).execute().getResult();
    }


}
