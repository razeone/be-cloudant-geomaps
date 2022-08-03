package mx.raze.geomaps.models;

import com.ibm.cloud.cloudant.v1.model.Document;
import mx.raze.geomaps.persistence.CloudantEntity;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Place extends CloudantEntity {

    private String docId;
    private String rev;
    private String name;
    private String description;
    private String phoneNumber;
    private String address;
    private String serviceTime;
    private Document document;

    private static final String DB_NAME = "places";


    public Place(String docId, String name, String description, String phoneNumber, String address, String serviceTime, String dbName) {
        super(dbName);
        this.docId = docId;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.serviceTime = serviceTime;
        setDocumentFromProperties();
    }
    
    public Place() {
        super(DB_NAME);
    }

    public String getDocId() {
        return this.docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getRev() {
        return this.rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServiceTime() {
        return this.serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void setDocumentFromProperties() {
        this.document = new Document();
        this.document.setId(this.docId);
        this.document.put("name", this.name);
        this.document.put("description", this.description);
        this.document.put("phoneNumber", this.phoneNumber);
        this.document.put("address", this.address);
        this.document.put("serviceTime", this.serviceTime);
    }


}
