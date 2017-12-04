package com.spring.boot.exception;

/**
 * @author yuderen
 * @version 2017/11/21 17:38
 */
public class CommonErrorException extends Exception {

    private enum CommonErrorCode{

        UNKNOW_ERROR(-1,"未知错误");

        private Integer code;
        private String msg;

        CommonErrorCode(Integer code, String msg){
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    private CommonErrorCode errorCode;
    private String errorMsg;

    public CommonErrorException(CommonErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMsg = errorCode.getMsg();
    }

    public CommonErrorException(String errorMsg){
        this.errorCode = CommonErrorCode.UNKNOW_ERROR;
        this.errorMsg = errorMsg;
    }

    public CommonErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(CommonErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
