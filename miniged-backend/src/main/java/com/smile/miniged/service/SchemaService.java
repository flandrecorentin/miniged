package com.smile.miniged.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.smile.miniged.data.SchemaDAO;
import com.smile.miniged.model.Metadata;
import com.smile.miniged.model.Schema;
import com.smile.miniged.model.TypeMetadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smile.miniged.exception.InternalServerError;
import com.smile.miniged.exception.Conflict;
import com.smile.miniged.exception.NotFound;

@Service
public class SchemaService implements InterfaceSchemaService{
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SchemaService.class);
    @Autowired
    SchemaDAO schemaDAO;

    public void initializationSchemaDublincore(){
        try {
            List<Metadata> metadataDublinCore = new LinkedList<Metadata>();
            metadataDublinCore.add(new Metadata("title", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("author", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("subject", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("description", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("contributors", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("date", TypeMetadata.DATE));
            metadataDublinCore.add(new Metadata("language", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("format", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("source", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("reference", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("state", TypeMetadata.STRING));
            metadataDublinCore.add(new Metadata("law", TypeMetadata.STRING));
            Schema schemaDublinCore = new Schema("dublincore", "DublinCore", metadataDublinCore); 
            if(schemaDAO.findSchemaById("dublincore")!= null ){
                logger.warn("The schema dublincore already exist, the old schema is replace by the new one");
                return;
            }
            schemaDAO.save(schemaDublinCore);
            logger.info("Creation of the schema DublinCore");
        } catch (Exception e) {
            logger.error("Error during creation schema DublinCore", e);
        }
    }
    public void initializationSchemaImage(){
        try {
            List<Metadata> metadataImage= new LinkedList<Metadata>();
            metadataImage.add(new Metadata("width", TypeMetadata.INT));
            metadataImage.add(new Metadata("height", TypeMetadata.INT));
            metadataImage.add(new Metadata("color", TypeMetadata.STRING));
            Schema schemaImage = new Schema("image", "Image", metadataImage); 
            if(schemaDAO.findSchemaById("image")!= null ){
                logger.warn("The schema Image already exist, the old schema is replace by the new one");
                return;
            }
            schemaDAO.save(schemaImage);
            logger.info("Creation of the schema Image");
        } catch (Exception e) {
            logger.error("Error during creation schema Image", e);
        }
    }


    public List<Schema> findAll(){
        List<Schema> allSchemas = null;
        try {
            allSchemas = schemaDAO.findAll();
            logger.info("Recovery of all schemas");
        } catch (Exception e) {
            logger.error("Error while recovering all the schemas");
            throw new InternalServerError("Error while recovering all the schemas");
        }
        return allSchemas;
    }

    public void save(Schema schema){
        try {
            for (Schema schemaATester : schemaDAO.findAll()) {
                if(schemaATester.getId().equals(schema.getId())){
                    logger.error("The schema with the identifier "+ schema.getId() +" already exists");
                    throw new Conflict("409 The schema with the identifier "+ schema.getId() +" already exists");
                }
            }
            if(schema.getId() == null){
                logger.error("The schema identifier has not been given");
                throw new Conflict("409 The schema identifier has not been given");
            }
            for(Metadata metadataATester1 : schema.getMetadonnees()){
                for(Metadata metadataATester2 : schema.getMetadonnees()){
                    if((metadataATester1!=metadataATester2) && metadataATester1.getName().equals(metadataATester2.getName())){
                        logger.error("The metadata with the name "+metadataATester1.getName() +" is a duplicate");
                        throw new Conflict("409 The metadata with the name "+metadataATester1.getName() +" is a duplicate");
                    }
                }
            }
            schemaDAO.save(schema);
            logger.info("The creation of the schema with the identifier "+ schema.getId()+" is valid");
        } catch (Exception e) {
            logger.error("##### Error during the creation of the schema with the identifier " +schema.getId());
            throw new InternalServerError("500 ##### Error during the creation of the schema with the identifier "+schema.getId());
        }
    }

    public Schema find(String schemaId){
        Schema schemaGet = null; 
        for (Schema schema : schemaDAO.findAll()) {
            if(schema.getId().equals(schemaId)){
                schemaGet = schema;
                break;
            }
        }
        if(schemaGet==null){
            logger.error("The schema with the identifier "+ schemaId +" does not exist");
            throw new NotFound("404 The schema with the identifier "+ schemaId +" does not exist");
        }
        logger.info("The recovery of the scheme with the identifier " + schemaId + " is valid");
        return schemaGet; 
    }


    public boolean exist(String schemaId){
        return schemaDAO.findSchemaById(schemaId) != null;
    }

}
