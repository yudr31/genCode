package com.spring.boot.interceptor;

import com.spring.boot.exception.CommonErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuderen
 * @version 2017/11/21 17:19
 */
@ControllerAdvice
public class SystemExceptionHandler extends SimpleMappingExceptionResolver {

    private Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView doResolveException(HttpServletRequest request,Exception ex){
        String servletPath = request.getServletPath();
        logger.error(servletPath,ex);
        String message = ex instanceof CommonErrorException ? ex.getMessage() : "系统错误";
        ModelAndView mav = new ModelAndView("/template/ajaxDone");
        mav.addObject("statusCode",200);
        mav.addObject("message", message);
        return mav;
    }

}
