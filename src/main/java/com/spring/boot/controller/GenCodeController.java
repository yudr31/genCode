package com.spring.boot.controller;

import com.spring.boot.bean.MasterSlaveModel;
import com.spring.boot.bean.ModelConfig;
import com.spring.boot.bean.table.SingleTableInfo;
import com.spring.boot.bean.table.TableInfo;
import com.spring.boot.service.GenCodeService;
import com.spring.boot.utils.FileUtil;
import com.spring.boot.utils.FreeMarkerUtil;
import com.spring.boot.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-16 21:40
 */
@Controller
@RequestMapping("/genCode")
public class GenCodeController extends BaseController{

    private static Logger logger = Logger.getLogger(GenCodeController.class);

    @Autowired
    private GenCodeService genCodeService;

    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        TableInfo tableInfo = new TableInfo();
        modelMap.addAttribute("tableInfo",tableInfo);
        return "genCode/index";
    }

    @RequestMapping("/querySingleTable")
    public String querySingleTable(SingleTableInfo tableInfo, ModelMap modelMap){
        genCodeService.querySingleTable(tableInfo);
        modelMap.addAttribute("tableInfo",tableInfo);
        return "genCode/singleModel/singleTable";
    }

    @RequestMapping("/generateSingleTable")
    public ModelAndView generateSingleTable(SingleTableInfo tableInfo){
        genCodeService.generateSingleTable(tableInfo);
        return getSuccessResponse(tableInfo.getProtoEntity());
    }

    @RequestMapping("/previewSingleTable")
    public String previewSingleTable(SingleTableInfo tableInfo, ModelMap modelMap) throws UnsupportedEncodingException {
        Map result = genCodeService.previewSingleTable(tableInfo);
        modelMap.addAttribute("result",result);
        logger.info("******************测试******************");
        return "genCode/preview";
    }

    @RequestMapping("/queryMasterSlaveTable")
    public String queryMasterSlaveTable(MasterSlaveModel masterSlaveModel, ModelMap modelMap){
        genCodeService.queryMasterSlaveTable(masterSlaveModel);
        modelMap.addAttribute("masterSlave",masterSlaveModel);
        return "genCode/masterSlave/masterSlaveTable";
    }

    @RequestMapping("/generateMasterSlaveTable")
    public ModelAndView generateMasterSlaveTable(MasterSlaveModel masterSlaveModel){
        genCodeService.generateMasterSlaveTable(masterSlaveModel);
        return getSuccessResponse();
    }

    @RequestMapping("/previewMasterSlaveTable")
    public String previewMasterSlaveTable(MasterSlaveModel masterSlaveModel, ModelMap modelMap) throws UnsupportedEncodingException {
        Map result = genCodeService.previewMasterSlaveTable(masterSlaveModel);
        modelMap.addAttribute("result",result);
        return "genCode/preview";
    }

    @ResponseBody
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response){
        String fileName = request.getParameter("fileName");
        String downloadPath = request.getSession().getServletContext().getRealPath("/download");
        FileUtil.zipAndDownload(response,downloadPath + "/" + fileName,fileName);
    }

    @RequestMapping("/previewGenerate")
    public ModelAndView previewGenerate(ModelConfig model){
        String filePath = StringUtil.addFileSeparator(model.getSavePath()) + model.getFileName();
        FreeMarkerUtil.generateFile(model.getFileName(),model.getModelContent());
        return getSuccessResponse(model.getFileName());
    }

    @ResponseBody
    @RequestMapping("/downloadPreviewFile")
    public void downloadPreviewFile(HttpServletRequest request, HttpServletResponse response){
        String fileName = request.getParameter("fileName");
        String downloadPath = request.getSession().getServletContext().getRealPath("/download");
        FileUtil.downloadFile(response,StringUtil.addFileSeparator(downloadPath) + fileName,fileName);
    }

}
