package com.spring.boot.controller;

import com.github.pagehelper.PageInfo;
import com.spring.boot.bean.FragmentTemplate;
import com.spring.boot.service.FragmentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuderen
 * @version 2017/10/26 17:12
 */
@Controller
@RequestMapping("/fragmentTemplate")
public class FragTemplateController extends BaseController{

    @Autowired
    private FragmentTemplateService fragmentTemplateService;

    @RequestMapping("/fragmentTemplateList")
    public String fragmentTemplateList(HttpServletRequest request, FragmentTemplate fragmentTemplate, ModelMap modelMap){
        String str = null;
        str.toString();
        return getFragmentTemplateList(request,fragmentTemplate,modelMap);
    }

    private String getFragmentTemplateList(HttpServletRequest request, FragmentTemplate fragmentTemplate, ModelMap modelMap){
        PageInfo<FragmentTemplate> pageInfo = fragmentTemplateService.getFragmentTemplateList(getPage(request),fragmentTemplate);
        modelMap.addAttribute("page",pageInfo);
        modelMap.addAttribute("param",fragmentTemplate);
        return "fragmentTemplate/fragmentTemplateList";
    }

    @RequestMapping("/fragmentTemplateForCheck")
    public String fragmentTemplateListForCheck(HttpServletRequest request, FragmentTemplate fragmentTemplate,
                                               String lookUpType, ModelMap modelMap){
        getFragmentTemplateList(request,fragmentTemplate,modelMap);
        modelMap.addAttribute("lookUpType", lookUpType);
        return "fragmentTemplate/fragmentTemplateForCheck";
    }

    @RequestMapping("/fragmentTemplateCreator")
    public String fragmentTemplateCreator(ModelMap modelMap){
        setPageModeForCreator(modelMap);
        getFragmentTemplateViewer(null,modelMap);
        return "fragmentTemplate/fragmentTemplateViewForCreator";
    }

    @RequestMapping("/fragmentTemplateCreator.do")
    public ModelAndView fragmentTemplateCreator(FragmentTemplate fragmentTemplate){
        FragmentTemplate result = fragmentTemplateService.createFragmentTemplate(fragmentTemplate);
        if (null != result)
            return getSuccessResponseWithNewId(result.getId());
        return getErrorResponse("添加数据字典失败");
    }

    @RequestMapping("/fragmentTemplateEditor")
    public String fragmentTemplateEditor(Long id, ModelMap modelMap){
        setPageModeForEditor(modelMap);
        getFragmentTemplateViewer(id,modelMap);
        return "fragmentTemplate/fragmentTemplateViewForEditor";
    }

    @RequestMapping("/fragmentTemplateEditor.do")
    public ModelAndView fragmentTemplateEditor(FragmentTemplate fragmentTemplate){
        String str = null;
        str.toString();
        FragmentTemplate result = fragmentTemplateService.createFragmentTemplate(fragmentTemplate);
        if (null != result)
            return getSuccessResponse();
        return getErrorResponse("修改数据字典失败");
    }

    @RequestMapping("/fragmentTemplateViewer")
    public String fragmentTemplateViewer(Long id, ModelMap modelMap){
        setPageModeForViewer(modelMap);
        return getFragmentTemplateViewer(id,modelMap);
    }

    public String getFragmentTemplateViewer(Long id,ModelMap modelMap){
        FragmentTemplate fragmentTemplate = null != id ? fragmentTemplateService.getFragmentTemplate(id) : new FragmentTemplate();
        modelMap.addAttribute("data",fragmentTemplate);
        return "fragmentTemplate/fragmentTemplateViewForViewer";
    }

}
