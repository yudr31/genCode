package com.spring.boot.bean;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-16 23:15
 */
@Entity(name = "ecsys_model_config")
@EntityListeners(AuditingEntityListener.class)
public class ModelConfig extends BaseBean {

    @Id
    @GeneratedValue
    private Long id;
    private String modelName;
    private Integer modelType;
    private String savePath;
    private String fileName;
    @Lob
    private String modelContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getModelType() {
        return modelType;
    }

    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getModelContent() {
        return modelContent;
    }

    public void setModelContent(String modelContent) {
        this.modelContent = modelContent;
    }

}
