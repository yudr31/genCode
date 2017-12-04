package com.spring.boot.controller;


import com.spring.boot.bean.Page;
import com.spring.boot.utils.JsonMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-17 14:23
 */
public class BaseController {

    @Value("${pageSize}")
    private int pageSize;

    public static int ERROR_CODE_OK = 200;
    public static int ERROR_CODE_ERROR = 300;
    public static int ERROR_CODE_TIMEOUT = 301; // Session Invalid

    public static String AJAX_DONE_MSG_SUCCEED = "操作成功";
    public static String AJAX_DONE_MSG_FAILED = "操作失败";
    public static String AJAX_DONE_MSG_TIMEOUT = "会话超时，请重新登录";

    public ModelAndView getAjaxDoneResponse(int statusCode, String message) {
        ModelAndView mav = new ModelAndView("/template/ajaxDone");
        mav.addObject("statusCode", statusCode);
        mav.addObject("message", message);
        mav.addObject("resultDetail",JsonMapper.toJsonString(""));
        return mav;
    }

    public String assembleErrorMessage(int errorCode, String errorMessage) {
        return "错误代码： " + errorCode + "</br>错误详情： " + errorMessage;
    }

    public ModelAndView getErrorResponse(int errorCode, String errorMessage) {
        return getAjaxDoneResponse(ERROR_CODE_ERROR, assembleErrorMessage(errorCode, errorMessage));
    }

    public ModelAndView getErrorResponse(String errorMessage) {
        return getAjaxDoneResponse(ERROR_CODE_ERROR, errorMessage);
    }

    /**
     * 当前选项卡跳转
     */
    public ModelAndView getForwardSuccessResponse(String forwardUrl) {
        ModelAndView mav = getAjaxDoneResponse(ERROR_CODE_OK, AJAX_DONE_MSG_SUCCEED);
        mav.addObject("forwardUrl", forwardUrl);
        mav.addObject("callbackType", "forward");
        return mav;
    }

    /**
     * 跳转至新选项卡
     */
    public ModelAndView getNewForwardSuccessResponse(String forwardUrl, String navTabId, String navTabTitle) {
        ModelAndView mav = getAjaxDoneResponse(ERROR_CODE_OK, null);
        mav.addObject("forwardUrl", forwardUrl);
        mav.addObject("navTabId", navTabId);
        mav.addObject("navTabTitle", navTabTitle);
        mav.addObject("callbackType", "openCurrentAndForward");
        return mav;
    }

    public ModelAndView getSuccessResponse() {
        return getAjaxDoneResponse(ERROR_CODE_OK, AJAX_DONE_MSG_SUCCEED);
    }

//    public ModelAndView getSuccessResponse(String errorMessage) {
//        return getAjaxDoneResponse(ERROR_CODE_OK, errorMessage);
//    }

    public ModelAndView getSuccessResponse(Object resultData) {
        ModelAndView mav = getAjaxDoneResponse(ERROR_CODE_OK, "操作成功");
        mav.addObject("resultDetail",JsonMapper.toJsonString(resultData));
        return mav;
    }

    public ModelAndView getSuccessResponseWithNewId(Long newId) {
        ModelAndView mav = getSuccessResponse();
        mav.addObject("newId", String.valueOf(newId));
        return mav;
    }

    public void setPageMode(ModelMap modelMap, WebPageViewerMode viewerMode) {
        String mode = null;
        switch (viewerMode) {
            case MODE_VIEWER:
                mode = "_mode_viewer";
                break;
            case MODE_CREATOR:
                mode = "_mode_creator";
                break;
            case MODE_EDITOR:
                mode = "_mode_editor";
                break;
        }
        modelMap.addAttribute("_pageViewerMode", mode);
    }

    public void setPageModeForCreator(ModelMap modelMap) {
        setPageMode(modelMap, WebPageViewerMode.MODE_CREATOR);
    }

    public void setPageModeForEditor(ModelMap modelMap) {
        setPageMode(modelMap, WebPageViewerMode.MODE_EDITOR);
    }

    public void setPageModeForViewer(ModelMap modelMap) {
        setPageMode(modelMap, WebPageViewerMode.MODE_VIEWER);
    }

    public Page getPage(HttpServletRequest request){
        Page page = new Page();
        page.setNumPerPage(pageSize);
        String pageNo = request.getParameter("pageNum");
        String pageSize = request.getParameter("numPerPage");
        if(null != pageNo && !"".equals(pageNo)){
            page.setCurrentPage(Integer.valueOf(pageNo));
        }
        if (null != pageSize && !"".equals(pageSize)){
            page.setNumPerPage(Integer.valueOf(pageSize));
        }
        return page;
    }

    public enum  WebPageViewerMode{
        MODE_CREATOR,MODE_EDITOR,MODE_VIEWER
    }

}
