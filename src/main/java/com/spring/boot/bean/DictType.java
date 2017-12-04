package com.spring.boot.bean;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 14:03
 */
@Entity(name = "ecsys_dict_type")
@EntityListeners(AuditingEntityListener.class)
public class DictType extends BaseBean{

    @Id
    @GeneratedValue
    private Long id;                //id
    @Column(length = 64)
    private String label;           // 标签类型名
    @Column(length = 64)
    private String value;           // 标签类型值
    private String description;     // 描述
    @Column(length = 32)
    private String scope;           // 适用范围

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
