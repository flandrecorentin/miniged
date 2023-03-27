package com.smile.miniged.service;

import com.smile.miniged.model.Schema;
import java.util.List;
public interface InterfaceSchemaService {
    void initializationSchemaDublincore();
    List<Schema> findAll();
    void save(Schema schema);
    Schema find(String schemaId);
    boolean exist(String schemaId);
    void initializationSchemaImage();
}
