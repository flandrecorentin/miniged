package com.smile.miniged.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Id;

public class Folder {
    @Id
    private String id;
    private List<Folder> subfolders;
    private List<Doc> documents;

    public Folder() {
        this.subfolders = new LinkedList<Folder>();
        this.documents = new LinkedList<Doc>();
    }
    
    public Folder(String id) {
        this.id = id;
        this.subfolders = new LinkedList<Folder>();
        this.documents = new LinkedList<Doc>();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Folder> getSubfolders() {
        return subfolders;
    }
    public void setSubfolders(List<Folder> subfolders) {
        this.subfolders = subfolders;
    }
    public List<Doc> getDocuments() {
        return documents;
    }
    public void setDocuments(List<Doc> documents) {
        this.documents = documents;
    }
    
    public void addSubfolder(Folder folder){
        this.subfolders.add(folder);
    }

    public void addDocument(Doc doc){
        this.documents.add(doc);
    }
    public static Folder findFolderById(List<Folder> listFolders, String id){
        Folder folderToReturn = null;
        for (Folder folder : listFolders) {
            if(folder.getId().equals(id)){
                return folderToReturn;
            }
        }
        return folderToReturn;
    }

}
