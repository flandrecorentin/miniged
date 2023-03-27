package com.smile.miniged;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.smile.miniged.service.DocService;

@Component
public class InitializationService implements ApplicationRunner {

    private static String basicType = "File";
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MinigedApplication.class);
    @Autowired
    DocService docService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("|| Beggining initialization service");
        docService.initializationDublincoreImageFilePhotography();
        if(docService.numberOfDoc()==0){
            try {
                docService.createDocument(basicType);
                log.info("Creation automatic of the first document with type File succeed");
            } catch (Exception e) {
                log.error("Error during creation automatic of the first document with type File");
            }
        }
        log.info("|| End initialization service");
    }

}
