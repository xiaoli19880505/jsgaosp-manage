package com.britecloud.marketingcloud.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;



import com.britecloud.marketingcloud.core.log.Logger;
import com.britecloud.marketingcloud.core.log.LoggerFactory;

/**
 * 该工具类只适用于Linux平台
 * @author yh
 *   
 */
public class ProcessUtilsForLinux {
	private Logger logger = LoggerFactory.getLogger(ProcessUtilsForLinux.class);
	
	private String startupFilePath="./model_daemon.sh"; //用于启动进程的shell脚本，参见model_daemon.sh
	private static  ProcessUtilsForLinux procUtils = null;
	
	public static ProcessUtilsForLinux getInstance(){
		if(procUtils == null){
			procUtils = new ProcessUtilsForLinux();
		}
		return procUtils;
	}
	
	
	public String getProcessOutput(Process proc){
		InputStream is = proc.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String output = "";
		 try {
	            String line = reader.readLine();
	            while (line != null) {
	            	output += line;
	                line = reader.readLine();
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return output;
	}
	
	/**
	 * 创建子进程
	 * @param cmdArgs 命令参数
	 * @return 进程PID
	 * 
	 */
	public int forkProcess(String cmdArgs){
		
		if(StringUtils.isEmpty(startupFilePath)){
			logger.error("Please set startupFilePath first!");
			return -1;
		}
		
		String runCmd = startupFilePath + " start " + cmdArgs;
		logger.info(runCmd);
		int pid=-1;
		try {
			Process proc = Runtime.getRuntime().exec(runCmd);
			String pidOutput = getProcessOutput(proc);
			logger.info("pid = " + pidOutput);
			//if(StringUtils.isNumeric(pidOutput)){
			//pid =  Integer.parseInt(pidOutput);
			//}
			proc.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}	
		return pid;
	}
	
	/**
	 * 停止进程
	 * 
	 * @param pid 进程PID
	 * @return
	 */
	public int stopProcess(int pid){
		if(StringUtils.isEmpty(startupFilePath)){
			logger.error("Please set startupFilePath first!");
			return -1;
		}
		String pidCmd = startupFilePath + " stop " + pid;
		logger.info(pidCmd);
		try {
			Process proc = Runtime.getRuntime().exec(pidCmd);
			proc.waitFor();
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	public boolean isRunning(int pid){
		
		  
		if(StringUtils.isEmpty(startupFilePath)){
			logger.error("Please set startupFilePath first!");
			return false;
		}
		
		String pidCmd = startupFilePath + " status " + pid;
		logger.info(pidCmd);
		try {
			Process proc = Runtime.getRuntime().exec(pidCmd);
			String pidOutput = getProcessOutput(proc);
			
			if(StringUtils.contains(pidOutput, String.valueOf(pid))){
				return true;
			}
			proc.waitFor(); 
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public String getStartupFilePath() {
		return startupFilePath;
	}

	public void setStartupFilePath(String startupFilePath) {
		this.startupFilePath = startupFilePath;
	}
}
