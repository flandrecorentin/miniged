package com.smile.miniged.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smile.miniged.model.IDType;
import com.smile.miniged.model.Type;
import com.smile.miniged.service.TypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin("*")
@RestController
@Api
public class TypeRestController {

    @Autowired
    TypeService typeService;

    // ################################
    // ############/type###############
    // ################################
    // POST sur /type pour créer un nouveau type de document. Il faut que les schémas que le type utilise existent déjà et ceux là seront mis à jour avec les modèles existants
    @PostMapping("/type")
    @ApiOperation(value="Create a type" , tags = "Type") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the creation of the type"),
        @ApiResponse(code = 409, message ="Conflict happen during the creation of the type"),
        @ApiResponse(code = 200, message ="Successfull the creation of the type"),
    })
    public void saveType(@RequestBody IDType idType){
        typeService.save(idType);
    }
    // GET sur /type/{id} pour récupérer le schema avec le bon identifiant : rien si aucun identifiant
    @GetMapping("/type/{id}")
    @ApiOperation(value="Recover a type with the specific id" , tags = "Type") 
    @ApiResponses(value = {
        @ApiResponse(code = 404, message ="Ressource not found during the recovery of the specific type"),
        @ApiResponse(code = 200, message ="Successfull the recovery of the specific type"),
    })
    public Type findType(@PathVariable(value="id") String typeName){
        return typeService.find(typeName);
    }


    // GET sur /type pour récupérer tout les types de documents existants
    @GetMapping("/type")
    @ApiOperation(value="Recover all type", tags = "Type") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the recovery of all types"),
        @ApiResponse(code = 200, message ="Successfull recovery of all types"),
    })
    public List<Type> findAllTypes(){
        return typeService.findAll();
       
    } 
}
