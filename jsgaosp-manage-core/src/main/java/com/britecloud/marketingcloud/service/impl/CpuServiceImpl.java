package com.britecloud.marketingcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.CpuDao;
import com.britecloud.marketingcloud.model.Cpu;
import com.britecloud.marketingcloud.service.CpuService;

@Service
public class CpuServiceImpl implements CpuService{

	@Autowired
	private CpuDao cpuDao; 
	
	@Override
	public void addCpu(Cpu cpu) {
		cpuDao.addCpu(cpu);
	}

	@Override
	public List<?> getCpuRunningInfo() {
		return cpuDao.getCpuRunningInfo();
	}

}
