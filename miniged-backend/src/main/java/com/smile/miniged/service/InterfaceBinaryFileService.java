package com.smile.miniged.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public interface InterfaceBinaryFileService {
    ResponseEntity<Resource> downloadAttachedFile(String documentId);
    void uploadAttachedFile(String documentId, MultipartFile file);
}
