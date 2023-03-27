package com.smile.miniged.model;

import java.util.LinkedList;
import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Type {
    
    @Id
    private String name; 
    // @ManyToMany(mappedBy = "id")
    private List<Schema> schemas;

    public Type(){
        this.name = null;
        this.schemas = new LinkedList<Schema>();
    }

    public Type(String name, List<Schema> schemas) {
        this.name = name;
        this.schemas = schemas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Schema> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<Schema> schemas) {
        this.schemas = schemas;
    }

    public void addSchema(Schema schema) {
        this.schemas.add(schema);
    }
    
}
