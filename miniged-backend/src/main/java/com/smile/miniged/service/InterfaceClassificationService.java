package com.smile.miniged.service;

import java.util.List;

import com.smile.miniged.model.ClassificationPlan;
import com.smile.miniged.model.ClassificationPlanModel;

public interface InterfaceClassificationService {
    void create(ClassificationPlanModel classificationPlanModel);
    List<ClassificationPlanModel> findAll();
    ClassificationPlanModel find(String classificationId);
    boolean exist(String classificationId);
    ClassificationPlan construct(String classificationId);
    
}
