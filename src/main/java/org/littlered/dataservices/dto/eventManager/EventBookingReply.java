package org.littlered.dataservices.dto.eventManager;

/**
 * Created by Jeremy on 4/29/2018.
 */
public class EventBookingReply {

	private String status;
	private String message;
	private String guid;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}
