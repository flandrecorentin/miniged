package com.smile.miniged.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smile.miniged.data.DocumentDAO;
import com.smile.miniged.exception.Conflict;
import com.smile.miniged.exception.InternalServerError;
import com.smile.miniged.exception.NotFound;
import com.smile.miniged.model.Doc;
import com.smile.miniged.model.DocMetadata;
import com.smile.miniged.model.DocSchema;
import com.smile.miniged.model.DocType;
import com.smile.miniged.model.Metadata;
import com.smile.miniged.model.Schema;
import com.smile.miniged.model.Type;

@Service
public class DocService implements InterfaceDocService{
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DocService.class);
    @Autowired 
    DocumentDAO documentDAO;

    @Autowired
    TypeService typeService;
    @Autowired
    SchemaService schemaService;

    public void createDocument(String nameType){
        try {
            Type typeUtilise = null;
            if(typeService.find(nameType)== null){
                logger.error("The type "+ nameType +" does not exist");
                throw new Conflict("409 The type "+ nameType +" does not exist");
            }else{
                typeUtilise = typeService.find(nameType); 
            }
            DocType docType = new DocType();
            List<DocSchema> docsSchemas = new LinkedList<DocSchema>();
            docType.setName(typeUtilise.getName());
            for (Schema schema : typeUtilise.getSchemas()) {
                DocSchema docSchema = new DocSchema();
                docSchema.setId(schema.getId());
                docSchema.setName(schema.getName());
                List<DocMetadata> docsMetadatas = new LinkedList<DocMetadata>();
                for (Metadata metadata : schema.getMetadonnees()) {
                    DocMetadata docMetadata = new DocMetadata();
                    docMetadata.setName(metadata.getName());
                    docMetadata.setType(metadata.getTypeMetadata());
                    String value = automaticValue(schema,metadata);
                    docMetadata.setValue(value);
                    docsMetadatas.add(docMetadata);
                }
                docSchema.setMetadonnees(docsMetadatas);
                docsSchemas.add(docSchema);
            }
            docType.setDocSchemas(docsSchemas);
            Doc doc = new Doc(docType);
            documentDAO.save(doc);
            logger.info("Document create with the identifier "+ doc.getId());
        } catch (Exception e) {
            logger.error("Error during creation of the document", e);    
            throw new InternalServerError("Error during creation of the document");
        }
    }
        
    public void initializationDublincoreImageFilePhotography(){
        typeService.initializationTypeFile();
        typeService.initializationTypePhotography();
    }

    public void modify(String documentId, Doc doc) {
        try {
            Doc docAModifier = null;
            docAModifier = documentDAO.findDocById(documentId);
            // existence du document
            if(docAModifier== null){
                logger.error("The document with the identifier "+ documentId+" does not exist, it's impossible to change it");
                throw new NotFound("404 The document with the identifier "+ documentId+" does not exist, it's impossible to change it");
            }
            // logger.info("-oO0Oo- document find: "+ docAModifier);
            // existence du type
            if(!doc.getDocType().getName().equals(docAModifier.getDocType().getName())){
                logger.error("The document to be modified does not have the same type ("+ docAModifier.getDocType().getName()+") as the document to be replaced ("+ doc.getDocType().getName()+")");
                throw new Conflict("409 The document to be modified does not have the same type ("+ docAModifier.getDocType().getName()+") as the document to be replaced ("+ doc.getDocType().getName()+")");
            }
            // existence des schémas dans le type
            for (DocSchema docSchema : doc.getDocType().getDocSchemas()) {
                // existence du schéma dans le type
                if(!existenceSchema(typeService.find(docAModifier.getDocType().getName()),docSchema.getId())){
                    logger.error("The schema with the identifier " + docSchema.getId() +" to be modified does not exist in the type" + docAModifier.getDocType().getName());
                    throw new Conflict("404 The schema with the identifier " + docSchema.getId() +" to be modified does not exist in the type" + docAModifier.getDocType().getName());
                }
            }
            // existence des métadonnées dans le schéma
            for (DocSchema docSchema : doc.getDocType().getDocSchemas()) {
                for (DocMetadata docMetadata : docSchema.getMetadonnees()) {
                    // existence métadonnée
                    if(!existenceMetadata(docMetadata, schemaService.find(docSchema.getId()).getMetadonnees())){
                        logger.warn("The metadata with the name " +  docMetadata.getName() +" does not exist in the schema " + docSchema.getId());
                    }else{
                        docAModifier.modificationMetadata(docMetadata, docSchema);
                    }
                }
            }
            documentDAO.save(docAModifier);
            logger.info(" The document with the identifier "+ documentId + " has been modified ");
        } catch (Exception e) {
            logger.error("##### Error during the modification of the document with the identifier " + documentId);
            throw new InternalServerError("500 ##### Error during the modification of the document with the identifier "+ documentId);
        }
    }



    public Doc find(String documentId){
        if(documentDAO.findDocById(documentId)==null){
            logger.error("The document with the identifier "+ documentId+ " does not exist");
            throw new NotFound("404 The document with the identifier "+ documentId+ " does not exist");
        }
        logger.info("Recovery of the document with the identifier "+ documentId);
        return  documentDAO.findDocById(documentId);
    }

    public List<Doc> findAll(){
        try {
            logger.info("Recovery of all documents");
        return documentDAO.findAll();
        } catch (Exception e) {
            logger.error("Error during the recovery of all documents", e);
            throw new InternalServerError("500 Error during the recovery of all documents ");
        }
    }

    public int numberOfDoc(){
        try {
            return (int) documentDAO.count();
        } catch (Exception e) {
            logger.error("Error during the counting of the number of documents", e);
            throw new InternalServerError("500 Error during the counting of the number of documents ");
        }
    }
    private static boolean existenceSchema(Type type, String schemadId ){
        boolean existence = false;
        for (Schema schema : type.getSchemas()) {
            if(schema.getId().equals(schemadId)){
                existence = true; 
            }
        }
        return existence;
    }

    private static boolean existenceMetadata(DocMetadata docMetadata, List<Metadata> metadatas){
        boolean existence = false;
        for (Metadata metadata : metadatas) {
            if(metadata.getName().equals(docMetadata.getName())){
                existence = true; 
            }
        }
        return existence; 
    }

    private String automaticValue(Schema schema, Metadata metadata){
        String value = null;
        switch(schema.getId()){
            // DUBLINCORE
            case "dublincore":
                switch(metadata.getName()){
                    case "date":
                        Date now = new Date();
                        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                        value = formater.format(now);
                        break;
                    case "author": 
                        value = "mono-user";
                    default:
                }
            //IMAGE
            case "image":
            default:
        }
        return value; 
    }
}
