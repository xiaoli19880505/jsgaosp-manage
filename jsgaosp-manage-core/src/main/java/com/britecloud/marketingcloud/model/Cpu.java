package com.britecloud.marketingcloud.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Cpu {
	public String host_name;
	public int time;
	public BigDecimal user;
	public BigDecimal sys;
	public BigDecimal wait;
	public BigDecimal hirq;
	public BigDecimal sirq;
	public BigDecimal util;
	public BigDecimal nice;
	public BigDecimal steal;
	public BigDecimal guest;
	public BigDecimal ncpu;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Date  view_time;
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
	public BigDecimal getUser() {
		return user;
	}
	public void setUser(BigDecimal user) {
		this.user = user;
	}
	public BigDecimal getSys() {
		return sys;
	}
	public void setSys(BigDecimal sys) {
		this.sys = sys;
	}
	public BigDecimal getWait() {
		return wait;
	}
	public void setWait(BigDecimal wait) {
		this.wait = wait;
	}
	public BigDecimal getHirq() {
		return hirq;
	}
	public void setHirq(BigDecimal hirq) {
		this.hirq = hirq;
	}
	public BigDecimal getSirq() {
		return sirq;
	}
	public void setSirq(BigDecimal sirq) {
		this.sirq = sirq;
	}
	public BigDecimal getUtil() {
		return util;
	}
	public void setUtil(BigDecimal util) {
		this.util = util;
	}
	public BigDecimal getNice() {
		return nice;
	}
	public void setNice(BigDecimal nice) {
		this.nice = nice;
	}
	public BigDecimal getSteal() {
		return steal;
	}
	public void setSteal(BigDecimal steal) {
		this.steal = steal;
	}
	public BigDecimal getGuest() {
		return guest;
	}
	public void setGuest(BigDecimal guest) {
		this.guest = guest;
	}
	public BigDecimal getNcpu() {
		return ncpu;
	}
	public void setNcpu(BigDecimal ncpu) {
		this.ncpu = ncpu;
	}
	public Date getView_time() {
		return view_time;
	}
	public void setView_time(Date view_time) {
		this.view_time = view_time;
	}
	
	
	
}
