package com.smile.miniged.service;

import java.util.List;

import com.smile.miniged.model.Doc;

public interface InterfaceDocService {
    void createDocument(String nameType);
    void initializationDublincoreImageFilePhotography();
    void modify(String documentId, Doc doc);
    Doc find(String documentId);
    List<Doc> findAll();
    int numberOfDoc(); 
}
