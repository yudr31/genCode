package com.spring.boot.controller;


import com.github.pagehelper.PageInfo;
import com.spring.boot.bean.ModelConfig;
import com.spring.boot.service.ModelConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-16 23:14
 */
@Controller
@RequestMapping("/modelConfig")
public class ModelConfigController extends BaseController {

    @Autowired
    private ModelConfigService modelConfigService;

    @RequestMapping("/modelConfigList")
    public String modelConfigList(HttpServletRequest request, ModelConfig modelConfig, ModelMap modelMap){
        return getModelConfigList(request,modelConfig,modelMap);
    }

    private String getModelConfigList(HttpServletRequest request, ModelConfig modelConfig, ModelMap modelMap){
        PageInfo<ModelConfig> pageInfo = modelConfigService.getModelConfigList(getPage(request),modelConfig);
        modelMap.addAttribute("page",pageInfo);
        modelMap.addAttribute("param",modelConfig);
        return "modelConfig/modelConfigList";
    }

    @RequestMapping("/modelConfigForCheck")
    public String modelConfigListForCheck(HttpServletRequest request, ModelConfig modelConfig,
                                       String lookUpType, ModelMap modelMap){
        getModelConfigList(request,modelConfig,modelMap);
        modelMap.addAttribute("lookUpType", lookUpType);
        return "modelConfig/modelConfigForCheck";
    }

    @RequestMapping("/modelConfigCreator")
    public String modelConfigCreator(ModelMap modelMap){
        setPageModeForCreator(modelMap);
        getModelConfigViewer(null,modelMap);
        return "modelConfig/modelConfigViewForCreator";
    }

    @RequestMapping("/modelConfigCreator.do")
    public ModelAndView modelConfigCreator(ModelConfig modelConfig){
        ModelConfig result = modelConfigService.createModelConfig(modelConfig);
        if (null != result)
            return getSuccessResponseWithNewId(result.getId());
        return getErrorResponse("添加数据字典失败");
    }

    @RequestMapping("/modelConfigEditor")
    public String modelConfigEditor(Long id, ModelMap modelMap){
        setPageModeForEditor(modelMap);
        getModelConfigViewer(id,modelMap);
        return "modelConfig/modelConfigViewForEditor";
    }

    @RequestMapping("/modelConfigEditor.do")
    public ModelAndView modelConfigEditor(ModelConfig modelConfig){
        ModelConfig result = modelConfigService.createModelConfig(modelConfig);
        if (null != result)
            return getSuccessResponse();
        return getErrorResponse("修改数据字典失败");
    }

    @RequestMapping("/modelConfigViewer")
    public String modelConfigViewer(Long id, ModelMap modelMap){
        setPageModeForViewer(modelMap);
        return getModelConfigViewer(id,modelMap);
    }

    public String getModelConfigViewer(Long id,ModelMap modelMap){
        ModelConfig modelConfig = null != id ? modelConfigService.getModelConfig(id) : new ModelConfig();
        modelMap.addAttribute("data",modelConfig);
        return "modelConfig/modelConfigViewForViewer";
    }

}
