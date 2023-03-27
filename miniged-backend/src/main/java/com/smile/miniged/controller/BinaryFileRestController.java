package com.smile.miniged.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smile.miniged.service.BinaryFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@CrossOrigin("*")
@RestController
public class BinaryFileRestController {
    @Autowired
    BinaryFileService binaryFileService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BinaryFileRestController.class);

    // ################################
    // #########/attachment############
    // ################################
    
    // POST sur /attachment/{id} pour uploader la pièce jointe dans un document - erreur 404 si document non créé
    @PostMapping("/attachment/{id}")
    @ApiOperation(value="Upload an attached file", tags = "Attachment") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the uploading of the attached file"),
        @ApiResponse(code = 404, message ="Ressource not found during the uploading of the attached file"),
        @ApiResponse(code = 200, message ="Successfull uploading of the attached file")
    })
    public void uploadAttachedFile(@PathVariable(value="id") String documentId, @RequestParam("file") MultipartFile file){
        logger.debug("request PostMapping on /attachment/{id} receive");
        binaryFileService.uploadAttachedFile(documentId, file);
    }


    // GET sur /attachment/{id} pour récupérer la pièce jointe
    @GetMapping("/attachment/{id}")
    @ApiOperation(value="Download an attached file" , tags = "Attachment") 
    @ApiResponses(value = {
        @ApiResponse(code = 500, message ="Internal error server during the downloading of the attached file"),
        @ApiResponse(code = 404, message ="Ressource not found during the downloading of the attached file"),
        @ApiResponse(code = 200, message ="Successfull downloading of the attached file")
    })
    @ResponseBody
    public ResponseEntity<Resource> downloadAttachedFile(@PathVariable(value="id") String documentId){
        return binaryFileService.downloadAttachedFile(documentId);
    }
}
