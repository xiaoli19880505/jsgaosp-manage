package com.britecloud.marketingcloud.executor.zmq;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.zeromq.ZMQ;

import com.britecloud.marketingcloud.model.Cpu;
import com.britecloud.marketingcloud.service.CpuService;

public class MonitorReceiveHandler {
	
	@Autowired
	public CpuService cpuService;
	
	//这个表示创建用于一个I/O线程的context
	public ZMQ.Context context = ZMQ.context(1);
	//创建一个response类型的socket，他可以接收request发送过来的请求，其实可以将其简单的理解为服务端
	public ZMQ.Socket socket = context.socket(ZMQ.REP);
	
	public MonitorReceiveHandler() {
		socket.bind ("tcp://*:5555");    //绑定端口
	}
	
	public void receive() {
		while (!Thread.currentThread().isInterrupted()) {
			//获取request发送过来的数据
			byte[] request = socket.recv(); 
			System.out.println("receive : " + new String(request));
			saveMonitorData(new String(request));
			String response = "<response><code>200</code><message>success</message></response>";
			//向request端发送数据  ，必须要要request端返回数据，没有返回就又recv，将会出错，这里可以理解为强制要求走完整个request/response流程
			socket.send(response.getBytes());
		}
	}
	
	public void destory() {
		socket.close();  //先关闭socket
		context.term();  //关闭当前的上下文
	}
	
	public void saveMonitorData(String data) {
		//--cpu:3643951,1111970,1603,0,144750,37293920,61208,0,0
		String[] result = data.split("\\|");
		String[] cpuData = result[2].split(",");
		Cpu cpu = new Cpu();
		cpu.setGuest(new BigDecimal(cpuData[1]));
		cpu.setHirq(new BigDecimal(cpuData[2]));
		cpu.setHost_name(result[1]);
		cpu.setNcpu(new BigDecimal(cpuData[3]));
		cpu.setNice(new BigDecimal(cpuData[4]));
		cpu.setSirq(new BigDecimal(cpuData[5]));
		cpu.setSteal(new BigDecimal(cpuData[6]));
		cpu.setSys(new BigDecimal(cpuData[7]));
		cpu.setTime(1);
		cpu.setUser(new BigDecimal(cpuData[8]));
		cpu.setUtil(new BigDecimal(cpuData[1]));
		cpu.setView_time(new Date());
		
		String[] tmp = cpuData[0].split(":");
		cpu.setWait(new BigDecimal(tmp[1]));
		cpuService.addCpu(cpu);
	}
}
