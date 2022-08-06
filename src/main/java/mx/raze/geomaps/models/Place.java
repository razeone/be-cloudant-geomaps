package mx.raze.geomaps.models;

import com.ibm.cloud.cloudant.v1.model.Document;
import mx.raze.geomaps.persistence.CloudantEntity;

import java.util.Date;
import java.sql.Timestamp;

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
    private Geometry geometry;
    private Document document;
    private Date timestamp;

    private static final String DB_NAME = "places";


    public Place(String docId, String name, String description, String phoneNumber, String address, String serviceTime, Geometry geometry) {
        super(DB_NAME);
        this.docId = docId;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.serviceTime = serviceTime;
        this.geometry = geometry;
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
        this.document.put("geometry", this.geometry);
        this.document.put("timestamp", this.timestamp);
    }

    public void setPropertiesFromDocument() {
        this.docId = this.document.getId();
        this.rev = this.document.getRev();
        this.name = this.document.get("name").toString();
        this.description = this.document.get("description").toString();
        this.phoneNumber = this.document.get("phoneNumber").toString();
        this.address = this.document.get("address").toString();
        this.serviceTime = this.document.get("serviceTime").toString();
        this.geometry = (Geometry) this.document.get("geometry");
    }

    public Geometry getGeometry() {
        return this.geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    protected boolean isValid() {
        return this.address != null && this.description != null && this.name != null && this.phoneNumber != null && this.geometry != null;
    }

    public void setTimestampToNow() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public void validateToCreate() {
        if(this.isValid()) {
            this.docId = null;
            this.setTimestampToNow();
            this.setDocumentFromProperties();
        }
        else {
            throw new IllegalArgumentException("Place is not valid");
        }
    }

}
