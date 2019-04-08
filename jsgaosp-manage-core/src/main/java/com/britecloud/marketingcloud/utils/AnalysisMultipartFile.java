/** 
 * 项目名称:91营销云
 * 文件名：AnalysisMultipartFile.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class AnalysisMultipartFile {

	private static Logger logger = Logger.getLogger(AnalysisMultipartFile.class);

	public static boolean analysis(MultipartFile file,DBCollection collection,List<String > dataSourceCode) throws IOException{
		boolean bool = true;
		if(file.getOriginalFilename().endsWith(".zip")){
			//创建临时压缩文件
			File zipFile = File.createTempFile("unzip", ".zip");
			//将上传的压缩文件写到临时压缩文件中
			BufferedInputStream bufIs = null;
			BufferedOutputStream bufOs = null;
			try {
				bufIs = new BufferedInputStream(file.getInputStream());
				bufOs = new BufferedOutputStream(new FileOutputStream(zipFile));
				Long length=file.getSize();
				byte[] buf = new byte[length.intValue()];
				int len = 0;
				while ((len = bufIs.read(buf, 0, buf.length)) > 0) {
					bufOs.write(buf, 0, len);
				}
			} catch (IOException ex) {
				throw ex;
			} finally {
				bufOs.close();
				bufIs.close();
			}
			//创建解压后的目录
			String unzipPath = zipFile.getParent()+"/unzip";
			File unzip = new File(unzipPath);
			if(!unzip.exists()){
				unzip.mkdirs();
			}
			//将压缩文件解压到解压目录
			ZipFileUtils.unZipFiles(zipFile.getAbsolutePath(), unzip.getAbsolutePath());
			//将压缩文件删除
			zipFile.delete();
			//过滤，只保留以.json为结尾的文件
			File[] files=FileUtils.listAllFiles(unzip, new JsonFileFilter());
			FileInputStream fis=null;
		
			for(int i=0;i<files.length;i++){
				//如果是json文件
				if(files[i].isFile()&&files[i].getName().endsWith(".json")){
					File tmpFile = files[i];
					fis = new FileInputStream(tmpFile);
					bool=insertData(fis,collection,dataSourceCode);
					if(bool==false){
						return false;
					}
				}
			}
			if(fis!=null){
				fis.close();
			}
			deleteFile(unzip);
			return bool;
		}else if(file.getOriginalFilename().endsWith(".json")){//如果上传的文件是单个json文件
			 bool=insertData((FileInputStream)file.getInputStream(),collection,dataSourceCode);
		}
		return bool;
	}

	public static boolean insertData(FileInputStream in,DBCollection collection,List<String > dataSourceCode){
		List<DBObject> listFile = new ArrayList<DBObject>();
		BufferedReader br=null;
		try {
			br = new BufferedReader(new InputStreamReader(in,"gbk"));
			String temp="";
			JSONObject json=null;
			DBObject object = null;
			while((temp=br.readLine())!=null){
				try{
					json=JSON.parseObject(temp);
				}catch(Exception e){
					logger.info("数据解析错误："+temp);
					return false;
				}finally{
					
				}
				//如果数据中没有UserID,则不给导入，并打印在log
				if(("").equals(json.get("usreId"))||json.get("usreId")==null){
					logger.info("未能成功导入用户数据："+json.toString());
				}else{
					object = new BasicDBObject();
					//查询mysql数据库中的数据源字段,对导入的数据进行匹配
					for(String str:dataSourceCode){
						object.put(str, json.get(str));
					}
					object.put("usreId", json.get("usreId").toString());
					listFile.add(object);
					//一次插入1000条数据
					if(listFile.size()%1000==0 && listFile.size()!=0){
						try{
							collection.insert(listFile);
						}catch(Exception e){
							logger.info("数据存在错误：");
							for(Object obj :listFile){
								logger.info(obj.toString());
							}
							return false;
						}finally{
							listFile.clear();
							
						}

					}
				}
			}
			//将剩余未导入的数据导入
			if(listFile.size()!=0){
				try{
					collection.insert(listFile);
				}catch(Exception e){
					for(Object obj :listFile){
						logger.info(obj.toString());
					}
					return false;
				}finally{
					listFile.clear();
				}
			}
			//关闭流
			if(in!=null){
				in.close();
			}
			if(br!=null){
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

		}
		return true;
	}


	public static  void deleteFile(File file){ 
		System.out.println(file.getAbsolutePath());
		if(file.isDirectory()){              //否则如果它是一个目录
			File files[] = file.listFiles();               //声明目录下所有的文件 files[];
			for(int i=0;i<files.length;i++){            //遍历目录下所有的文件
				deleteFile(files[i]);             //把每个文件 用这个方法进行迭代
			} 
		}else{
			file.delete();
		} 
		file.delete(); 
	} 
}
//文件拦截器,json文件
class JsonFileFilter implements FileFilter{
	@Override
	public boolean accept(File file) {
		if(file.isDirectory())  
			return true;  
		else  
		{  
			String name = file.getName();  
			if(name.endsWith(".json"))  
				return true;  
			else  
				return false;  
		}  
	}  
}


