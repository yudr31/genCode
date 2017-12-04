package com.spring.boot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.bean.FragmentTemplate;
import com.spring.boot.bean.Page;
import com.spring.boot.mapper.FragmentTemplateDao;
import com.spring.boot.repository.FragTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-19 22:08
 */
@Service
public class FragmentTemplateService {

    @Autowired
    private FragmentTemplateDao fragmentTemplateDao;
    @Autowired
    private FragTemplateRepository fragTemplateRepository;

    public PageInfo<FragmentTemplate> getFragmentTemplateList(Page page, FragmentTemplate fragmentTemplate){
        PageHelper.startPage(page.getCurrentPage(),page.getNumPerPage());
        List<FragmentTemplate> resultList = fragmentTemplateDao.findFragmentTemplateList(fragmentTemplate);
        return new PageInfo(resultList);
    }

    public FragmentTemplate getFragmentTemplate(Long id){
        return fragTemplateRepository.findOne(id);
    }

    public FragmentTemplate createFragmentTemplate(FragmentTemplate fragmentTemplate){
        return fragTemplateRepository.save(fragmentTemplate);
    }

    public List<FragmentTemplate> getFragmentTemplateList(){
        return (List<FragmentTemplate>) fragTemplateRepository.findAll();
    }

}
