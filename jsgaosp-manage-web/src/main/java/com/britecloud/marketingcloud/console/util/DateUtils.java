package com.britecloud.marketingcloud.console.util;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class DateUtils  extends org.apache.commons.lang.time.DateUtils {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String ALIGN_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_SQUOTE_DATE_FORMAT = "yyyy/MM/dd";
    public static final String DEFAULT_NOSQUOTE_DATE_FORMAT = "yyyyMMdd";


    private static final double[] LIMITS = { 0, 1, 2 };

    private static final String[] MINUTES_PART =
            { "", "1 minute ", "{0,number} minutes " };

    private static final String[] SECONDS_PART =
            { "0 seconds", "1 second", "{1,number} seconds" };

    private static final ChoiceFormat MINUTES_FORMAT =
            new ChoiceFormat(LIMITS, MINUTES_PART);

    private static final ChoiceFormat SECONDS_FORMAT =
            new ChoiceFormat(LIMITS, SECONDS_PART);

    private static final MessageFormat MINUTE_SECONDS =
            new MessageFormat("{0}{1}");

    static {
        MINUTE_SECONDS.setFormat(0, MINUTES_FORMAT);
        MINUTE_SECONDS.setFormat(1, SECONDS_FORMAT);
    }

    public static final long  ONE_SECOND = 1000;
    public static final long  ONE_MINUTE = 60*ONE_SECOND;
    public static final long  ONE_HOUR   = 60*ONE_MINUTE;
    public static final long  ONE_DAY    = 24*ONE_HOUR;
    public static final long  ONE_WEEK   = 7*ONE_DAY;


    public static final SimpleDateFormat _defDateTimeFmt =
            new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);

    public static final SimpleDateFormat _defDateFmt =
            new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    public static String toString(Date date, String format) {

        SimpleDateFormat formatter;

        if ((date == null) || (format == null) || (format.length() == 0)) {
            return null;
        }
        formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static Date toDate(String str, String format) {
        if ((str == null)
                || (str.length() == 0)
                || (format == null)
                || (format.length() == 0)) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setLenient(false);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(str, pos);
    }

    public static boolean compare(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        }
        if (date1 == null || date2 == null)
            return false;
        else
            return date1.getTime() == date2.getTime();
    }

    public static Date toDate(String str) {
        try {
            if (str.indexOf(':') > 0) {
                return  toDate(str, DEFAULT_DATETIME_FORMAT);
            } else {
                return  toDate(str, DEFAULT_DATE_FORMAT);
            }
        } catch (Exception ex) {
            return null;
        }
    }



    public static String currentDateToString(String format) {
        Date date = new Date();
        return toString(date, format);
    }

    public static String curDateStr() {
        return _defDateFmt.format(new Date());
    }

    public static String curDateTimeStr() {
        return _defDateTimeFmt.format(new Date());
    }

    public static String formatElapsedTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        Object[] args = { new Long(minutes), new Long(seconds % 60)};
        return MINUTE_SECONDS.format(args);
    }

    public static String toDefDateString(Date date) {
        return toString(date, DEFAULT_DATE_FORMAT);
    }

    public static String toDefDatetimeString(Date date) {
        return toString(date, DEFAULT_DATETIME_FORMAT);
    }

    public static String toDefTimeString(Date date) {
        return toString(date, DEFAULT_TIME_FORMAT);
    }
    public static String convertSecondsToTime(Long seconds){
        long  s;//秒
        long  h;//小时
        long  m;//分钟
        if(0==seconds){
            return "00:00:00";
        }
        seconds = seconds * 1000;
        h = seconds/1000/60/60;
        m=(seconds-h*60*60*1000)/1000/60;
        s=seconds/1000-h*60*60-m*60;
        return (h<10?("0"+h):h) + ":"+ (m<10?("0"+m):m) + ":" + (s<10?("0"+s):s);
    }

    public static Date getTodayStartTime(){
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getYearStartTime() {
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        c.set(java.util.Calendar.DAY_OF_YEAR, 0);
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);
        return c.getTime();
    }

    public static String getSmartDateString(Date date,String formatString){
        SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日");
        SimpleDateFormat sdf1=new SimpleDateFormat(formatString);
        Long dayLong=24*3600*1000l;
        Long todayStart=DateUtils.getTodayStartTime().getTime();
        Long last=todayStart-date.getTime();
        String time=sdf1.format(date);
        if(last<=0){
            return "今天"+time;
        }else if(last/dayLong>=1){
            return sdf.format(date)+" "+time;
        }else if(last/dayLong==0){
            return "昨天"+time;
        }else{
            return "";
        }
    }

    public static String getSmartDateString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日");
        Long dayLong=24*3600*1000l;
        Long todayStart=DateUtils.getTodayStartTime().getTime();
        Long last=todayStart-date.getTime();
        if(last<=0){
            Long time=new Date().getTime()-date.getTime();
            int s=(int) (time/1000);
            if(s>=3600){
                return s/3600+"小时前";
            }else if(s>=60){
                return s/60+"分钟前";
            }else{
                return "刚刚";
            }
        }else if(last/dayLong>10){
            return sdf.format(date);
        }else if(last/dayLong>=1){
            return last/dayLong+1+"天前";
        }else if(last/dayLong==0){
            return "昨天";
        }else{
            return "";
        }
    }

    public static int getDays(Date startTime, Date endTime) {
        Long time=endTime.getTime()-startTime.getTime();
        int days=(int) (time/(3600000*24));
        if(endTime.getTime()==toDate(toDefDateString(endTime)).getTime()){
            return days;
        }
        return days+1;
    }

    public static String getSmartLeftTime(Long leftTime) {
        if(leftTime>ONE_DAY){
            return leftTime/ONE_DAY+"天以上";
        }else if(leftTime>=ONE_HOUR){
            return leftTime/ONE_HOUR+"小时以上";
        }else if(leftTime>=ONE_MINUTE){
            return leftTime/ONE_HOUR+1+"分钟";
        }else if(leftTime>0){
            return "一分钟";
        }else{
            return "";
        }
    }

    /**
     * 距离目标日期相应天数的日期
     *
     * @param date
     *            目标日期
     * @param to
     *            距离天数
     * @return
     */
    public static String getDateToToday(String date, int to) {
        String str = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date myDate = formatter.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(myDate);
            c.add(Calendar.DATE, to);
            myDate = c.getTime();
            str = formatter.format(myDate);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return str;
    }

    /*
     * 将时间戳转换为日期
     */
    public static String stampToDateString(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为日期
     */
    public static Date stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);

        return date;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToTime(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 时间格式 2017-10-12 21:12:39 转 2017-10-12
     */
    public static String timeToDate(String s) {
        String res = s;

        if(s.length()>11){
            res = s.substring(0, 11);
        }
        return res.trim();
    }

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null){
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(24*60*60*1000);
    }

    /**
     * 获取过去的小时
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*60*1000);
    }

    /**
     * 获取过去的分钟
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis){
        long day = timeMillis/(24*60*60*1000);
        long hour = (timeMillis/(60*60*1000)-day*24);
        long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
        long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
        long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
        return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 日期计算 返回计算后的日期 yyyy-MM-dd
     * @param date 传入时间
     * @param num 计算天数 +加 -减
     * @return
     */
    public static String getDayCalc(Date date,int num){
        String formatDate = DateFormatUtils.format(new Date(date.getTime() + num * 24 * 60 * 60 * 1000), "yyyy-MM-dd");
        return formatDate;
    }
    /**
     * 比较日期 dt1 >dt2返回1 dt1<dt2后返回-1 dt1=dt2返回0
     * @param dt1
     * @param dt2
     * @return
     */
    public static int compare_date(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**获得当前时间的小时号*/
    public static String getCurrentHour(){
        SimpleDateFormat df = new SimpleDateFormat("H");
        String hours = "";
        hours = df.format(new Date());
        return hours;
    }

    /**获得当前时间的分钟号*/
    public static String getCurrentMin(){
        SimpleDateFormat df = new SimpleDateFormat("m");
        String hours = "";
        hours = df.format(new Date());
        return hours;
    }

}

