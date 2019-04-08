/** 
 * 项目名称:91营销云
 * 文件名：OfferTmplStatic.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.core.log.Logger;
import com.britecloud.marketingcloud.core.log.LoggerFactory;
import com.britecloud.marketingcloud.model.CsvData;
import com.britecloud.marketingcloud.model.CsvDescription;

public class CsvParser {
    
    /**
     * 获取Csv文件的第一行字段的信息 包括：每一个字段的名称
     * 
     * @param filePath
     *            给定的文件名
     * @param charSet
     *            需要指定字符集 "UTF-8","GBK"等
     * @return
     *
     */
	private static final Logger logger = LoggerFactory.getLogger(CsvParser.class);
	
    public static String getCsvHeader(String filePath, String charSet) {

        try (
        		InputStreamReader inReader = new InputStreamReader(new FileInputStream(filePath), charSet);
                BufferedReader bufferReader = new BufferedReader(inReader);
        ) {
            return getCsvHeader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return JSON.toJSONString(new LinkedList<CsvDescription>()); // 返回空List的JSONString，而不返回null
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString(new LinkedList<CsvDescription>());// 返回空List的JSONString，而不返回null
        }
    }

    /**
     * 获取Csv文件的第一行字段的信息 包括：每一个字段的名称
     * 
     * @param bufferReader
     * @return
     * @throws IOException
     *
     */
    public static String getCsvHeader(String filePath) throws IOException {
    	
    	logger.info("filePath:"+filePath);
    	
        List<CsvDescription> list = new LinkedList<>();
        List<String> listLines = new LinkedList<>();
        InputStreamReader inReader = new InputStreamReader(new FileInputStream(filePath), "utf-8");
		BufferedReader bufferReader=new BufferedReader(inReader);
        String line = null;
        String[] cells = null;
        line = bufferReader.readLine();
        cells = line.split(",");// 目前只解析逗号做分割符的标准csv文件
        CsvDescription csvDescription = null;
        Map<Integer, String> map = new HashMap<>();
        int count = 0;
        while ((line = bufferReader.readLine()) != null && count < 100) {// 读取前100行数据
            listLines.add(line);
            count++;
        }
        predictDataType(listLines, map);
        int length = cells.length;
        for (int i = 0; i < length; ++i) {
            csvDescription = new CsvDescription();
            csvDescription.setDataFieldName(cells[i]);
            csvDescription.setDataFieldCode("");
            csvDescription.setDataFieldType(map.get(i));
            list.add(csvDescription);
        }
        if(inReader!=null){
        	inReader.close();
        }
        return JSON.toJSONString(list);

    }

    /**
     * 数据 数据类型
     * 
     * @param lineList
     * @return
     *
     */
    private static void predictDataType(List<String> lineList, Map<Integer, String> map) {
        List<Map<String, Integer>> list = new LinkedList<>();
        Map<String, Integer> temp = null;
        for (Iterator<String> it = lineList.iterator(); it.hasNext();) {
            String line = it.next();
            String[] cells = line.split(",");
            int length = cells.length;
            for (int i = 0; i < length; ++i) {
                String type = getDateType(cells[i]);
                if (list.size() < length) {
                    temp = new HashMap<>();
                    temp.put(type, 1);
                    list.add(temp);
                } else {
                    temp = list.get(i);
                    Integer count = temp.get(type);
                    if (count == null) {
                        count = 0;
                    }
                    count++;
                    temp.put(type, count);
                }
            }
        }
        int counter = 0;
        for (Iterator<Map<String, Integer>> it = list.iterator(); it.hasNext(); counter++) {
            Map<String, Integer> typeCount = it.next();
            System.out.println(typeCount);
            map.put(counter, calcDataType(typeCount));

        }

    }

    public static String updateDataType(String src, String des) {// 更新数据类型,原则是非String转换为String,String的保持不变
        if (!"String".equals(src) && "String".equals(des)) {
            return des;
        } else {
            return src;
        }
    }

    private static String getDateType(String str) {
        if (str.matches(Constants.REG_FOR_NUMBER_INT)) {
            return "NumberInt";
        } else if (str.matches(Constants.REGEX_FOR_DEFAULT_DATE_FORMAT)) {
            return "Date";
        } else if (str.matches(Constants.REGEX_FOR_DEFAULT_SQUOTE_DATE_FORMAT)) {
            return "Date";
        } else if (str.matches(Constants.REGEX_FOR_DEFAULT_NOSQUOTE_DATE_FORMAT)) {
            return "Date";
        } else {
            return "String";
        }
    }

    public static String getSubData(String filePath, String charSet, String typeJson, Integer count) {
        try (InputStreamReader inReader = new InputStreamReader(new FileInputStream(filePath), charSet);
                BufferedReader bufferReader = new BufferedReader(inReader);) {

            return getSubData(bufferReader, typeJson, count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return JSON.toJSONString(new CsvData());// 返回空的串，而不返回null;
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString(new CsvData());// 返回空的串，而不返回null;
        }

    }

    @SuppressWarnings("rawtypes")
    public static String getSubData(BufferedReader bufferReader, String typeJson, Integer count) throws IOException {
        List listType = JSON.parseObject(typeJson, List.class);
        CsvData data = new CsvData();
        List<CsvDescription> listDes = new LinkedList<>();
        Map<String, String> header = new LinkedHashMap<>();
        for (Iterator it = listType.iterator(); it.hasNext();) {
            listDes.add(JSON.parseObject(it.next().toString(), CsvDescription.class));
        }

        for (Iterator<CsvDescription> it = listDes.iterator(); it.hasNext();) {
            CsvDescription csv = it.next();
            header.put(csv.getDataFieldCode(), csv.getDataFieldName());
        }
        data.setHeader(header);
        if (count == null) {
            return JSON.toJSONString(data);

        }
        String line = null;
        String[] cellValues = null;
        line = bufferReader.readLine();
        List<JSONObject> list = new LinkedList<>();
        JSONObject obj = null;
        int lineCount = 0;
        while ((line = bufferReader.readLine()) != null && lineCount < count) {
            cellValues = line.split(",");
            obj = new JSONObject();
            int number = 0;
            for (Iterator<String> it = header.keySet().iterator(); it.hasNext(); number++) {
                String key = it.next();
                obj.put(key, cellValues[number]);
            }
            lineCount++;
            list.add(obj);
        }
        data.setData(list);
        return JSON.toJSONString(data);
    }

    /**
     * 将指定 的Csv文件转换成为JSON字符串的列表
     * 
     * @param filePath
     * @return
     *
     */
    public static List<JSONObject> parseJSON(String filePath, String charSet, String typeJson) {

        try (InputStreamReader inReader = new InputStreamReader(new FileInputStream(filePath), charSet);
                BufferedReader bufferReader = new BufferedReader(inReader);) {
            return parseJSON(bufferReader, typeJson);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new LinkedList<JSONObject>();// 返回空的List，而不返回null;
        } catch (IOException e) {
            e.printStackTrace();
            return new LinkedList<JSONObject>();// 返回空的List，而不返回null;
        }
    }

    private static String calcDataType(Map<String, Integer> map) {
        String max = "";
        Iterator<String> it = map.keySet().iterator();
        if (it.hasNext()) {
            max = it.next();
        }
        Integer maxvalue = map.get(max);
        while (it.hasNext()) {
            String key = it.next();
            Integer value = map.get(key);
            if (value > maxvalue) {
                max = key;
                maxvalue = value;
            }
        }
        return max;

    }

    public static List<JSONObject> parseJSON(BufferedReader bufferReader, String typeJson) throws IOException {
        @SuppressWarnings("rawtypes")
        List listType = JSON.parseObject(typeJson, List.class);
        List<CsvDescription> listDes = new LinkedList<>();
        for (@SuppressWarnings("rawtypes")
        Iterator it = listType.iterator(); it.hasNext();) {
            listDes.add(JSON.parseObject(it.next().toString(), CsvDescription.class));
        }
        String line = null;
        String[] cellValues = null;
        line = bufferReader.readLine();
        // cellFields = line.split(",");// 直接使用
        List<JSONObject> list = new LinkedList<>();
        JSONObject obj = null;
        while ((line = bufferReader.readLine()) != null) {
            cellValues = line.split(",");
            obj = new JSONObject();
            int size;
            if ((size = listDes.size()) != cellValues.length) {
                size = cellValues.length;
            }
            for (int i = 0; i < size; ++i) {
                obj.put(listDes.get(i).getDataFieldCode(),
                        generateValue(cellValues[i], listDes.get(i).getDataFieldType()));// TODO此处还需指定类型
            }
            list.add(obj);
        }
        return list;
    }

    @SuppressWarnings("deprecation")
    private static String generateValue(String value, String type) {
        switch (type) {
        case "String":
            return value;
        case "Date":
            if (value.matches("[0-2]\\d{3,3}-((1[0-2])|(0[1-9]))-(([1-2]\\d)|(0[1-9])|(3[0-1]))(\\s+(\\d{2,2})+)*")) {
                value = value.replaceAll("-", "/");
            }
            return "NumberInt(" + Date.parse(value) + ")";
        default:
            return type + "(" + value + ")";
        }

    }

}
