package com.britecloud.marketingcloud.console.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.britecloud.marketingcloud.model.Cpu;
import com.britecloud.marketingcloud.service.CpuService;
import com.britecloud.marketingcloud.utils.DateUtils;

@Controller
@RequestMapping("/dashboard")
public class DashboardAct {

	@Autowired
	private CpuService cpuService;
	
	@RequestMapping(value = "/getCpuSirq", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> getCpuSirq(HttpServletRequest request) throws Exception {
		Map<String, Integer> result = new HashMap<String, Integer>();
		int num = (int)(Math.random() * 100);
		result.put("sirq", num);
		return result;
	}
	
	@RequestMapping(value = "/getCpuInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getCpuRunningInfo(HttpServletRequest request) throws Exception {
		Map result = new HashMap();
		List<?> list = cpuService.getCpuRunningInfo();
		
		List timeVal = new ArrayList();
		List dataVal1 = new ArrayList();
		List dataVal2 = new ArrayList();;		        	  
		List dataVal3 = new ArrayList();
		Iterator<?> it = list.iterator();
		while(it.hasNext()) {
			Cpu cpu = (Cpu)it.next();
			timeVal.add(DateUtils.toString(cpu.getView_time(), DateUtils.DEFAULT_TIME_FORMAT));
			dataVal1.add(cpu.getSirq().doubleValue()/10000);
			dataVal2.add(cpu.getHirq().doubleValue());
			dataVal3.add(cpu.getGuest().doubleValue()/200);
		}
			
		
		result.put("dataTimeList", timeVal.toArray());
		result.put("dataValue1", dataVal1.toArray());
		result.put("dataVelue2", dataVal2.toArray());
		result.put("dataVelue3", dataVal3.toArray());
		
		return result;
	}
}
