package com.smile.miniged.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smile.miniged.model.ClassificationPlan;
import com.smile.miniged.model.ClassificationPlanModel;
import com.smile.miniged.service.ClassificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin("*")
@RestController
@Api
public class ClassificationRestController {


    private final ClassificationService classificationService;

    public ClassificationRestController(final ClassificationService classificationService){
        this.classificationService = classificationService;
    }
    
    // ################################
    // ########/classification#########
    // ################################
    @PostMapping("/classification")
    @ApiOperation(value="Create a classification plan model", tags = "Classification") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the creation of the classification plan"),
        @ApiResponse(code = 404, message ="Ressource not found during the creation of the classification plan"),
        @ApiResponse(code = 409, message ="Conflict happen during the creation of the classification plan"),
        @ApiResponse(code = 200, message ="Successfull creation of the classification plan"),
    })
    public void createClassification(@RequestBody ClassificationPlanModel classificationPlanModel){
        classificationService.create(classificationPlanModel);
    }

    @GetMapping("/classification")
    @ApiOperation(value="Recover all classification plan model", tags = "Classification") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the recovery of all classifications plans"),
        @ApiResponse(code = 200, message ="Successfull recovering of all classifications plans"),
    })
    public List<ClassificationPlanModel> findAllClassification(){
        return classificationService.findAll();
    }

    @GetMapping("/classification/{id}")
    @ApiOperation(value="Recover the classification plan model with the specific id", tags = "Classification") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the recovery of the specific classification plan"),
        @ApiResponse(code = 404, message ="Ressource not found during the recovery of the specific classification plan"),
        @ApiResponse(code = 200, message ="Successfull recovering of the specific classification plan"),
    })
    public ClassificationPlanModel findClassification(@PathVariable(value="id") String classificationId ){
        return classificationService.find(classificationId);
    }

    // ################################
    // ###########/classify############
    // ################################
    @GetMapping("/classify/{id}")
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the construction of the classification plan"),
        @ApiResponse(code = 404, message ="Ressource not found during the construction of the classification plan"),
        @ApiResponse(code = 200, message ="Successfull construction of the classification plan"),
    })
    @ApiOperation(value="Build the classification plan model from all documents", tags = "Classification") 
    public ClassificationPlan constructClassificationPlan(@PathVariable(value="id") String classificationId ){
        return classificationService.construct(classificationId);
    }
}
