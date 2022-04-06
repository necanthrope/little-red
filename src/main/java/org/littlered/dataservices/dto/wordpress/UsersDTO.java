package org.littlered.dataservices.dto.wordpress;

import org.littlered.dataservices.entity.wordpress.BbcUserFavorites;
import org.littlered.dataservices.entity.wordpress.Usermeta;
import org.littlered.dataservices.entity.wordpress.Users;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by Jeremy on 6/3/2017.
 */
public class UsersDTO implements Serializable {

	private Long id;
	private String userNicename;
	private String userEmail;
	private String userUrl;
	private Timestamp userRegistered;
	private String displayName;
	private String bookingComment;
	private Long lastUpdated;
	private List<Usermeta> metadata;
	private Set<BbcUserFavorites> bbcUserFavorites;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserNicename() {
		return userNicename;
	}

	public void setUserNicename(String userNicename) {
		this.userNicename = userNicename;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public Timestamp getUserRegistered() {
		return userRegistered;
	}

	public void setUserRegistered(Timestamp userRegistered) {
		this.userRegistered = userRegistered;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getBookingComment() {
		return bookingComment;
	}

	public void setBookingComment(String bookingComment) {
		this.bookingComment = bookingComment;
	}

	public void wrapEntity(Users entity) {
		setId(entity.getId());
		setUserNicename(entity.getUserNicename());
		setUserEmail(entity.getUserEmail());
		setUserUrl(entity.getUserUrl());
		setUserRegistered(entity.getUserRegistered());
		setDisplayName(entity.getDisplayName());
		setMetadata(entity.getUserMetas());
		setBbcUserFavorites(entity.getBbcUserFavorites());
	}

	public Long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<Usermeta> getMetadata() {
		return metadata;
	}

	public void setMetadata(List<Usermeta> metadata) {
		this.metadata = metadata;
	}

	public Set<BbcUserFavorites> getBbcUserFavorites() {
		return bbcUserFavorites;
	}

	public void setBbcUserFavorites(Set<BbcUserFavorites> bbcUserFavorites) {
		this.bbcUserFavorites = bbcUserFavorites;
	}
}
