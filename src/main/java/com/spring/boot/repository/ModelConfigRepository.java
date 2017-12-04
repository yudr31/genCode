package com.spring.boot.repository;

import com.spring.boot.bean.ModelConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 13:35
 */
public interface ModelConfigRepository extends CrudRepository<ModelConfig,Long> {

    List<ModelConfig> findByModelType(Integer modelType);

}
