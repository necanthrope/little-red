package org.littlered.dataservices.entity.wordpress;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(TermRelationshipsPK.class)
public class TermRelationships {
	private long objectId;
	private long termTaxonomyId;
	private int termOrder;

	@Id
	@Column(name = "object_id")
	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	@Id
	@Column(name = "term_taxonomy_id")
	public long getTermTaxonomyId() {
		return termTaxonomyId;
	}

	public void setTermTaxonomyId(long termTaxonomyId) {
		this.termTaxonomyId = termTaxonomyId;
	}

	@Basic
	@Column(name = "term_order")
	public int getTermOrder() {
		return termOrder;
	}

	public void setTermOrder(int termOrder) {
		this.termOrder = termOrder;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TermRelationships that = (TermRelationships) o;
		return objectId == that.objectId &&
				termTaxonomyId == that.termTaxonomyId &&
				termOrder == that.termOrder;
	}

	@Override
	public int hashCode() {

		return Objects.hash(objectId, termTaxonomyId, termOrder);
	}
}
