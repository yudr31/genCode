package com.spring.boot.mapper;

import com.spring.boot.bean.DataDict;
import com.spring.boot.bean.DictType;

import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 13:59
 */
public interface DataDictDao {

    List<DataDict> findDataDictList(DataDict dataDict);

    List<DictType> findDictTypeList(DictType dictType);

}
