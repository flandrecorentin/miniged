package com.smile.miniged.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("classification")
public class ClassificationPlanModel {
    
    @Id
    private String id;
    private String metadata1;
    private String idSchema1;
    private String metadata2;
    private String idSchema2;
    private String idType;

    public ClassificationPlanModel() {

    }

    public ClassificationPlanModel(String id, String metadata1, String idSchema1 ,String metadata2 , String idSchema2,String idType) {
        this.id = id;
        this.metadata1 = metadata1;
        this.idSchema1 =  idSchema1;
        this.metadata2 = metadata2;
        this.idSchema2 =  idSchema2;
        this.idType = idType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMetadata1() {
        return metadata1;
    }

    public void setMetadata1(String metadata1) {
        this.metadata1 = metadata1;
    }

    public String getMetadata2() {
        return metadata2;
    }

    public void setMetadata2(String metadata2) {
        this.metadata2 = metadata2;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdSchema1() {
        return idSchema1;
    }

    public void setIdSchema1(String idSchema1) {
        this.idSchema1 = idSchema1;
    }

    public String getIdSchema2() {
        return idSchema2;
    }

    public void setIdSchema2(String idSchema2) {
        this.idSchema2 = idSchema2;
    }
    
    
}
