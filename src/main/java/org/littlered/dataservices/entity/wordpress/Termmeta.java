package org.littlered.dataservices.entity.wordpress;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name = "termmeta")
public class Termmeta {
	private long metaId;
	private long termId;
	private String metaKey;
	private String metaValue;

	@Id
	@Column(name = "meta_id")
	public long getMetaId() {
		return metaId;
	}

	public void setMetaId(long metaId) {
		this.metaId = metaId;
	}

	@Basic
	@Column(name = "term_id")
	public long getTermId() {
		return termId;
	}

	public void setTermId(long termId) {
		this.termId = termId;
	}

	@Basic
	@Column(name = "meta_key")
	public String getMetaKey() {
		return metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

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
		Termmeta that = (Termmeta) o;
		return metaId == that.metaId &&
				termId == that.termId &&
				Objects.equals(metaKey, that.metaKey) &&
				Objects.equals(metaValue, that.metaValue);
	}

	@Override
	public int hashCode() {

		return Objects.hash(metaId, termId, metaKey, metaValue);
	}
}
