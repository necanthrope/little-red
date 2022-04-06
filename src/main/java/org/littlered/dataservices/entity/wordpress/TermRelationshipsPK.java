package org.littlered.dataservices.entity.wordpress;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TermRelationshipsPK implements Serializable {
	private long objectId;
	private long termTaxonomyId;

	@Column(name = "object_id")
	@Id
	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	@Column(name = "term_taxonomy_id")
	@Id
	public long getTermTaxonomyId() {
		return termTaxonomyId;
	}

	public void setTermTaxonomyId(long termTaxonomyId) {
		this.termTaxonomyId = termTaxonomyId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TermRelationshipsPK that = (TermRelationshipsPK) o;
		return objectId == that.objectId &&
				termTaxonomyId == that.termTaxonomyId;
	}

	@Override
	public int hashCode() {

		return Objects.hash(objectId, termTaxonomyId);
	}
}
