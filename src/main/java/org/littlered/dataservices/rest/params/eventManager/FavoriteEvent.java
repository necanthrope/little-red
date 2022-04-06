package org.littlered.dataservices.rest.params.eventManager;

/**
 * Created by Jeremy on 4/23/2018.
 */
public class FavoriteEvent {

	private Long eventId;
	private String status;
	private String message;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
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
				", status='" + status + '\'' +
				", message='" + message + '\'' +
				'}';
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
