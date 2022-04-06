package org.littlered.dataservices.rest.params.eventManager;

/**
 * Created by Jeremy on 4/23/2018.
 */
public class EventAndUser {

	private Long eventId;
	private Long userId;
	private String status;

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

	@Override
	public String toString() {
		return "EventBookingData{" +
				"eventId=" + eventId +
				", userId=" + userId +
				", status='" + status + '\'' +
				'}';
	}
}
