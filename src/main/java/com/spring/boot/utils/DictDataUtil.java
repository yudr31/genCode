package com.spring.boot.utils;

import com.spring.boot.bean.DataDict;
import com.spring.boot.repository.DataDictRepository;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 19:59
 */
public class DictDataUtil implements TemplateMethodModelEx {

    @Autowired
    private DataDictRepository dataDictRepository;

    @Override
    public Object exec(List list) throws TemplateModelException {
        dataDictRepository = SpringUtil.getBean(DataDictRepository.class);
        if (list.size() < 3) return "";
        String method = String.valueOf(list.get(0));    //第一个参数为方法名
        String dictType = String.valueOf(list.get(1));  //第二个参数为字典类型
        String value = String.valueOf(list.get(2));     //第三个参数为当前值
        List<DataDict> dictList = dataDictRepository.findByType(dictType);
        StringBuffer stringBuffer = new StringBuffer();
        switch (method){
            case "getDictLabelByValue":
                return getDictLabelByValue(dictList,value);
            case "getOptions":
                return getOptions(stringBuffer,dictList,value);
            case "getOptionsWithNull":
                return getOptionsWithNull(stringBuffer,dictList,value);
            default:
                return stringBuffer.toString();
        }
    }

    public String getDictLabelByValue(List<DataDict> dictList,String value){
        if (null != value && !"".equals(value)){
            for (DataDict dataDict : dictList){
                if (value.equals(dataDict.getValue())){
                    return dataDict.getLabel();
                }
            }
        }
        return "";
    }

    public String getOptions(StringBuffer stringBuffer,List<DataDict> dictList,String value){
        if (null != value && !"".equals(value)){
            for (DataDict dataDict : dictList){
                String selected = value.equals(dataDict.getValue()) ? "selected" : "";
                stringBuffer.append("<option value='"+dataDict.getValue()+"' "+selected+">"+dataDict.getLabel()+"</option>");
            }
        }
        return stringBuffer.toString();
    }

    public String getOptionsWithNull(StringBuffer stringBuffer,List<DataDict> dictList,String value){
        stringBuffer.append("<option value=''>所有</option>");
        getOptions(stringBuffer,dictList,value);
        return stringBuffer.toString();
    }

}
