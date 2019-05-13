/*
 * Copyright (C), 2015-9999, 南京紫金数据信息技术有限公司
 * Author: 许欣欣
 * Date: 2017年9月27日
 * Description:
 */

package com.britecloud.marketingcloud.api.utils;

/**
 * @描述: 接口返回常量 .
 * @作者: xuxinxin .
 * @创建时间: 2017年9月27日 .
 * @版本: 1.0 .
 */
public interface ResultConstants
{
    
    
    /**
     * 调用接口成功
     */
    static final String SUCCESS_CODE = "10000";
    
    /**
     * 调用接口成功描述
     */
    static final String SUCCESS_CODE_DESC = "请求成功";
    
    /**
     * 缺少必填参数
     */
    static final String ERROR_INVALID_PARAMS_CODE = "40001";
    
    /**
     * 40001描述
     */
    static final String ERROR_INVALID_PARAMS_CODE_DESC = "非法参数";
    
    /**
     * 未授权
     */
    static final String NO_AUTH_CODE = "401";
    
    /**
     * 请求要求身份验证。对于登录后请求的网页，服务器可能返回此响应
     */
    static final String NO_AUTH_CODE_DESC = "未授权";
    
    /**
     * 请求超时
     */
    static final String TIME_OUT_CODE = "408";
    
    /**
     * 服务器等候请求时发生超时。
     */
    static final String TIME_OUT_CODE_DESC = "请求超时";
    
    /**
     * 服务器内部错误
     */
    static final String SYS_ERROR_CODE = "500";
    
    /**
     * 服务器遇到错误，无法完成请求
     */
    static final String SYS_ERROR_CODE_DESC = "服务器内部错误";
    
    /**
     * 参数不合法
     */
    static final String PARAM_ERROR_CODE = "600";
    
    /**
     * 请求参数为空或者类型不匹配
     */
    static final String PARAM_ERROR_CODE_DESC = "请求参数错误";
    
    /**
     * 返回结果为空
     */
    static final String RESULT_EMPTY_CODE = "700";
    
    /**
     * 请求参数为空或者类型不匹配
     */
    static final String RESULT_EMPTY_CODE_DESC = "请求参数错误";
    
}
