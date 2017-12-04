package com.spring.boot.pattern;

import com.spring.boot.bean.ModelConfig;
import com.spring.boot.repository.ModelConfigRepository;
import com.spring.boot.utils.FreeMarkerUtil;
import com.spring.boot.utils.SpringUtil;
import com.spring.boot.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 使用模板方法设计模式执行生成代码文件
 * @author: yuderen
 * @version: 1.0 2017-11-17 20:36
 */
public class GenerateFile {

    @Autowired
    private ModelConfigRepository modelConfigRepository;

    public void getTemplateList(){
        this.modelConfigRepository = SpringUtil.getBean(ModelConfigRepository.class);
    }

    public void generateFile(List<String> modelTypeList, Object object, String dirName){
        for (String modelTypeStr : modelTypeList){
            Integer modelType = Integer.parseInt(modelTypeStr);
            List<ModelConfig> modelList = modelConfigRepository.findByModelType(modelType);
            for (ModelConfig model : modelList){
                String result = FreeMarkerUtil.getResult(object,model);
                String savePath = StringUtil.addFileSeparator(dirName,model.getSavePath()) + model.getFileName();
                savePath = FreeMarkerUtil.getContext(object,model.getModelName(),savePath);
                if (StringUtils.isNotBlank(result)){
                    FreeMarkerUtil.generateFile(savePath,result);
                }
            }
        }
    }

    public final void process(List<String> modelTypeList, Object object, String dirName){

        this.getTemplateList();     // 获取碎片模板列表

        this.generateFile(modelTypeList, object, dirName);  // 生成代码文件信息
    }

}
