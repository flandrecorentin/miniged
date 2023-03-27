package com.smile.miniged.model;

import java.util.LinkedList;
import java.util.List;

public class DocType {

    private String name; 
    private List<DocSchema> schemas;

    public DocType(){
        this.name = null;
        this.schemas = null;
    }

    public DocType(String name, List<DocSchema> schemas) {
        this.name = name;
        this.schemas = schemas;
    }

    public DocType(String name) {
        this.name = name;
        this.schemas = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DocSchema> getDocSchemas() {
        return schemas;
    }

    public void setDocSchemas(List<DocSchema> schemas) {
        this.schemas = schemas;
    }

    public void addSchema(DocSchema docSchema) {
        schemas.add(docSchema);
    }

    public List<String> allIdSchemass(){
        List<String> idSchemas = new LinkedList<String>(); 
        for (DocSchema docSchema : this.getDocSchemas()) {
            idSchemas.add(docSchema.getId());
        }
        return idSchemas;
    }

    public DocSchema getSchemaById(String schemaId){
        DocSchema doc = null;
        for (DocSchema docSchema : this.getDocSchemas()) {
            if(docSchema.getId().equals(schemaId)){
                doc = docSchema;
                break; 
            }
        }
        return doc;
    }
}
