package com.spring.boot.service;

import com.spring.boot.bean.table.ColumnInfo;
import com.spring.boot.bean.MasterSlaveModel;
import com.spring.boot.bean.ModelConfig;
import com.spring.boot.bean.table.SingleTableInfo;
import com.spring.boot.bean.table.SlaveTableInfo;
import com.spring.boot.bean.table.TableInfo;
import com.spring.boot.pattern.GenerateFile;
import com.spring.boot.repository.ModelConfigRepository;
import com.spring.boot.utils.DataBaseUtil;
import com.spring.boot.utils.FreeMarkerUtil;
import com.spring.boot.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-20 22:19
 */
@Service
public class GenCodeService {

    @Autowired
    private ModelConfigRepository modelConfigRepository;

    public void querySingleTable(SingleTableInfo tableInfo){
        Integer modelType = Integer.parseInt(tableInfo.getModuleType().get(0));
        List<ModelConfig> modelList = modelConfigRepository.findByModelType(modelType);
        tableInfo.setModelList(modelList);
        DataBaseUtil.getTableInfo(tableInfo);
    }

    public void queryMasterSlaveTable(MasterSlaveModel masterSlaveModel){
        Integer modelType = Integer.parseInt(masterSlaveModel.getMaster().getModuleType().get(0));
        List<ModelConfig> modelList = modelConfigRepository.findByModelType(modelType);
        masterSlaveModel.getMaster().setModelList(modelList);
        DataBaseUtil.getTableInfo(masterSlaveModel.getMaster());
        for (SlaveTableInfo tableInfo : masterSlaveModel.getSlave()){
            DataBaseUtil.getTableInfo(tableInfo);
        }
    }

    public boolean generateSingleTable(SingleTableInfo tableInfo){
        initEnumerOrAmount(tableInfo);
        new GenerateFile().process(tableInfo.getModuleType(),tableInfo,tableInfo.getEntityName());
        return true;
    }

    public Map previewSingleTable(SingleTableInfo tableInfo){
        Map result = new HashMap();
        initEnumerOrAmount(tableInfo);
        ModelConfig model = modelConfigRepository.findOne(tableInfo.getPreview());
        model.setSavePath(FreeMarkerUtil.getContext(tableInfo,model.getModelName(),model.getSavePath()));
        model.setFileName(FreeMarkerUtil.getContext(tableInfo,model.getModelName(),model.getFileName()));
        result.put("content",FreeMarkerUtil.getResult(tableInfo,model));
        result.put("model",model);
        return result;
    }

    public boolean generateMasterSlaveTable(MasterSlaveModel masterSlaveModel){
        initEnumerOrAmount(masterSlaveModel.getMaster());
        for (SlaveTableInfo tableInfo : masterSlaveModel.getSlave()){
            initEnumerOrAmount(tableInfo);
        }
        new GenerateFile().process(masterSlaveModel.getMaster().getModuleType(),masterSlaveModel,masterSlaveModel.getMaster().getEntityName());
      return true;
    }

    public Map previewMasterSlaveTable(MasterSlaveModel masterSlaveModel){
        Map result = new HashMap();
        initEnumerOrAmount(masterSlaveModel.getMaster());
        for (SlaveTableInfo tableInfo : masterSlaveModel.getSlave()){
            initEnumerOrAmount(tableInfo);
        }
        ModelConfig model = modelConfigRepository.findOne(masterSlaveModel.getMaster().getPreview());
        model.setSavePath(FreeMarkerUtil.getContext(masterSlaveModel,model.getModelName(),model.getSavePath()));
        model.setFileName(FreeMarkerUtil.getContext(masterSlaveModel,model.getModelName(),model.getFileName()));
        result.put("content",FreeMarkerUtil.getResult(masterSlaveModel,model));
        result.put("model",model);
        return result;
    }

    public void initEnumerOrAmount(TableInfo tableInfo){
        for (ColumnInfo columnInfo : tableInfo.getColumns()){
            if ("select".equals(columnInfo.getTag())){
                columnInfo.setEnumBool(true);
            } else if ("amount".equals(columnInfo.getTag())){
                columnInfo.setAmountBool(true);
            } else if ("dict".equals(columnInfo.getTag())){
                columnInfo.setDictBool(true);
            }
        }
    }

}
