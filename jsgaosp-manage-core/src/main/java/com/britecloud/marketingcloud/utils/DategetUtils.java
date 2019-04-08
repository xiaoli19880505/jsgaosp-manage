/** 
 * 项目名称:91营销云
 * 文件名：DategetUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DategetUtils {

	public static Date getToday(){
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		c.set(java.util.Calendar.HOUR_OF_DAY, 0);
		c.set(java.util.Calendar.MINUTE, 0);
		c.set(java.util.Calendar.SECOND, 0);
		c.set(java.util.Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
		return c.getTime();
	}
	
	public static Date getTomorrow(){
		Date date=new Date();//取时间
		 Calendar c = new GregorianCalendar();
		 c.setTime(date);
		 c.add(c.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		 c.set(java.util.Calendar.HOUR_OF_DAY, 0);
		 c.set(java.util.Calendar.MINUTE, 0);
		 c.set(java.util.Calendar.SECOND, 0);
		 return c.getTime();
	}
	
	public static Date getYesterDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, -1);
		c.set(java.util.Calendar.HOUR_OF_DAY, 0);
		c.set(java.util.Calendar.MINUTE, 0);
		c.set(java.util.Calendar.SECOND, 0);
		c.set(java.util.Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date getDay(int i){
	    Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(c.DATE, i);
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);
        c.set(java.util.Calendar.MILLISECOND, 0);
        return c.getTime();
	}
	
	public static Date getAfterDay(String endtimeStr){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Date dateEnd;
		try {
			dateEnd = sdf.parse(endtimeStr);
			Calendar c = new GregorianCalendar();
			 c.setTime(dateEnd);
			 c.add(c.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
			 c.set(java.util.Calendar.HOUR_OF_DAY, 0);
			 c.set(java.util.Calendar.MINUTE, 0);
			 c.set(java.util.Calendar.SECOND, 0);
			 return c.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return null;
	}
	
	public static Date getFirstDay() 
	 {// Date date=new Date();//取时间
	   Calendar c = new GregorianCalendar();
	  // c.setTime(date);   
      c.add(Calendar.MONTH, 0);
	   c.set(Calendar.DAY_OF_MONTH,1);
	// c.add(c.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
	 c.set(java.util.Calendar.HOUR_OF_DAY, 0);
	 c.set(java.util.Calendar.MINUTE, 0);
	 c.set(java.util.Calendar.SECOND, 0);
	 return c.getTime();
	 }
	
	public static String time() 
	{  Date date=new Date(); 
	   DateFormat format=new SimpleDateFormat(" yyyy-MM-dd"); 
	   String time=format.format(date); 
	   return time; 
	   }
	

	 public static String getFirstDay1() {
		 Calendar calendar  =   new  GregorianCalendar();
		 calendar.set( Calendar.DATE,  1 );
		 SimpleDateFormat simpleFormate  =   new  SimpleDateFormat( " yyyy-MM-dd" );
		 return  simpleFormate.format(calendar.getTime());
		  }

 
}
