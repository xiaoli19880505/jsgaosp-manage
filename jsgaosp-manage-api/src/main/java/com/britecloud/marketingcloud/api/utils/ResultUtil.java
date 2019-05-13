/*
 * Copyright (C), 2015-9999, 南京紫金数据信息技术有限公司
 * Author: 许欣欣
 * Date: 2017年9月27日
 * Description:
 */

package com.britecloud.marketingcloud.api.utils;

import org.apache.commons.lang.StringUtils;

import com.britecloud.marketingcloud.common.ResponseResult;

/**
 * @描述: 结果处理工具类
 * @作者: xuxinxin .
 * @创建时间: 2017年9月27日 .
 * @版本: 1.0 .
 */
public class ResultUtil
{
    
    
    /**
     * 返回成功，传入返回体具体出參
     * 
     * @param object
     * @return
     */
    public static ResponseResult success(Object object)
    {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultConstants.SUCCESS_CODE);
        result.setMsg(ResultConstants.SUCCESS_CODE_DESC);
        result.setData(object);
        return result;
    }
    
    /**
     * 提供给部分不需要出參的接口
     * 
     * @return
     */
    public static ResponseResult success()
    {
        return success(null);
    }
    
    public static ResponseResult success(Object object, String msg)
    {
        if (StringUtils.isEmpty(msg))
        {
            return null;
        }
        ResponseResult result = new ResponseResult();
        result.setCode(ResultConstants.SUCCESS_CODE);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
    
    /**
     * 自定义错误信息
     * 
     * @param code
     * @param msg
     * @return
     */
    public static ResponseResult error(String code, String msg)
    {
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
    
    /**
     * 返回异常信息，在已知的范围内
     * 
     * @param exceptionEnum
     * @return
     */
    public static ResponseResult error(ExceptionEnum exceptionEnum)
    {
        ResponseResult result = new ResponseResult();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }
}
