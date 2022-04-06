package org.littlered.dataservices.entity.wordpress;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
//@Table(name = "postmeta")
public class Postmeta implements Serializable {
	private long metaId;
	private long postId;
	private String metaKey;
	private String metaValue;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "meta_id", nullable = false)
	public long getMetaId() {
		return metaId;
	}

	public void setMetaId(long metaId) {
		this.metaId = metaId;
	}

	@Basic
	@Column(name = "post_id", nullable = false)
	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	@Basic
	@Column(name = "meta_key", nullable = true, length = 255)
	public String getMetaKey() {
		return metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	@Basic
	@Lob
	@Column(name = "meta_value", nullable = true, length = -1)
	public String getMetaValue() {
		return metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Postmeta that = (Postmeta) o;
		return metaId == that.metaId &&
				postId == that.postId &&
				Objects.equals(metaKey, that.metaKey) &&
				Objects.equals(metaValue, that.metaValue);
	}

	@Override
	public int hashCode() {

		return Objects.hash(metaId, postId, metaKey, metaValue);
	}


}
