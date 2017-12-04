package com.spring.boot.bean;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Created by yuderen on 2017-8-28.
 */
@Entity(name = "ecsys_data_dict")
@EntityListeners(AuditingEntityListener.class)
public class DataDict extends BaseBean{

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 64)
    private String value;	          // 数据值
    @Column(length = 64)
    private String label;	          // 标签名
    private String description;     // 描述
    private Long parent;            // 父Id
    private String type;	          // 类型
    private Integer sort;           // 排序

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
