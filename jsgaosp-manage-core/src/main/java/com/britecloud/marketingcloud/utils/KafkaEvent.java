package com.britecloud.marketingcloud.utils;

public class KafkaEvent {
	private String eventType;  //事件类型
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventJson() {
		return eventJson;
	}

	public void setEventJson(String eventJson) {
		this.eventJson = eventJson;
	}

	private String eventJson; //事件内容，json格式
}
