/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   MessageUtils.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MessageUtils {
	private static final String HTML_START="<div class=\"row\">";
	private static final String HTML_DIV_START="<div class=\"col-md-12\">";
	private static final String HTML_DIV_END="</div>";
	private static final String HTML_END = "</div>";
	
	public static String mergeHTML(List msgBlocks){
		StringBuffer sb = new StringBuffer();
		sb.append(HTML_START);
		sb.append(HTML_DIV_START);
		for(Iterator it = msgBlocks.iterator();it.hasNext();){
			Map map = (Map)it.next();
			String html = (String)map.get("HTML");
			sb.append(html);
		}
		sb.append(HTML_END);
		sb.append(HTML_DIV_END);
		System.out.println(sb.toString());
		return sb.toString();
	}
}
