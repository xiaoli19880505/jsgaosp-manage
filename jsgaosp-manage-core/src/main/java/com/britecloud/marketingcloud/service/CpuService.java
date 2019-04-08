package com.britecloud.marketingcloud.service;

import java.util.List;

import com.britecloud.marketingcloud.model.Cpu;

public interface CpuService {
	public void addCpu(Cpu cpu);
	public List<?> getCpuRunningInfo();
}
