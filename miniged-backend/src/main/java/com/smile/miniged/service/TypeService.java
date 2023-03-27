package com.smile.miniged.service;

import java.util.LinkedList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smile.miniged.data.TypeDAO;
import com.smile.miniged.exception.Conflict;
import com.smile.miniged.exception.InternalServerError;
import com.smile.miniged.exception.NotFound;
import com.smile.miniged.model.IDType;
import com.smile.miniged.model.Schema;
import com.smile.miniged.model.Type;

@Service
public class TypeService implements InterfaceTypeService{

    @Autowired
    TypeDAO typeDAO;
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TypeService.class);
    
    @Autowired
    SchemaService schemaService;

    public void initializationTypeFile(){
        try {
            schemaService.initializationSchemaDublincore();
            // Type Donnee
            List<Schema> schemasFile = new LinkedList<Schema>();
            Schema schemaDublincore = schemaService.find("dublincore");
            if(schemaDublincore == null ){
                logger.error("The schema dublincore is not found");
                throw new Conflict("The schema dublincore is not found");
            }
            schemasFile.add(schemaDublincore);
            Type file = new Type("File",schemasFile);
            if(typeDAO.findTypeByName("File")!=null){
                logger.warn("Probably the type File already exist ");
                return;
            }
            typeDAO.save(file);
            logger.info("Creation of the type File");
        } catch (Exception e) {
            logger.error("Error during creation of the type File", e);
        }
    }

    public void initializationTypePhotography(){
        try {
            schemaService.initializationSchemaDublincore();
            schemaService.initializationSchemaImage();
            // Type Donnee
            List<Schema> schemasPhotography = new LinkedList<Schema>();
            Schema schemaDublincore = schemaService.find("dublincore");
            if(schemaDublincore == null ){
                logger.error("The schema dublincore is not found");
                throw new Conflict("The schema dublincore is not found");
            }
            schemasPhotography.add(schemaDublincore);
            Schema schemaImage = schemaService.find("image");
            if(schemaImage == null ){
                logger.error("The schema image is not found");
                throw new Conflict("The schema image is not found");
            }
            schemasPhotography.add(schemaImage);
            Type photography = new Type("Photography",schemasPhotography);
            if(typeDAO.findTypeByName("Photography")!=null){
                logger.warn("Probably the type Photography already exist ");
                return;
            }
            typeDAO.save(photography);
            logger.info("Creation of the type Photography");
        } catch (Exception e) {
            logger.error("Error during creation of the type Photography", e);
        }
    }


    public void save(IDType idType){
        // vérifie unicité du type
        if(exist(idType.getType())){
            logger.error("The type with the name " + idType.getType() +" already exist");
            throw new Conflict("409 The type with the name " +idType.getType() +" already exist");
        }
        if(idType.getType() == null){
            logger.error("The type identifier has not been given");
            throw new Conflict("409 The type identifier has not been given");
        }
        // vérifie existence de chaque schéma
        for (String idSchemaATester : idType.getIdSchemas()) {
            if(!schemaService.exist(idSchemaATester)){
                logger.error("The schema with the identifier "+ idSchemaATester+" does not exist");
                throw new NotFound("404 The schema with the identifier "+ idSchemaATester+" does not exist");
            }
        }
        Type type = new Type();
        type.setName(idType.getType());
        for (String idSchema : idType.getIdSchemas()) {
            type.addSchema(schemaService.find(idSchema));
        }
        typeDAO.save(type);
        logger.info("The creation of the type with the name "+ type.getName() +" is valid");
        // types.add(type);
    }


    public List<Type> findAll(){
        try {
            logger.info("Recovery of all types");
            return typeDAO.findAll();
        } catch (Exception e) {
            logger.error("Error during the recovery of all types", e);
            throw new InternalServerError("500 Error during the recovery of all types");
        }
    }

    public Type find(String typeName){
        Type typeGet = null;
        typeGet = typeDAO.findTypeByName(typeName);
        if(typeGet==null) {
            logger.error("The type with the identifier "+ typeName +" does not exist");
            throw new NotFound("404 The schema with the identifier "+ typeName +" does not exist");
        }
        logger.info("The recovery with the name " + typeName+" is valid");
        return typeGet;
    }

    public boolean exist(String typeName){
        return typeDAO.findTypeByName(typeName) != null;
    }
}
