package com.smile.miniged.service;


import java.io.InputStream;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smile.miniged.data.BinaryFileDAO;
import com.smile.miniged.exception.InternalServerError;
import com.smile.miniged.exception.NotFound;
import com.smile.miniged.model.BinaryFile;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;

@Service
public class BinaryFileService implements InterfaceBinaryFileService {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BinaryFileService.class);

    @Autowired
    BinaryFileDAO binaryFileDAO;

    @Autowired
    DocService docService;
    

    @Value("${minio.host}")
    private String endPoint;

    @Value("${minio.access_key}")
    private String accesKey;

    @Value("${minio.secret_key}")
    private String secretKey;

                
    public void uploadAttachedFile(String documentId, MultipartFile file){
        logger.info("start start");
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endPoint)
                .credentials(accesKey, secretKey)
                .build();
        // verif start of the method of the uploadAttachedFile service
        logger.info("start of the method of the uploadAttachedFile service");
        try {
            if(docService.find(documentId)==null){
                logger.warn("The document with the identifer "+ documentId + " does not exist");
                throw new NotFound("404 The document with the identifer "+ documentId + " does not exist");
            }
            // verif verification of the existence of the document succed
            logger.info("verification of the existence of the document succed");
            boolean bucketFound = minioClient.bucketExists(BucketExistsArgs.builder().bucket("miniged").build());
            // verif bucket found or not
            logger.info("bucket found or not: "+ bucketFound);
            logger.info("Recovery client minio success");
            if(bucketFound){
                logger.info("Bucket \"miniged\" is available");
            }
            else{
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("miniged").build());  
                logger.info("Creation bucket \"miniged\" succed ");
            }
            logger.info("file.getName()"+ file.getName());
            logger.info("getContentType()" +  file.getContentType());
            logger.info("getOriginalFilename()" +  file.getOriginalFilename());
            minioClient.putObject(
                PutObjectArgs.builder().bucket("miniged").object(documentId).stream(
                        file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
            logger.info("upload minio method succeed");
            binaryFileDAO.save(new BinaryFile(documentId));
            logger.info("Uploading of the binary file succed");
            logger.info("####### Successful file upload ");
        } catch (Exception e) {
            logger.error("####### Error during file upload ", e);
            throw new InternalServerError("####### Error during binary file download or the binary file is already download");
        }
    }

    public ResponseEntity<Resource> downloadAttachedFile(String documentId){
        try {
            logger.info("start start");
            
            if(docService.find(documentId)==null){
                logger.warn("The document with the identifer "+ documentId + " does not exist");
                throw new NotFound("404 The document with the identifer "+ documentId + " does not exist");
            }
            if(binaryFileDAO.findBinaryFileByName(documentId)==null){
                logger.warn("No binary file has been attached to the document with the identifier "+ documentId);
                throw new NotFound("404 No binary file has been attached to the document with the identifier "+ documentId);
            }
            MinioClient minioClient = MinioClient.builder()
                .endpoint(endPoint)
                .credentials(accesKey, secretKey)
                .build();
            InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                .bucket("miniged")
                .object(documentId)
                .build());
            logger.info("stream ok");
            
            InputStreamResource inputStreamResource = new InputStreamResource(stream);
            logger.info("inputStreamResource ok");

            StatObjectResponse objectStat =
                minioClient.statObject(
                StatObjectArgs.builder().bucket("miniged").object(documentId).build());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentId+"\"").contentLength(objectStat.size()).contentType(MediaType.parseMediaType(objectStat.contentType())).body(inputStreamResource);

        } catch (Exception e) {
            logger.error("####### Error during binary file download or the binary file is already download ", e);
            throw new InternalServerError("####### Error during binary file download or the binary file is already download");
        }
        finally{
            logger.info("####### Successful file download ");
        }
    }
}
