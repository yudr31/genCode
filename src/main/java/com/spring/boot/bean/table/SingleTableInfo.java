package com.spring.boot.bean.table;

import com.spring.boot.bean.ModelConfig;
import com.spring.boot.bean.table.TableInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuderen
 * @version 2017/10/26 9:09
 */
public class SingleTableInfo extends NnkTableInfo {

    private String sqlFile;				// sql文件名
    private String protoFile;			// protobuf协议文件名
    private String modulePkg;			// 模块包名

    private Long preview;				// 预览的文件
    private boolean batchAdd;           // 是否生成批量添加代码
    private List<String> moduleType = new ArrayList<>();
    private List<ModelConfig> modelList = new ArrayList();		//模板列表

    public String getSqlFile() {
        return sqlFile;
    }

    public void setSqlFile(String sqlFile) {
        this.sqlFile = sqlFile;
    }

    public String getProtoFile() {
        return protoFile;
    }

    public void setProtoFile(String protoFile) {
        this.protoFile = protoFile;
    }

    public String getModulePkg() {
        return modulePkg;
    }

    public void setModulePkg(String modulePkg) {
        this.modulePkg = modulePkg;
    }

    public Long getPreview() {
        return preview;
    }

    public void setPreview(Long preview) {
        this.preview = preview;
    }

    public boolean isBatchAdd() {
        return batchAdd;
    }

    public void setBatchAdd(boolean batchAdd) {
        this.batchAdd = batchAdd;
    }

    public List<String> getModuleType() {
        return moduleType;
    }

    public void setModuleType(List<String> moduleType) {
        this.moduleType = moduleType;
    }

    public List<ModelConfig> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelConfig> modelList) {
        this.modelList = modelList;
    }
}
