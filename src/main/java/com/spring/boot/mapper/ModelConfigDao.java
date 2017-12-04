package com.spring.boot.mapper;

import com.spring.boot.bean.ModelConfig;

import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 13:38
 */
public interface ModelConfigDao {

    List<ModelConfig> findModelConfigList(ModelConfig modelConfig);

}
