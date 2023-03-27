package com.smile.miniged.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smile.miniged.model.ClassificationPlan;
import com.smile.miniged.data.ClassificationPlanModelDAO;
import com.smile.miniged.exception.Conflict;
import com.smile.miniged.exception.InternalServerError;
import com.smile.miniged.exception.NotFound;
import com.smile.miniged.model.ClassificationPlanModel;
import com.smile.miniged.model.Doc;
import com.smile.miniged.model.DocSchema;
import com.smile.miniged.model.Folder;
import com.smile.miniged.model.Metadata;

@Service
public class ClassificationService implements InterfaceClassificationService{
    
    @Autowired
    ClassificationPlanModelDAO classificationPlanModelDAO ;

    @Autowired
    SchemaService schemaService;

    @Autowired
    DocService docService;


    @Autowired
    TypeService typeService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ClassificationPlanModel.class);

    public void create(ClassificationPlanModel classificationPlanModel){
        try {
            // verif if the Id is not empty
            if(classificationPlanModel.getId()==null){
                logger.error("The identifier of the classification plan can not be null");
                throw new Conflict("409 The identifier of the classification plan can not be null");
            }
            // verif if the Id is already use by another classification plan 
            if(classificationPlanModelDAO.findClassificationPlanModelById(classificationPlanModel.getId())!=null){
                logger.error("Error in creating the classification plan because a classification plan with the identifier " +classificationPlanModel.getId()+" already exist");
                throw new Conflict("409 Error in creating the classification plan because a classification plan with the identifier " +classificationPlanModel.getId()+" already exist");
            }
            // verif if the schema1 exist
            if(!schemaService.exist(classificationPlanModel.getIdSchema1())){
                logger.error("The first schema use for the classification " +classificationPlanModel.getIdSchema1()+ "does not exist");
                throw new NotFound("404 The first schema use for the classification " +classificationPlanModel.getIdSchema1()+ "does not exist");
            }
            // verif metadata1 is in the schema1
            boolean coherenceMetadata1 = false;
            for (Metadata metadata : schemaService.find(classificationPlanModel.getIdSchema1()).getMetadonnees()) {
                if(classificationPlanModel.getMetadata1().equals(metadata.getName())){
                    coherenceMetadata1 = true;
                }
            }
            if(!coherenceMetadata1){
                logger.error("The first metadata use for the classification " +classificationPlanModel.getMetadata1()+ " is not in the schema " + classificationPlanModel.getIdSchema1());
                throw new NotFound("404 The first metadata use for the classification " +classificationPlanModel.getMetadata1()+ " is not in the schema " + classificationPlanModel.getIdSchema1());
            }
            // same thing for schema2 and metadata2 if it exist
            if(classificationPlanModel.getIdSchema2()!=null){
                // verif schema2
                if(!schemaService.exist(classificationPlanModel.getIdSchema2())){
                    logger.error("The second schema use for the classification " +classificationPlanModel.getIdSchema2()+ " does not exist");
                    throw new NotFound("404 The second schema use for the classification " +classificationPlanModel.getIdSchema2()+ "does not exist");
                }

                // verif metadata2 is in the schema2
                boolean coherenceMetadata2 = false;
                for (Metadata metadata : schemaService.find(classificationPlanModel.getIdSchema2()).getMetadonnees()) {
                    if(classificationPlanModel.getMetadata2().equals(metadata.getName())){
                        coherenceMetadata2 = true;
                    }
                }
                if(!coherenceMetadata2){
                    logger.error("The second metadata use for the classification " +classificationPlanModel.getMetadata2()+ " is not in the schema " + classificationPlanModel.getIdSchema2());
                    throw new NotFound("404 The second metadata use for the classification " +classificationPlanModel.getMetadata2()+ " is not in the schema " + classificationPlanModel.getIdSchema2());
                }
            }
            // verif the type exist if it is use for the classification
            if(classificationPlanModel.getIdType()!=null){
                if(!typeService.exist(classificationPlanModel.getIdType())){
                    logger.error("The type use for the classification " +classificationPlanModel.getIdType()+ " does not exist");
                    throw new NotFound("404 The type use for the classification " +classificationPlanModel.getIdType()+ " does not exist");
                }
            }
            classificationPlanModelDAO.save(classificationPlanModel);
            logger.info("The creation of the classification plan " + classificationPlanModel.getId() + " succeed");
        } catch (Exception e) {
            logger.error("##### The creation of the classification plan with the identifier "+ classificationPlanModel.getId()+ " failed",e );
            throw new InternalServerError("500 The creation of the classification plan with the identifier "+ classificationPlanModel.getId()+ " failed");
        }
    }

    public List<ClassificationPlanModel> findAll(){
        try {
            List<ClassificationPlanModel> listeClassificationPlanModel = classificationPlanModelDAO.findAll();
            logger.info("Recovery of all classification plan model succeed");
            return listeClassificationPlanModel;
        } catch (Exception e) {
            logger.error("#### Error during recovery of all classification plan models");
            throw new InternalServerError("500 Error during recovery of all classification plan models");
        }
        
    }

    public ClassificationPlanModel find(String classificationId) {
        try {
            ClassificationPlanModel classification = classificationPlanModelDAO.findClassificationPlanModelById(classificationId);
            if(classification ==null){
                logger.error("The classification plan model with the identifier " +classificationId+ "does not exist");
                throw new NotFound("404 The classification plan model with the identifier " +classificationId+ "does not exist");
            }
            logger.info("The recovery of the classification plan mode with the identifier " +classificationId+ " succeed");
            return classification;
        } catch (Exception e) {
            logger.error("##### Error during the recovery of the classification plan model with the identifier " + classificationId);
            throw new InternalServerError("500 Error during the recovery of the classification plan model with the identifier " + classificationId);
        }
    }
    public boolean exist(String classificationId){
        if(classificationPlanModelDAO.findClassificationPlanModelById(classificationId)==null){
            return false;
        }
        else{
            return true; 
        }
    }

    public ClassificationPlan construct(String classificationId) {
        if(!exist(classificationId)){
            logger.error("The classification plan with the identifier "+ classificationId + " does not exist");
            throw new NotFound("404 The classification plan with the identifier "+ classificationId + " does not exist");
        }
        ClassificationPlanModel classificationPlanModel = find(classificationId);
        // all folders of first level
        Map<String,Folder> firstLevel = new HashMap<>();
        // all folders of second level
        Map<String,Folder> secondLevel = new HashMap<>();
        // name of folders of firstLevel
        List<String> values = new LinkedList<String>();
        // name of subfolders of firstLevel
        List<String> secondValues = new LinkedList<String>();
        String type = classificationPlanModel.getIdType();
        String firstSchema = classificationPlanModel.getIdSchema1();
        boolean classificationOnTwoLevel = classificationPlanModel.getIdSchema2()!=null;
        String secondSchema = classificationPlanModel.getIdSchema2();
        // construction first level
        for (Doc doc : docService.findAll()) {
            // only if right type && if contains first schema
            if((type==null || doc.getDocType().getName().equals(type)) && doc.getDocType().allIdSchemass().contains(firstSchema)){
                DocSchema docSchema = doc.getDocType().getSchemaById(firstSchema);
                String value = docSchema.getMetadataByName(classificationPlanModel.getMetadata1()).getValue();
                // if new values -> create new folder
                if(!values.contains(value)){
                    values.add(value);
                    Folder folderLevel1 = new Folder(value);
                    // if schema2 exist -> creation of the second level
                    if(classificationOnTwoLevel){
                        // logger.info("secondSchema" + secondSchema);
                        DocSchema docSchema2 = doc.getDocType().getSchemaById(secondSchema);
                        logger.info("metadata2:"+ classificationPlanModel.getMetadata2());
                        logger.info("name 2"+  (docSchema2.getMetadataByName(classificationPlanModel.getMetadata2())).getName());
                        logger.info("DocMetadata"+ (docSchema2.getMetadataByName(classificationPlanModel.getMetadata2())));
                        logger.info("value 2"+ (docSchema2.getMetadataByName(classificationPlanModel.getMetadata2())).getValue());
                        String value2 = docSchema2.getMetadataByName(classificationPlanModel.getMetadata2()).getValue();
                        secondValues.add(value+"-"+value2);
                        Folder folderLevel2 = new Folder(value+"-"+value2);
                        folderLevel2.addDocument(doc);
                        secondLevel.put(value+"-"+value2, folderLevel2);
                        folderLevel1.addSubfolder(folderLevel2);
                    }
                    // else -> add document
                    else{
                        folderLevel1.addDocument(doc);
                    }
                    firstLevel.put(value, folderLevel1);
                }
                // else -> use the good folder already existing
                else{
                    // if schema2 exist -> new folder of seconde level or add document to folder already existing
                    if(classificationOnTwoLevel){
                        DocSchema docSchema2 = doc.getDocType().getSchemaById(secondSchema);
                        String value2 = docSchema2.getMetadataByName(classificationPlanModel.getMetadata2()).getValue();
                        // The folder for the second level already existing
                        if (secondValues.contains(value+"-"+value2)) {
                            secondLevel.get(value+"-"+value2).addDocument(doc);
                        } else {
                            secondValues.add(value+"-"+value2);
                            Folder folderLevel2 = new Folder(value+"-"+value2);
                            folderLevel2.addDocument(doc);
                            secondLevel.put(value+"-"+value2, folderLevel2);
                            firstLevel.get(value).addSubfolder(folderLevel2);
                        }
                    }
                    // else -> add document to the good one
                    else {
                        firstLevel.get(value).addDocument(doc);
                    }
                }
            }
        }        
        List<Folder> foldersFirstLevel = new LinkedList<Folder>();
        foldersFirstLevel.addAll(firstLevel.values());
        try {
            ClassificationPlan classificationPlan = new ClassificationPlan(classificationPlanModel, foldersFirstLevel);
            logger.info("Creation of the classification plan with identifier " +classificationPlanModel.getId() + " succeed");
            return classificationPlan;
        } catch (Exception e) {
            logger.error("");
            throw new InternalServerError("500 Error during the creation of the classification plan with identifier"+classificationPlanModel.getId());
        }
    }
}