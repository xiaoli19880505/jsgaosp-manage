package com.britecloud.marketingcloud.model.api;





public class UpdateSubscriptionsRequest {
	private String userId;
	private String unsubscribedChannelIds;
	private String unsubscribedMessageTypeIds;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUnsubscribedChannelIds() {
		return unsubscribedChannelIds;
	}

	public void setUnsubscribedChannelIds(String unsubscribedChannelIds) {
		this.unsubscribedChannelIds = unsubscribedChannelIds;
	}


	public String getUnsubscribedMessageTypeIds() {
		return unsubscribedMessageTypeIds;
	}

	public void setUnsubscribedMessageTypeIds(String unsubscribedMessageTypeIds) {
		this.unsubscribedMessageTypeIds = unsubscribedMessageTypeIds;
	}

}
