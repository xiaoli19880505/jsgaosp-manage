/** 
 * 项目名称:91营销云
 * 文件名：Transfer.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Transfer {

	private List<String> result = new ArrayList<String>();
	private Stack<String> stack = new Stack<String>();

	//���б�˳��ߵ�
	public List<String> Reverse(List<String> list){
		ArrayList<String> list2 = new ArrayList<String>();
		for(int i=list.size()-1;i>=0;i--){
			list2.add(list.get(i));
		}
		return list2;
	}
	//���ַ���ת���б�
	public List<String> StringToList(String str){
		List<String> list = Arrays.asList(str.split(";"));
		list = this.Reverse(list);
		return list;
	}
	//���������ǰ��ļ���
	public List<String> Cal(String str){
		List<String> list = this.StringToList(str);
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals("and") || list.get(i).equals("or") || list.get(i).equals("in") || list.get(i).equals("not in")){
				if(stack.isEmpty()){
					stack.push(list.get(i));
					continue;
				}else if(stack.peek().equals(")") || stack.peek().equals("and") || stack.peek().equals("or") 
						|| stack.peek().equals("in") || stack.peek().equals("not in")){
					stack.push(list.get(i));
					continue;
				}else{
					result.add(stack.pop());
				}
			}else if(list.get(i).equals("(")){
				while(!stack.peek().equals(")")){
					result.add(stack.pop());
				}
				stack.pop();
			}else if(list.get(i).equals(")")){
				stack.push(list.get(i));
			}else{
				result.add(list.get(i));
			}
		}
		while(!stack.empty()){
			result.add(stack.pop());
		}
		result = this.Reverse(result);
		return result;
	}

	public String transf(String str){
		try {
			return this.C(this.Cal(str));
		} catch (Exception e) {
			return "该表达式错误";
		}
	}
	public String C(List<String> list) throws Exception{
		Stack<String> s = new Stack<String>();
		int count=0;
		String result="",temp1="",temp2="",operator="";
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals("and") || list.get(i).equals("or") || list.get(i).equals("in")|| list.get(i).equals("not in")){
				s.push(list.get(i));
				count=0;
			}else{
				s.push(list.get(i));
				count++;
				if(count==2){
					temp2=s.pop();
					temp1=s.pop();
					operator=s.pop();
					count=1;
					if(operator.equals("or")){
						operator="'$or':";
					}else if(operator.equals("and")){
						operator="'$and':";
					}else if(operator.equals("in")){
						operator="'$in':";
					}else if(operator.equals("not in")){
						operator="'$nin':";
					}
					result="{"+operator+"["+temp1+","+temp2+"]"+"}";
					list.remove(i);
					list.remove(i-1);
					list.set(i-2, result);
					i-=2;
					if(list.size()!=1){
						this.C(list);
					}
				}
			}
		}
		return list.get(0);
	}

}
