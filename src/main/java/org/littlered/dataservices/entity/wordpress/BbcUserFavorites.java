package org.littlered.dataservices.entity.wordpress;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bbc_user_favorites")
public class BbcUserFavorites {
	private Long bbcUserFavoritesId;
	private Long userId;
	private Long eventId;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Short status;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "bbc_user_favorites_id", nullable = false)
	public Long getBbcUserFavoritesId() {
		return bbcUserFavoritesId;
	}

	public void setBbcUserFavoritesId(Long bbcUserFavoritesId) {
		this.bbcUserFavoritesId = bbcUserFavoritesId;
	}

	@Basic
	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Basic
	@Column(name = "event_id")
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	@Basic
	@Column(name = "create_date")
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Basic
	@Column(name = "update_date")
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Basic
	@Column(name = "status")
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BbcUserFavorites that = (BbcUserFavorites) o;
		return bbcUserFavoritesId == that.bbcUserFavoritesId &&
				userId == that.userId &&
				eventId == that.eventId &&
				Objects.equals(createDate, that.createDate) &&
				Objects.equals(updateDate, that.updateDate) &&
				Objects.equals(status, that.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bbcUserFavoritesId, userId, eventId, createDate, updateDate, status);
	}
}
