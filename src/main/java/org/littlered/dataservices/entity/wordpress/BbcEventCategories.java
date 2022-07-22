package org.littlered.dataservices.entity.wordpress;

import org.littlered.dataservices.entity.eventManager.pub.EmEvents;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bbc_event_categories")
public class BbcEventCategories implements Serializable {
	@Id
	@Column(name = "post_id")
	private long postId;

	@Basic
	@Column(name = "taxonomy")
	private String taxonomy;

	@Id
	@Column(name = "term_id")
	private Long termId;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "slug")
	private String slug;

	@ManyToOne
	@JoinColumn(name = "event_id")
	@JsonBackReference
	private EmEvents eventId;


//	@ManyToOne
//	@JoinColumn(name = "event_id", insertable = false, updatable = false)
//	@JsonBackReference
//	private org.littlered.dataservices.entity.eventManager.pub.EmEvents eventPublic;

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmEvents getEvent() {
		return this.eventId;
	}
	public void setEventId(EmEvents event) {
		this.eventId = event;
	}


//	public EmEvents getEventPublic() {
//		return this.eventPublic;
//	}
//
//	public void setEventPublic(EmEvents eventPublic) {
//		this.eventPublic = eventPublic;
///	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BbcEventCategories that = (BbcEventCategories) o;
		return postId == that.postId &&
				Objects.equals(taxonomy, that.taxonomy) &&
				Objects.equals(termId, that.termId) &&
				Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(postId, taxonomy, termId, name);
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
}
