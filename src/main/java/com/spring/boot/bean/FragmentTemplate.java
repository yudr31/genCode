package com.spring.boot.bean;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author yuderen
 * @version 2017/10/26 16:34
 */
@Entity(name = "ecsys_fragment_template")
@EntityListeners(AuditingEntityListener.class)
public class FragmentTemplate extends BaseBean{

    @Id
    @GeneratedValue
    private Long id;
    private String modelName;           //碎片模板名称
    private Integer modelType;          //碎片模板类型
    private String modelDesc;           //碎片模板描述
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

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getModelContent() {
        return modelContent;
    }

    public void setModelContent(String modelContent) {
        this.modelContent = modelContent;
    }
}
