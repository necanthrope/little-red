package org.littlered.dataservices.entity.wordpress;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bbc_event_view")
public class BbcEventView implements Serializable {
	private long id;
	private String displayName;
	private String userEmail;
	private Long eventId;
	private String eventName;
	private Timestamp startTime;
	private Timestamp endTime;

	@Id
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Basic
	@Column(name = "user_email")
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Id
	@Column(name = "event_id")
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	@Basic
	@Column(name = "event_name", columnDefinition = "TEXT")
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Basic
	@Column(name = "start_time")
	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Basic
	@Column(name = "end_time")
	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BbcEventView that = (BbcEventView) o;
		return id == that.id &&
				Objects.equals(displayName, that.displayName) &&
				Objects.equals(userEmail, that.userEmail) &&
				Objects.equals(eventId, that.eventId) &&
				Objects.equals(eventName, that.eventName) &&
				Objects.equals(startTime, that.startTime) &&
				Objects.equals(endTime, that.endTime);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, displayName, userEmail, eventId, eventName, startTime, endTime);
	}
}
