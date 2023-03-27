package com.smile.miniged.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smile.miniged.model.Schema;
import com.smile.miniged.service.SchemaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin("*")
@RestController
@Api
public class SchemaRestController {

    @Autowired 
    SchemaService schemaService;


    // ################################
    // ###########/schemas#############
    // ################################
    @PostMapping("/schemas")
    @ApiOperation(value = "Create a schema", tags = "Schema")
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the creation of the schema"),
        @ApiResponse(code = 409, message ="Conflict happen during the creation of the schema"),
        @ApiResponse(code = 200, message ="Successfull the creation of the schema"),
    })
    public void saveSchema(@RequestBody Schema schema){
        schemaService.save(schema);
    }

    // GET sur /schemas/{id} pour récupérer le schema avec le bon identifiant : rien si aucun identifiant
    @GetMapping("/schemas/{id}")
    @ApiOperation(value = "Recover a schema with the specific id", tags = "Schema")
    @ApiResponses(value = {
        @ApiResponse(code = 404, message ="Ressource not found during the recovery of the specific schema"),
        @ApiResponse(code = 200, message ="Successfull the recovery of the specific schema"),
    })
    public Schema findSchema(@PathVariable(value="id") String schemaId){
        return schemaService.find(schemaId);
    }

    // GET sur /schemas pour récupérer tout les schemas 
    @ApiOperation(value = "Recover all schemas", tags = "Schema")
    @GetMapping("/schemas")
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the recovery of all schemas"),
        @ApiResponse(code = 200, message ="Successfull recovery of all schemas"),
    })
    public List<Schema> findAllSchemas(){
        return schemaService.findAll();
    }

    
}