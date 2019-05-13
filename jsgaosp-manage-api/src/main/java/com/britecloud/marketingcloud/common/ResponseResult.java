/*
 * Copyright (C), 2015-9999, 南京紫金数据信息技术有限公司
 * Author: 许欣欣
 * Date: 2017年9月27日
 * Description:
 */

package com.britecloud.marketingcloud.common;

/**
 * @描述: 响应结果
 * @作者: xuxinxin .
 * @创建时间: 2017年9月27日 .
 * @版本: 1.0 .
 */
public class ResponseResult<T>
{
    
    
    // error_code 状态值：10000为成功，其他数值代表失败
    private String code;
    
    // error_msg 错误信息，若code为10000时，为success
    private String msg;
    
    // content 返回体报文的出参，使用泛型兼容不同的类型
    private T data;
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public T getData(Object object)
    {
        return data;
    }
    
    public void setData(T data)
    {
        this.data = data;
    }
    
    public T getData()
    {
        return data;
    }
    
    @Override
    public String toString()
    {
        return "Result{" + "code=" + code + ", msg='" + msg + '\'' + ", data="
                + data + '}';
    }
    
}
