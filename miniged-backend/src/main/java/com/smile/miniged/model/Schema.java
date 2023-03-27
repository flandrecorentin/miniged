package com.smile.miniged.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Schema {

    @Id
    private String id;
    private String name; 
    private List<Metadata> metadonnees;

    public Schema(){
        this.id = null;
        this.name = null; 
        this.metadonnees = null;
    }
    public Schema(String id, String name, List<Metadata> metadonnees) {
        this.id = id;
        this.name = name; 
        this.metadonnees = metadonnees;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Metadata> getMetadonnees() {
        return metadonnees;
    }
    public void setMetadonnees(List<Metadata> metadonnees) {
        this.metadonnees = metadonnees;
    }
}
