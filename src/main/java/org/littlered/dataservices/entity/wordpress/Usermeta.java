package org.littlered.dataservices.entity.wordpress;

import javax.persistence.*;

/**
 * Created by Jeremy on 7/1/2017.
 */
@Entity
//@Table(name = "usermeta")
public class Usermeta {
	private long umetaId;
	private String metaKey;
	private String metaValue;
	private Long userId;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "umeta_id")
	public long getUmetaId() {
		return umetaId;
	}

	public void setUmetaId(long umetaId) {
		this.umetaId = umetaId;
	}

	@Basic
	@Column(name = "meta_key")
	public String getMetaKey() {
		return metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	@Basic
	@Lob
	@Column(name = "meta_value")
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

		Usermeta that = (Usermeta) o;

		if (umetaId != that.umetaId) return false;
		if (metaKey != null ? !metaKey.equals(that.metaKey) : that.metaKey != null) return false;
		if (metaValue != null ? !metaValue.equals(that.metaValue) : that.metaValue != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (umetaId ^ (umetaId >>> 32));
		result = 31 * result + (metaKey != null ? metaKey.hashCode() : 0);
		result = 31 * result + (metaValue != null ? metaValue.hashCode() : 0);
		return result;
	}

	@Basic
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
