package com.smile.miniged.model;

import java.util.List;

public class ClassificationPlan {
    
    private ClassificationPlanModel classificationPlanModel;
    private List<Folder> folders;

    public ClassificationPlan() {
    }

    public ClassificationPlan(ClassificationPlanModel classificationPlanModel, List<Folder> folders) {
        this.classificationPlanModel = classificationPlanModel;
        this.folders = folders;
    }

    public ClassificationPlanModel getClassificationPlanModel() {
        return classificationPlanModel;
    }
    public void setClassificationPlanModel(ClassificationPlanModel classificationPlanModel) {
        this.classificationPlanModel = classificationPlanModel;
    }
    public List<Folder> getFolders() {
        return folders;
    }
    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
}
