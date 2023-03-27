package com.smile.miniged.model;

import java.util.List;

public class IDType {
    private List<String> idSchemas;
    private String type;

    public IDType() {
    }

    public IDType(List<String> idSchemas, String type) {
        this.idSchemas = idSchemas;
        this.type = type;
    }

    public List<String> getIdSchemas() {
        return idSchemas;
    }

    public void setIdSchemas(List<String> idSchemas) {
        this.idSchemas = idSchemas;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
