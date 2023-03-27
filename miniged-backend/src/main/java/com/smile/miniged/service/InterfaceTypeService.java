package com.smile.miniged.service;

import java.util.List;

import com.smile.miniged.model.IDType;
import com.smile.miniged.model.Type;

public interface InterfaceTypeService {
    void initializationTypeFile();
    void save(IDType idType);
    Type find(String typeName);
    List<Type> findAll();
    boolean exist(String typeName);
    void initializationTypePhotography();

}
