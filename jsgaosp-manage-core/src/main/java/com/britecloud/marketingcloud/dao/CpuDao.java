package com.britecloud.marketingcloud.dao;

import java.util.List;

import com.britecloud.marketingcloud.model.Cpu;

public interface CpuDao {
	public void addCpu(Cpu cpu);
	public List<?> getCpuRunningInfo();
}
