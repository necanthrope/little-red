package org.littlered.dataservices.entity.wordpress.shrt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bbc_event_categories")
public class BbcEventCategories implements Serializable {
	private long eventId;
	private long postId;
	private String name;
	private String slug;

	@Basic
	@Column(name = "post_id")
	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BbcEventCategories that = (BbcEventCategories) o;
		return postId == that.postId &&
				Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(postId, name);
	}

	@Id
	@Column(name = "slug")
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	@Id
	@Column(name = "event_id")
	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
}
