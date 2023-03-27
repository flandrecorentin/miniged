package com.smile.miniged.model;


public class Metadata {
    
    private String name;
    private TypeMetadata typeMetadata;

    

    public Metadata() {
        this.name = null;
        this.typeMetadata= null;
    }
    public Metadata(String name){
        this.name = name;
        this.typeMetadata = TypeMetadata.STRING;
    }
    public Metadata(String name, TypeMetadata type) {
        this.name = name;
        this.typeMetadata = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeMetadata getTypeMetadata() {
        return typeMetadata;
    }

    public void setTypeMetadata(TypeMetadata type) {
        this.typeMetadata = type;
    }
}
