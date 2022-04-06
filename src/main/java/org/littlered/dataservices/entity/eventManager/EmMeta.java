package org.littlered.dataservices.entity.eventManager;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Jeremy on 3/19/2017.
 */
@Entity
//@Table(name = "em_meta")
public class EmMeta {
	private long metaId;
	private long objectId;
	private String metaKey;
	private String metaValue;
	private Timestamp metaDate;

	@Id
	@Column(name = "meta_id")
	public long getMetaId() {
		return metaId;
	}

	public void setMetaId(long metaId) {
		this.metaId = metaId;
	}

	@Basic
	@Column(name = "object_id")
	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
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

	@Basic
	@Column(name = "meta_date")
	public Timestamp getMetaDate() {
		return metaDate;
	}

	public void setMetaDate(Timestamp metaDate) {
		this.metaDate = metaDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EmMeta that = (EmMeta) o;

		if (metaId != that.metaId) return false;
		if (objectId != that.objectId) return false;
		if (metaKey != null ? !metaKey.equals(that.metaKey) : that.metaKey != null) return false;
		if (metaValue != null ? !metaValue.equals(that.metaValue) : that.metaValue != null) return false;
		if (metaDate != null ? !metaDate.equals(that.metaDate) : that.metaDate != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (metaId ^ (metaId >>> 32));
		result = 31 * result + (int) (objectId ^ (objectId >>> 32));
		result = 31 * result + (metaKey != null ? metaKey.hashCode() : 0);
		result = 31 * result + (metaValue != null ? metaValue.hashCode() : 0);
		result = 31 * result + (metaDate != null ? metaDate.hashCode() : 0);
		return result;
	}
}
