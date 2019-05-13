/*
 * Copyright (C), 2015-9999, 南京紫金数据信息技术有限公司
 * Author: 许欣欣
 * Date: 2017年9月27日
 * Description:
 */

package com.britecloud.marketingcloud.api.utils;

/**
 * @描述: 错误编码
 * @作者: xuxinxin .
 * @创建时间: 2017年9月27日 .
 * @版本: 1.0 .
 */
public enum ExceptionEnum
{
    
    NO_AUTH(ResultConstants.NO_AUTH_CODE, ResultConstants.NO_AUTH_CODE_DESC),
    
    TIME_OUT(ResultConstants.TIME_OUT_CODE, ResultConstants.TIME_OUT_CODE_DESC),
    
    SYSTEM_ERROR(ResultConstants.SYS_ERROR_CODE,
            ResultConstants.SYS_ERROR_CODE_DESC), PARAM_ERROR(
                    ResultConstants.PARAM_ERROR_CODE,
                    ResultConstants.PARAM_ERROR_CODE_DESC), EMPTY_RETURN(
                            ResultConstants.RESULT_EMPTY_CODE,
                            ResultConstants.RESULT_EMPTY_CODE_DESC);
    
    private String code;
    
    private String msg;
    
    ExceptionEnum(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
}
