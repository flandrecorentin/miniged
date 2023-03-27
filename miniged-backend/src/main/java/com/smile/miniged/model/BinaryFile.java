package com.smile.miniged.model;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;


@Document("binaryfile")

public class BinaryFile {

    @Transient
    private MultipartFile file;
    @Id
    private String name;
    // private String format;

    public BinaryFile() {
    }   

    

    public BinaryFile(MultipartFile file) {
        this.file = file;
    }

    

    public BinaryFile(String name) {
        this.name = name;
    }



    public BinaryFile(MultipartFile file, String name) {
        this.file = file;
        this.name = name;
    }



    // public BinaryFile(String name, String format){
    //     this.name = name;
    //     this.format = format;
    // }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile path) {
        this.file = path;
    }

    // public String getFormat() {
    //     return format;
    // }

    // public void setFormat(String format) {
    //     this.format = format;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
