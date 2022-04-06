package org.littlered.dataservices.entity.wordpress;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name = "term_taxonomy")
public class TermTaxonomy {
	private long termTaxonomyId;
	private long termId;
	private String taxonomy;
	private String description;
	private long parent;
	private long count;

	@Id
	@Column(name = "term_taxonomy_id")
	public long getTermTaxonomyId() {
		return termTaxonomyId;
	}

	public void setTermTaxonomyId(long termTaxonomyId) {
		this.termTaxonomyId = termTaxonomyId;
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
	@Column(name = "taxonomy")
	public String getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}

	@Lob
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@Column(name = "parent")
	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	@Basic
	@Column(name = "count")
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TermTaxonomy that = (TermTaxonomy) o;
		return termTaxonomyId == that.termTaxonomyId &&
				termId == that.termId &&
				parent == that.parent &&
				count == that.count &&
				Objects.equals(taxonomy, that.taxonomy) &&
				Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {

		return Objects.hash(termTaxonomyId, termId, taxonomy, description, parent, count);
	}
}
