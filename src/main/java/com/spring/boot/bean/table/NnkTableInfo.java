package com.spring.boot.bean.table;

/**
 * @author yuderen
 * @version 2017/10/31 15:27
 */
public class NnkTableInfo extends TableInfo {

    private String moduleName;			// 表模块名：orderMonitor
    private String entityName;			// 实体类名
    private String protoEntity;			// 协议实体名
    private String infoName;			// 实体信息简称

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getProtoEntity() {
        return protoEntity;
    }

    public void setProtoEntity(String protoEntity) {
        this.protoEntity = protoEntity;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }
}
