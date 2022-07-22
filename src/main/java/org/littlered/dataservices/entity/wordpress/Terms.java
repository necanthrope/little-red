package org.littlered.dataservices.entity.wordpress;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Terms {
	private long termId;
	private String name;
	private String slug;
	private long termGroup;

	@Id
	@Column(name = "term_id")
	public long getTermId() {
		return termId;
	}

	public void setTermId(long termId) {
		this.termId = termId;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "slug")
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	@Basic
	@Column(name = "term_group")
	public long getTermGroup() {
		return termGroup;
	}

	public void setTermGroup(long termGroup) {
		this.termGroup = termGroup;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Terms that = (Terms) o;
		return termId == that.termId &&
				termGroup == that.termGroup &&
				Objects.equals(name, that.name) &&
				Objects.equals(slug, that.slug);
	}

	@Override
	public int hashCode() {

		return Objects.hash(termId, name, slug, termGroup);
	}
}




