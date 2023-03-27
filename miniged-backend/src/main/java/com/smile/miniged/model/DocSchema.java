package com.smile.miniged.model;

import java.util.List;

public class DocSchema {
    
    private String id;
    private String name; 
    private List<DocMetadata> metadonnees;

    public DocSchema(){
        this.id = null;
        this.name = null; 
        this.metadonnees = null;
    }
    public DocSchema(String id, String name, List<DocMetadata> metadonnees) {
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
    public List<DocMetadata> getMetadonnees() {
        return metadonnees;
    }
    public void setMetadonnees(List<DocMetadata> metadonnees) {
        this.metadonnees = metadonnees;
    }
    public void addMetadonee(DocMetadata metadata){
        this.metadonnees.add(metadata);
    }
    
    public DocMetadata getMetadataByName(String nameMetadata){
        DocMetadata metadata = null;
        for (DocMetadata docMetadata : this.getMetadonnees()) {
            if(docMetadata.getName().equals(nameMetadata)){
                metadata = docMetadata;
                break; 
            }
        }
        return metadata;
    }
}
