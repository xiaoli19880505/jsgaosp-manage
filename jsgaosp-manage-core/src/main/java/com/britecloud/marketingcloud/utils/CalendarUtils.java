/** 
 * 项目名称:91营销云
 * 文件名：CalendarUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarUtils {

	
	/**
	 * 得到上个月第一天
	 * @return
	 */
	public static Date getFirstDay() {
		 Calendar calendar  =   new  GregorianCalendar();
		 if((calendar.get(Calendar.MONTH) -1)!=0){
			 calendar.set( Calendar.MONTH, calendar.get(Calendar.MONTH) -1);
			 calendar.set(Calendar.DAY_OF_MONTH, 1); 
		 }else{
			 calendar.set( Calendar.YEAR, calendar.get(Calendar.YEAR) -1);
			 calendar.set(Calendar.MONTH, 12); 
			 calendar.set(Calendar.DAY_OF_MONTH, 1); 
		 }
		 //SimpleDateFormat simpleFormate  =   new  SimpleDateFormat( " yyyy-MM-dd" );
		 //return  simpleFormate.format(calendar.getTime());
		 return  calendar.getTime();
		  }
	
	/**
	 * 得到明天凌晨时间
	 * @return
	 */
	public static Date getTomorrow(){
		Date date=new Date();//取时间
		 Calendar c = new GregorianCalendar();
		 c.setTime(date);
		 c.add(c.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		 c.set(java.util.Calendar.HOUR_OF_DAY, 0);
		 c.set(java.util.Calendar.MINUTE, 0);
		 c.set(java.util.Calendar.SECOND, 0);
		 //SimpleDateFormat simpleFormate  =   new  SimpleDateFormat( " yyyy-MM-dd" );
		 //return simpleFormate.format(c.getTime());
		 return c.getTime();
	}
	
	/**
	 * 得到MM-dd格式的数据
	 * @param createTime
	 * @return
	 */
	public static String getFormatMD(Date createTime){
		 Calendar c = new GregorianCalendar();
		 c.setTime(createTime);
		 SimpleDateFormat simpleFormate  =   new  SimpleDateFormat( "MM-dd" );
		 return simpleFormate.format(c.getTime());
	}
	
	/**
	 * 得到yyyy-MM-dd格式的数据
	 * @param createTime
	 * @return
	 */
	public static String getFormatYMD(Date createTime){
		 Calendar c = new GregorianCalendar();
		 c.setTime(createTime);
		 SimpleDateFormat simpleFormate  =   new  SimpleDateFormat( "yyyy-MM-dd" );
		 return simpleFormate.format(c.getTime());
	}
	
	/**
	 * 将String转化为Date
	 * @param timeStr
	 * @return
	 */
	public static Date StringToDate(String timeStr){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		try {
			Date time=sdf.parse(timeStr);
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
    /** 
     * 根据开始时间和结束时间返回时间段内的时间集合 
     *  
     * @param beginDate 
     * @param endDate 
     * @return List 
     */  
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {  
        List<Date> lDate = new ArrayList<Date>();  
        lDate.add(beginDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDate);  
        boolean bContinue = true;  
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        }  
        lDate.add(endDate);// 把结束时间加入集合  
        return lDate;  
    }  
	
}
