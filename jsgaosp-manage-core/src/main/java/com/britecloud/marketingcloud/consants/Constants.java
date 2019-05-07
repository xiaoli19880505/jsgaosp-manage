/** 
 * 项目名称:91营销云
 * 文件名：Constants.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.consants;

public class Constants {
	public static final String UN_FILTERED_URIS="(/)|(/auth/.*)|(/global/in)|(/global/out)|(.*\\.html)|(.*\\.png)|(.*\\.jpg)|(.*\\.map)|(.*\\.gif)|(.*\\.ico)|(.*\\.css)|(.*\\.swf)|(.*\\.js)|(.*\\.pdf)|(.*\\.eot)|(.*\\.svg)|(.*\\.ttf)|(.*\\.woff)|(.*\\.otf)|(.*\\.json)|(.*\\.JSON)";

	//图标
	public static final String SYSADMIN_ICON = "<i class='fa fa-user'></i>&nbsp;&nbsp;管&nbsp;理&nbsp;员";
	public static final String USER_ICON = "<i class='fa icon-user'></i>&nbsp;&nbsp;管&nbsp;理&nbsp;员";
	public static final String MAINTAIN_ICON = "<i class='fa icon-wrench'></i>&nbsp;&nbsp;运维人员";

	//代码格式
	public static final String SEGMENT_CODE = "客户分群CODE";
	public static final String TARGET_CODE = "目标客户分群CODE";
	public static final String COMPAIGN_CODE = "营销活动CODE";
	public static final String OFFER_CODE = "推荐品CODE";

	//渠道类别
	public static final String CHANNEL_EMAIL_TYPE = "EMAIL";//邮件
	public static final String CHANNEL_MESSEGE_TYPE = "SMS";//短信
	public static final String CHANNEL_OUTBOUND_TYPE = "OUTBOUND";//外呼
	public static final String CHANNEL_WEIBO_TYPE = "WEIBO";//微博
	public static final String CHANNEL_WEIXIN_TYPE = "WEIXIN";//微信
	public static final String CHANNEL_APP_TYPE = "APP";//app
	public static final String CHANNEL_ALL_TYPE = "CHANNEL_ALL";//所有渠道

	//渠道集成设置
	public static final String CHANNEL_EMAIL_SENDCLOUD="SENDCLOUD";
	public static final String CHANNEL_EMAIL_SMTP="SMTP";
	public static final String CHANNEL_SMS_YUNPIAN="YUNPIAN";
	public static final String CHANNEL_PUSH_JPSH="JPUSH";
	public static final String CHANNEL_EMAIL_DIRECTMAIL="DIRECTMAIL";//阿里云邮件推送
	public static final String CHANNEL_PUSH_ALIMOBILEPUSH="ALIYUNMOBILEPUSH";//阿里云移动推送

	//优化参数
	public static final String OPT_FILTER_YES = "0";
	public static final String OPT_FILTER_NO="1";


	//流程调度参数
	public static final String SEND_JOB_TYPE = "send_offer"; // Offer发送任务
	//private static String EXEC_JOB_TYPE = "exec_job"; // 流程执行任务
	public static final String EVENT_JOB_TYPE = "exec_flow_job"; // 流程执行任务
	public static final String EVENT_SCH_JOB_TYPE = "wakeup_flow_job";//流程唤醒任务
	public static final String SEGMENT_COUNT_JOB_TYPE = "segment_count_job";//客户分群计算任务
	public static final String DATASOURCE_SYNC_JOB_TYPE = "datasource_sync_job";//数据源同步任务
	//客户分群
	public static final String SEGMENT_ALL = "SEGMENT_ALL";//所有客户

	//推荐品
	public static final String OFFER_ALL = "OFFER_ALL";//所有推荐品和推荐品列表

	public static final String CHANNEL_TEMPLATE_VERIFY_OK="1";  //审核通过
	public static final String CHANNEL_TEMPLATE_VERIFY_FAIL="2";  //审核失败
	public static final String CHANNEL_TEMPLATE_VERIFY_WAIT="0";  //审核中


	public static final String CHANNEL_TEMPLATE_VERIFY_ING="4";  //界面已提交审核
	public static final String CHANNEL_TEMPLATE_NOT_SUBMIT="3";  //界面待提交审核

	public final static String CYCLE_TYPE_01 = "1"; //每天
	public final static String CYCLE_TYPE_02 = "2"; //每周
	public final static String CYCLE_TYPE_03 = "3"; //每月
	public final static String CYCLE_TYPE_04 = "4"; //每季度
	public final static String CYCLE_TYPE_05 = "5"; //每年

	//数据类型
	public final static String STRING = "1"; //字符串类型
	public final static String INT = "2";    //整型
	public final static String DATE = "3";   //日期类型
	public final static String DOUBLE = "4"; //浮点类型
	public final static String ENUM = "5"; //枚举类型
	public final static String BOOL = "6";   //布尔类型


	//
	public final static String JS_MAIN = "todo";

	//MongoDB 表名字
	public final static String TB_USER_INFO = "UserInfo";
	public final static String TB_TARGET_User="TargetUser_";
	public final static String DB_NAME="britecloud_";
	public final static String DATASOURCE_NAME="datasource_"; 
	public final static String RESPONSE_NAME="Response";
	public final static String RESPONSE_SUMMARY_NAME="ResponseSummary";
	public final static String RESPONSE_HISTORY_NAME="ResponseHistory";
	public final static String EVENTS_NAME="Events";
	public static final String PAGE_VIEW = "PageView";
	public static final String PHOTO = "Photo";




	public final static String MONGODB_COLLECTION_USERS = "Users";
	public final static String MONGODB_COLLECTION_CARTS = "Carts";
	public final static String MONGODB_COLLECTION_ORDERS = "Orders";
	public final static String MONGODB_COLLECTION_RESPONSES="Responses";
	public final static String MONGODB_COLLECTION_RESPONSE_SUMMARY_NAME="ResponseSummary";
	public final static String MONGODB_COLLECTION_EVENTS="Events";
	public static final String MONGODB_COLLECTION_PAGEVIEWS = "PageViews";
	public static final String MONGODB_COLLECTION_SEGMENT = "Segments";
	public static final String MONGODB_COLLECTION_BLACKLIST = "BlackList";
	public static final String MONGODB_COLLECTION_ITEMS = "Items";

	//创建新公司时，默认创建的数据源和数据源字段
	public static final String INIT_DATATABLE_USER = "Users";
	public static final String INIT_DATATABLE_CART = "Carts";
	public static final String INIT_DATATABLE_ORDER = "Orders";
	//public static final String INIT_DATATABLE_EVENT = "Events";
	public static final String INIT_DATATABLE_PAGEVIEW = "PageViews";
	//public static final String INIT_DATATABLE_RESPONSE = "Responses";

	//数据源CODE和NAME
	public static final String[] DATATABLE_CODE = {INIT_DATATABLE_USER,INIT_DATATABLE_CART,INIT_DATATABLE_ORDER,INIT_DATATABLE_PAGEVIEW};
	public static final String[] DATATABLE_NAME = {"会员信息","购物车数据","订单数据","浏览数据"};

	//数据源字段CODE和NAME
	//Users
	public static final String[] USER_FIELD_CODE = {"userId","name","idCardNo","education","email","mobilePhone","sex","job","maritalStatus","nationality","score","consumingAbility"};
	public static final String[] USER_FIELD_NAME = {"用户ID","姓名","身份证号码","教育程度","电子邮件地址","手机号码","性别","职业","婚姻状况","民族","会员积分","消费能力"};                                                    	
	public static final String[] USER_FIELD_TYPE = {"字符串","字符串","字符串","字符串","字符串","字符串","枚举","字符串","枚举","字符串","数字","数字"};
	//Carts
	/*public static final String[] CARTS_FIELD_CODE = {"productId","sku","name","description","categories","price","quantity","imageUrl","url","buyDate","userId"};
		public static final String[] CARTS_FIELD_NAME = {"商品ID","SKU","商品名称","商品描述","商品分类","商品价格","商品数量","图片URL","商品URL","购买日期","用户ID"};
		public static final String[] CARTS_FIELD_TYPE = {"字符串","字符串","字符串","字符串","字符串","货币","数字","字符串","字符串","日期","字符串"};*/
	//Carts
	public static final String[] CARTS_FIELD_CODE = {"itemId","count","sellAmount","userId","transactionId"};
	public static final String[] CARTS_FIELD_NAME = {"商品ID","商品数量","销售总额","用户ID","交易ID"};
	public static final String[] CARTS_FIELD_TYPE = {"字符串","数字","数字","字符串","字符串"};
	//Orders
	/*public static final String[] ORDERS_FIELD_CODE = {"productId","SkuCode","name","description","categories","price","quantity","imageUrl","url","buyDate","userId"};
		public static final String[] ORDERS_FIELD_NAME = {"商品ID","SKU","商品名称","商品描述","商品分类","商品价格","商品数量","图片URL","商品URL","购买日期","用户ID"};
		public static final String[] ORDERS_FIELD_TYPE = {"字符串","字符串","字符串","字符串","字符串","货币","数字","字符串","字符串","日期","字符串"};*/
	//Orders
	public static final String[] ORDERS_FIELD_CODE = {"orderId","itemId","sellAmount","sellCount","sellDate","userId","transactionId"};
	public static final String[] ORDERS_FIELD_NAME = {"订单ID","商品ID","销售总金额","销售数量","销售日期","用户ID","交易ID"};
	public static final String[] ORDERS_FIELD_TYPE = {"字符串","字符串","数字","数字","日期","字符串","字符串"};

	//Events
	public static final String[] EVENTS_FIELD_CODE = {"companyId","createdAt","eventName","userId"};
	public static final String[] EVENTS_FIELD_NAME = {"公司ID","创建时间","事件名","用户ID"};
	public static final String[] EVENTS_FIELD_TYPE = {"字符串","日期","字符串","字符串"};
	//Pageviews
	/*public static final String[] PAGEVIEWS_FIELD_CODE = {"userId","createdAt","device","deviceModel","ip","pageName","screen","transcationId","url"};
		public static final String[] PAGEVIEWS_FIELD_NAME = {"用户ID","创建时间","设备","设备型号","IP","页面名称","屏幕尺寸","交易ID","域名"};
		public static final String[] PAGEVIEWS_FIELD_TYPE = {"字符串","日期","字符串","字符串","字符串","字符串","字符串","字符串","字符串"};*/
	//Pageviews
	/*public static final String[] PAGEVIEWS_FIELD_CODE = {"itemId","name","url","imageUrl","simageUrl","marketPrice","price","vipPrice","discount","brand","brandId","endTime","onsale","stock","userId","createdAt","transcationId"};
		public static final String[] PAGEVIEWS_FIELD_NAME = {"商品ID","商品名称","商品URL","商品大图","商品小图","市场价","售价","vip价或特价","折扣","品牌名称","品牌ID","失效时间","是否下架","库存","用户ID","创建时间","交易ID"};
		public static final String[] PAGEVIEWS_FIELD_TYPE = {"字符串","字符串","字符串","字符串","字符串","数字","数字","数字","数字","字符串","品牌ID","日期","布尔","数字","字符串","日期","字符串"};*/
	public static final String[] PAGEVIEWS_FIELD_CODE = {"userId","url","pageName","createdAt","transcationId"};
	public static final String[] PAGEVIEWS_FIELD_NAME = {"用户ID","路径","页面名称","创建时间","交易ID"};
	public static final String[] PAGEVIEWS_FIELD_TYPE = {"字符串","字符串","字符串","日期","字符串"};
	//Responses
	public static final String[] RESPONSES_FIELD_CODE = {"userId","cartCount","channelType","clickCount","compaignId","expSendTime","flowId","msgTmplId","msgType","openCount","optimizeStatus","orderCount","pv","sendStatus","userGroup","uv"};
	public static final String[] RESPONSES_FIELD_NAME = {"用户ID","购物车次数","渠道类型","点击次数","活动ID"," 预期发送时间","流程ID","消息模板ID","消息类型","打开次数","优化状态","购买次数","浏览次数","发送状态","用户群","用户浏览次数"};
	public static final String[] RESPONSES_FIELD_TYPE = {"字符串","数字","字符串","数字","字符串","日期","字符串","字符串","字符串","数字","字符串","数字","数字","字符串","字符串","数字"};
	//public final static String PARQUET_ROOT = "hdfs://192.168.200.31:8020/marketingcloud/";
	public final static String PARQUET_ROOT = "/marketingcloud/";

	public final static String PARQUET_FOLDER_USERS = "Users";
	public final static String PARQUET_FOLDER_CARTS = "Carts";
	public final static String PARQUET_FOLDER_ORDERS = "Orders";
	public final static String PARQUET_FOLDER_RESPONSES="Responses";
	public final static String PARQUET_FOLDER_RESPONSE_SUMMARY_NAME="ResponseSummary";
	public final static String PARQUET_FOLDER_EVENTS="Events";
	public static final String PARQUET_FOLDER_PAGEVIEWS = "PageViews";
	public static final String PARQUET_FOLDER_SEGMENTS = "Segments";

	//客户分群类型
	public static final String DYNAMIC_SEGMENT = "Dynamic";
	public static final String STATIC_SEGMENT = "Static";
	//Job Group
	//
	public final static String JOB_GROUP_DAY = "job_group_day";
	//
	public final static String JOB_GROUP_WEEK="job_group_week";
	//
	public final static String JOB_GROUP_MONTH="job_group_month";
	//
	public final static String JOB_GROUP_QUARTER="job_group_quarter";
	//
	public final static String JOB_GROUP_YEAR="job_group_year";

	//通用函数AVG
	public final static String FUN_AVG = "function def_avg(obj){var avg;for(var i=0;i<obj.length;i++) { avg+=parseFloat(obj[i]);} avg/=(obj.length); return avg;}";

	//通用函数SUM
	public final static String FUN_SUM = "function def_sum(obj){var sum;for(var i=0;i<obj.length;i++) { if(def_isNum(obj[i])){sum+=parseFloat(obj[i]);} } return sum;}";

	//通用函数MAX
	public final static String FUN_MAX = "function def_max(obj){var maxVal = Math.max.apply(Math, obj);return maxVal;}";

	//通用函数MIN
	public final static String FUN_MIN = "function def_min(obj){var minVal = Math.min.apply(Math, obj);return minVal;}";

	//通用函数COUNT
	public final static String FUN_COUNT = "function def_count(obj){ return obj.length;}";

	//判断是否是数字
	public final static String FUN_IS_NUM = "function def_isNum(obj){  return (obj[i] == null || obj[i] == '' || isNaN(obj)); }";

	//获取最近XX个月的具体日期
	public final static String FUN_LAST_MONTH ="function def_lastMonth(day){var date=new Date();date.setMonth(date.getMonth()-day);return date.getTime()}";
	//" function def_lastMongth(day){ var date=new Date(); date.setMonth(date.getMonth() - day ); var res = date.getFullYear() + '-' + (date.getMonth()+1) + '-' + date.getDate(); return res; }";



	//event type

	public final static String EVENT_NAME_SIGNUP = "signUp";
	public final static String EVENT_NAME_UPDATEPROFILE = "updateProfile";
	public final static String EVENT_NAME_VIEWITEM = "viewItem";
	public final static String EVENT_NAME_UPDATECART = "updateCart";
	public final static String EVENT_NAME_PURCHASE = "purchase";
	public final static String EVENT_NAME_ORDERSHIPPED = "orderShipped";
	public final static String EVENT_NAME_SUBSCRIBE = "subscribe";
	public final static String EVENT_NAME_UNSUBSCRIBE = "unsubscribe";
	public final static String EVENT_NAME_EMAILOPEN = "emailOpen";
	public final static String EVENT_NAME_EMAILCLICK = "emailClick";
	public final static String EVENT_NAME_PUSHOPEN = "pushOpen";
	public final static String EVENT_NAME_PAGEVIEW = "PageView";
	public final static String EVENT_NAME_SMSCLICK = "smsclick";


	//流程
	public final static String FLOWCHART_TYPE_BATCH_FLOW = "BATCH_FLOW";

	public final static String FLOWCHART_TYPE_EVENT_FLOW = "EVENT_FLOW";
	//测试状态
	public final static String FLOWCHART_STATUS_TESTSTATUS = "1";
	//非测试状态
	public final static String FLOWCHART_STATUS_NON_TESTSTATUS = "0";
	//流程可执行状态
	public final static String FLOWCHART_FLOW_CAN_EXECUTE = "0";
	//流程执行结束状态
	public final static String FLOWCHART_FLOW_EXECUTE_FINISHED = "24";


	// 正则表达式

	// 匹配yyyy-MM-dd格式的字符串
	public final static String REGEX_FOR_DEFAULT_DATE_FORMAT = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

	// 匹配yyyy/MM/dd格式字符串
	public final static String REGEX_FOR_DEFAULT_SQUOTE_DATE_FORMAT = "(((0[1-9]|[12][0-9]|3[01])/((0[13578]|1[02]))|((0[1-9]|[12][0-9]|30)/(0[469]|11))|(0[1-9]|[1][0-9]|2[0-8])/(02))/([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}))|(29/02/(([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00)))";
	// 匹配yyyyMMdd格式字符串
	public final static String REGEX_FOR_DEFAULT_NOSQUOTE_DATE_FORMAT = "(((0[1-9]|[12][0-9]|3[01])((0[13578]|1[02]))|((0[1-9]|[12][0-9]|30)(0[469]|11))|(0[1-9]|[1][0-9]|2[0-8])(02))([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}))|(29/02/(([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00)))";

	// 匹配数字格式的字符串
	public static final String REG_FOR_NUMBER_INT = "(^[1-9]\\d+)|0";

	public static final String REGEX_FOR_USERDEFINED_PARAM = "%\\w+(:\\w+)*%";


	/*状态*/
	public static final String STATUS_ENABLE = "1";//启用
	public static final String STATUS_DISABLE = "0";//禁用

	/*接口状态HTTP*/
	public static final String RESPONSE_SUCCESS_CODE = "200";//正常
	public static final String RESPONSE_BAD_CODE = "500";//异常
}
