/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   GroupUtils.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GroupUtils {
	public static List<?> toGroup(List<?> groupList,List msgList){
		List result = new ArrayList();
		for(Iterator<?> it = groupList.iterator();it.hasNext();){
			Map tmp = (Map)it.next();
			Map resMap = new HashMap();
			String channelType = (String)tmp.get("CHANNEL_TYPE");
			String channelTypeName = (String)tmp.get("CHANNEL_TYPE_NAME");
			List res = getMessageByChannelType(msgList,channelType);
			resMap.put("channel_type", channelType);
			resMap.put("channel_type_name",channelTypeName);
			resMap.put("count", res.size());
			resMap.put("list", res);
			result.add(resMap);
		}
		return result;
	}
	
	public static List<?> getMessageByChannelType(List msgList,String channelType){
		List res = new ArrayList();
		for(Iterator it = msgList.iterator();it.hasNext();){
			Map tmp = (Map)it.next();
			String channel = (String)tmp.get("CHANNEL_TYPE");
			if(channelType.equals((String)tmp.get("CHANNEL_TYPE"))){
				res.add(tmp);
			}
		}
		return res;
	}
}
