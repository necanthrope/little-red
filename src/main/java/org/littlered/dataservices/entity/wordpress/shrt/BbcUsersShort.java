package org.littlered.dataservices.entity.wordpress.shrt;

import org.littlered.dataservices.entity.wordpress.BbcUserFavorites;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Jeremy on 3/26/2017.
 */
@Entity
public class BbcUsersShort implements Serializable {
	private Long id;
	private String displayName;
	private String bookingComment;
	private Set<BbcUserFavorites> bbcUserFavorites;

	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@Where(clause = "status = 1")
	@OrderBy(value = "createDate")
	public Set<BbcUserFavorites> getBbcUserFavorites() {
		return bbcUserFavorites;
	}

	public void setBbcUserFavorites(Set<BbcUserFavorites> bbcUserFavorites) {
		this.bbcUserFavorites = bbcUserFavorites;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BbcUsersShort that = (BbcUsersShort) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
		return result;
	}

	@Transient
	public String getBookingComment() {
		return bookingComment;
	}

	public void setBookingComment(String bookingComment) {
		this.bookingComment = bookingComment;
	}
}
