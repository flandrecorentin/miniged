package com.smile.miniged.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smile.miniged.model.Doc;
import com.smile.miniged.model.IDDoc;
import com.smile.miniged.service.DocService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin("*")
@RestController
@Api
public class DocumentRestController {

    @Autowired
    DocService docService;

    // ################################
    // ##########/document#############
    // ################################

    // POST sur /document pour créer un document avec son type et ses schémas 
    @PostMapping("/document")
    @ApiOperation(value="Create a document" , tags = "Document") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the creation of the document"),
        @ApiResponse(code = 409, message ="Conflict happen during the creation of the document"),
        @ApiResponse(code = 200, message ="Successfull creation of the document"),
    })
    public void saveDocument(@RequestBody IDDoc type){
        docService.createDocument(type.getIdType());
    }


    //  PUT sur /document/ pour modifier les métadonnées du document -> on ne peut pas changer le Type du document
    @PutMapping("/document/{id}")
    @ApiOperation(value="Modify a document" , tags = "Document") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the modification of the document"),
        @ApiResponse(code = 409, message ="Conflict happen during the modification of the document"),
        @ApiResponse(code = 404, message ="Ressource not found happen during the modification of the document"),
        @ApiResponse(code = 200, message ="Successfull modification of the document"),
    })
    public void modifyDocument(@PathVariable(value="id") String documentId, @RequestBody Doc doc ){
        docService.modify(documentId, doc);
    }

    
    //  GET sur /document/{id} pour récupérer le document : rien si identifiant non existant
    @GetMapping("/document/{id}")
    @ApiOperation(value="Recover a document with the specific id" , tags = "Document") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the recovery of the specific document"),
        @ApiResponse(code = 404, message ="Ressource not found happen during the recovery of the specific document"),
        @ApiResponse(code = 200, message ="Successfull recovery of the specific document"),
    })
    public Doc findDocument(@PathVariable(value="id") String documentId){
        return docService.find(documentId);
    }

    
    // GET sur /document pour l'affichage de tout les docs
    @ApiOperation(value = "Recover all documents", tags = "Document")
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the recovery of all documents"),
        @ApiResponse(code = 404, message ="Ressource not found happen during the recovery of all documents"),
        @ApiResponse(code = 200, message ="Successfull recovery of all documents"),
    })
    @GetMapping("/document")
    public List<Doc> findAllDocuments(){
        return docService.findAll();
    } 

}

