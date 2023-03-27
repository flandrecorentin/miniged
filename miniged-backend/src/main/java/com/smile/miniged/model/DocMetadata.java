package com.smile.miniged.model;

public class DocMetadata {
    
    private String name;
    private String value;
    private TypeMetadata typeMetadata;



    public TypeMetadata getType() {
        return typeMetadata;
    }

    public void setType(TypeMetadata type) {
        this.typeMetadata = type;
    }

    public DocMetadata() {
        this.name = null;
        this.value = null;
        this.typeMetadata = TypeMetadata.STRING;
    }

    public DocMetadata(String name, String value, TypeMetadata type) {
        this.name = name;
        this.value = value;
        this.typeMetadata = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DocMetadata [name=" + name + ", typeMetadata=" + typeMetadata + ", value=" + value + "]";
    }
    
}
