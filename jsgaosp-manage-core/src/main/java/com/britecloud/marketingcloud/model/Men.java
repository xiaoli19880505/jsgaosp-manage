package com.britecloud.marketingcloud.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Men {
	public String host_name;
	public int time;
	public long free;
	public long used;
	public long buff;
	public long cach;
	public long total;
	public long util;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Date view_time;
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public long getFree() {
		return free;
	}
	public void setFree(long free) {
		this.free = free;
	}
	public long getUsed() {
		return used;
	}
	public void setUsed(long used) {
		this.used = used;
	}
	public long getBuff() {
		return buff;
	}
	public void setBuff(long buff) {
		this.buff = buff;
	}
	public long getCach() {
		return cach;
	}
	public void setCach(long cach) {
		this.cach = cach;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getUtil() {
		return util;
	}
	public void setUtil(long util) {
		this.util = util;
	}
	public Date getView_time() {
		return view_time;
	}
	public void setView_time(Date view_time) {
		this.view_time = view_time;
	}
}
