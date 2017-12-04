package com.spring.boot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.bean.ModelConfig;
import com.spring.boot.bean.Page;
import com.spring.boot.mapper.ModelConfigDao;
import com.spring.boot.repository.ModelConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-19 22:08
 */
@Service
public class ModelConfigService {

    @Autowired
    private ModelConfigDao modelConfigDao;
    @Autowired
    private ModelConfigRepository modelConfigRepository;

    public PageInfo<ModelConfig> getModelConfigList(Page page, ModelConfig modelConfig){
        PageHelper.startPage(page.getCurrentPage(),page.getNumPerPage());
        List<ModelConfig> dictList = modelConfigDao.findModelConfigList(modelConfig);
        return new PageInfo(dictList);
    }

    public ModelConfig getModelConfig(Long id){
        return modelConfigRepository.findOne(id);
    }

    public ModelConfig createModelConfig(ModelConfig modelConfig){
        return modelConfigRepository.save(modelConfig);
    }

}
