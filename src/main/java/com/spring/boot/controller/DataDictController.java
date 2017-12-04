package com.spring.boot.controller;

import com.github.pagehelper.PageInfo;
import com.spring.boot.bean.DataDict;
import com.spring.boot.bean.DictType;
import com.spring.boot.service.DataDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 14:21
 */
@Controller
@RequestMapping("/dataDict")
public class DataDictController extends BaseController {

    @Autowired
    private DataDictService dataDictService;

    @RequestMapping("/dataDictList")
    public String dataDictList(HttpServletRequest request, DataDict dataDict, ModelMap modelMap){
        return getDataDictList(request,dataDict,modelMap);
    }

    private String getDataDictList(HttpServletRequest request, DataDict dataDict, ModelMap modelMap){
        PageInfo<DataDict> pageInfo = dataDictService.getDataDictList(getPage(request),dataDict);
        modelMap.addAttribute("page",pageInfo);
        modelMap.addAttribute("param",dataDict);
        return "dataDict/dataDictList";
    }

    @RequestMapping("/dataDictListForCheck")
    public String dictTypeListForCheck(HttpServletRequest request, DataDict dataDict,
                                       String lookUpType, ModelMap modelMap){
        getDataDictList(request,dataDict,modelMap);
        modelMap.addAttribute("lookUpType", lookUpType);
        return "dataDict/dataDictForCheck";
    }

    @RequestMapping("/dataDictCreator")
    public String dataDictCreator(ModelMap modelMap){
        setPageModeForCreator(modelMap);
        getDataDictViewer(null,modelMap);
        return "dataDict/dataDictViewForCreator";
    }

    @RequestMapping("/dataDictCreator.do")
    public ModelAndView dataDictCreator(DataDict dataDict){
        DataDict result = dataDictService.createDataDict(dataDict);
        if (null != result)
            return getSuccessResponseWithNewId(dataDict.getId());
        return getErrorResponse("添加数据字典失败");
    }

    @RequestMapping("/dataDictEditor")
    public String dataDictEditor(Long id, ModelMap modelMap){
        setPageModeForEditor(modelMap);
        getDataDictViewer(id,modelMap);
        return "dataDict/dataDictViewForEditor";
    }

    @RequestMapping("/dataDictEditor.do")
    public ModelAndView dataDictEditor(DataDict dataDict){
        DataDict result = dataDictService.createDataDict(dataDict);
        if (null != result)
            return getSuccessResponse();
        return getErrorResponse("修改数据字典失败");
    }

    @RequestMapping("/dataDictViewer")
    public String dataDictViewer(Long id, ModelMap modelMap){
        setPageModeForViewer(modelMap);
        return getDataDictViewer(id,modelMap);
    }

    public String getDataDictViewer(Long id,ModelMap modelMap){
        DataDict dataDict = null != id ? dataDictService.getDataDict(id) : new DataDict();
        modelMap.addAttribute("data",dataDict);
        return "dataDict/dataDictViewForViewer";
    }

    @RequestMapping("/dictTypeList")
    public String dictTypeList(HttpServletRequest request, DictType dictType, ModelMap modelMap){
        return getDictTypeList(request,dictType,modelMap);
    }

    public String getDictTypeList(HttpServletRequest request, DictType dictType, ModelMap modelMap){
        PageInfo<DictType> pageInfo = dataDictService.getDictTypeList(getPage(request),dictType);
        modelMap.addAttribute("page",pageInfo);
        modelMap.addAttribute("param",dictType);
        return "dictType/dictTypeList";
    }

    @RequestMapping("/dictTypeListForCheck")
    public String dictTypeListForCheck(HttpServletRequest request, DictType dictType,
                                       String lookUpType, ModelMap modelMap){
        getDictTypeList(request,dictType,modelMap);
        modelMap.addAttribute("lookUpType", lookUpType);
        return "dictType/dictTypeForCheck";
    }

    @RequestMapping("/dictTypeCreator")
    public String dictTypeCreator(ModelMap modelMap){
        setPageModeForCreator(modelMap);
        getDictTypeViewer(null,modelMap);
        return "dictType/dictTypeViewForCreator";
    }

    @RequestMapping("/dictTypeCreator.do")
    public ModelAndView dictTypeCreator(DictType dictType){
        DictType result = dataDictService.createDictType(dictType);
        if (null != result)
            return getSuccessResponseWithNewId(result.getId());
        return getErrorResponse("添加数据字典失败");
    }

    @RequestMapping("/dictTypeEditor")
    public String dictTypeEditor(Long id, ModelMap modelMap){
        setPageModeForEditor(modelMap);
        getDictTypeViewer(id,modelMap);
        return "dictType/dictTypeViewForEditor";
    }

    @RequestMapping("/dictTypeEditor.do")
    public ModelAndView dictTypeEditor(DictType dictType){
        DictType result = dataDictService.createDictType(dictType);
        if (null != result)
            return getSuccessResponse();
        return getErrorResponse("修改数据字典失败");
    }

    @RequestMapping("/dictTypeViewer")
    public String dictTypeViewer(Long id, ModelMap modelMap){
        setPageModeForViewer(modelMap);
        return getDictTypeViewer(id,modelMap);
    }

    public String getDictTypeViewer(Long id,ModelMap modelMap){
        DictType dictType = null != id ? dataDictService.getDictType(id) : new DictType();
        modelMap.addAttribute("data",dictType);
        return "dictType/dictTypeViewForViewer";
    }

}
