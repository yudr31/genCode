package com.spring.boot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.bean.DataDict;
import com.spring.boot.bean.DictType;
import com.spring.boot.bean.Page;
import com.spring.boot.mapper.DataDictDao;
import com.spring.boot.repository.DataDictRepository;
import com.spring.boot.repository.DictTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 13:55
 */
@Service
public class DataDictService {

    @Autowired
    private DataDictDao dataDictDao;
    @Autowired
    private DataDictRepository dictRepository;
    @Autowired
    private DictTypeRepository dictTypeRepository;

    public PageInfo<DataDict> getDataDictList(Page page, DataDict dataDict){
        PageHelper.startPage(page.getCurrentPage(),page.getNumPerPage());
        List<DataDict> dictList = dataDictDao.findDataDictList(dataDict);
        return new PageInfo(dictList);
    }

    public DataDict getDataDict(Long id){
        return dictRepository.findOne(id);
    }

    public DataDict createDataDict(DataDict dataDict){
        return dictRepository.save(dataDict);
    }

    public PageInfo<DictType> getDictTypeList(Page page, DictType dictType){
        PageHelper.startPage(page.getCurrentPage(),page.getNumPerPage());
        return new PageInfo(dataDictDao.findDictTypeList(dictType));
    }

    public DictType getDictType(Long id){
        return dictTypeRepository.findOne(id);
    }

    public DictType createDictType(DictType dictType){
        return dictTypeRepository.save(dictType);
    }

}
