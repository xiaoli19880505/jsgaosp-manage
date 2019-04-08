/** 
 * 项目名称:91营销云
 * 文件名：VelocityUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.io.File;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.Resource;

public class VelocityUtils {
	public static void export(Resource templateFile, Map parameters, Writer writer) throws Exception {
		VelocityEngine ve = new VelocityEngine();
		File file = templateFile.getFile();
		String folder= file.getParent();
		String fileName = file.getName();
		Properties properties = new Properties();
		properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,folder);
		properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
		properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		
		try {
			ve.init(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Template t = ve.getTemplate(fileName,"UTF-8");

		VelocityContext context = new VelocityContext();
		for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
			Entry entry = (Entry)iter.next();
			context.put(entry.getKey().toString(), entry.getValue());
		}

		t.merge(context, writer);
	}

}
