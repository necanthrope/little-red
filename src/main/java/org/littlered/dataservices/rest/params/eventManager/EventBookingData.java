package org.littlered.dataservices.rest.params.eventManager;

/**
 * Created by Jeremy on 4/23/2018.
 */
public class EventBookingData {

	private Long eventId;
	private Long userId;
	private Boolean isGm;
	private String status;
	private String message;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public Boolean getIsGm() {
		return isGm;
	}

	public void setIsGm(Boolean gm) {
		isGm = gm;
	}

	@Override
	public String toString() {
		return "EventBookingData{" +
				"eventId=" + eventId +
				", userId=" + userId +
				", isGm=" + isGm +
				", status='" + status + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
