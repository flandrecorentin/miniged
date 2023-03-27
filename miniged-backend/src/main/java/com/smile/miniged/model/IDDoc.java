package com.smile.miniged.model;

public class IDDoc {
    private String idType;
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public IDDoc( ){
        idType= null;
    }
    public IDDoc(String idType) {
        this.idType = idType;
    }

}
