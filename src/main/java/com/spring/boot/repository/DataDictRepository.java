package com.spring.boot.repository;

import com.spring.boot.bean.DataDict;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 13:59
 */
public interface DataDictRepository extends CrudRepository<DataDict, Long>{

    List<DataDict> findByType(String type);

}
