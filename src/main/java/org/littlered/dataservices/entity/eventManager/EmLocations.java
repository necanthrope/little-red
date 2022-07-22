package org.littlered.dataservices.entity.eventManager;

import javax.persistence.*;

/**
 * Created by Jeremy on 3/19/2017.
 */
@Entity
public class EmLocations {
	private long locationId;
	private String locationSlug;
	private String locationName;
	private long locationOwner;
	private String locationAddress;
	private String locationTown;
	private String locationState;
	private String locationPostcode;
	private String locationRegion;
	private String locationCountry;
	private Double locationLatitude;
	private Double locationLongitude;
	private String postContent;
	private long postId;
	private Long blogId;
	private Integer locationStatus;
	private byte locationPrivate;

	@Id
	@Column(name = "location_id")
	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	@Basic
	@Column(name = "location_slug")
	public String getLocationSlug() {
		return locationSlug;
	}

	public void setLocationSlug(String locationSlug) {
		this.locationSlug = locationSlug;
	}

	@Basic
	@Column(name = "location_name", columnDefinition = "TEXT")
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Basic
	@Column(name = "location_owner")
	public long getLocationOwner() {
		return locationOwner;
	}

	public void setLocationOwner(long locationOwner) {
		this.locationOwner = locationOwner;
	}

	@Basic
	@Column(name = "location_address")
	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	@Basic
	@Column(name = "location_town")
	public String getLocationTown() {
		return locationTown;
	}

	public void setLocationTown(String locationTown) {
		this.locationTown = locationTown;
	}

	@Basic
	@Column(name = "location_state")
	public String getLocationState() {
		return locationState;
	}

	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}

	@Basic
	@Column(name = "location_postcode")
	public String getLocationPostcode() {
		return locationPostcode;
	}

	public void setLocationPostcode(String locationPostcode) {
		this.locationPostcode = locationPostcode;
	}

	@Basic
	@Column(name = "location_region")
	public String getLocationRegion() {
		return locationRegion;
	}

	public void setLocationRegion(String locationRegion) {
		this.locationRegion = locationRegion;
	}

	@Basic
	@Column(name = "location_country", columnDefinition = "CHAR")
	public String getLocationCountry() {
		return locationCountry;
	}

	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}

	@Basic
	//@Column(name = "location_latitude", columnDefinition = "FLOAT")
	@Column(name = "location_latitude", columnDefinition = "DECIMAL")
	public Double getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(Double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	@Basic
//	@Column(name = "location_longitude", columnDefinition = "FLOAT")
	@Column(name = "location_longitude", columnDefinition = "DECIMAL")
	public Double getLocationLongitude() {
		return locationLongitude;
	}

	public void setLocationLongitude(Double locationLongitude) {
		this.locationLongitude = locationLongitude;
	}

	@Basic
	@Lob
	@Column(name = "post_content")
	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	@Basic
	@Column(name = "post_id")
	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	@Basic
	@Column(name = "blog_id")
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	@Basic
	@Column(name = "location_status")
	public Integer getLocationStatus() {
		return locationStatus;
	}

	public void setLocationStatus(Integer locationStatus) {
		this.locationStatus = locationStatus;
	}

	@Basic
	@Column(name = "location_private")
	public byte getLocationPrivate() {
		return locationPrivate;
	}

	public void setLocationPrivate(byte locationPrivate) {
		this.locationPrivate = locationPrivate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EmLocations that = (EmLocations) o;

		if (locationId != that.locationId) return false;
		if (locationOwner != that.locationOwner) return false;
		if (postId != that.postId) return false;
		if (locationPrivate != that.locationPrivate) return false;
		if (locationSlug != null ? !locationSlug.equals(that.locationSlug) : that.locationSlug != null) return false;
		if (locationName != null ? !locationName.equals(that.locationName) : that.locationName != null) return false;
		if (locationAddress != null ? !locationAddress.equals(that.locationAddress) : that.locationAddress != null)
			return false;
		if (locationTown != null ? !locationTown.equals(that.locationTown) : that.locationTown != null) return false;
		if (locationState != null ? !locationState.equals(that.locationState) : that.locationState != null)
			return false;
		if (locationPostcode != null ? !locationPostcode.equals(that.locationPostcode) : that.locationPostcode != null)
			return false;
		if (locationRegion != null ? !locationRegion.equals(that.locationRegion) : that.locationRegion != null)
			return false;
		if (locationCountry != null ? !locationCountry.equals(that.locationCountry) : that.locationCountry != null)
			return false;
		if (locationLatitude != null ? !locationLatitude.equals(that.locationLatitude) : that.locationLatitude != null)
			return false;
		if (locationLongitude != null ? !locationLongitude.equals(that.locationLongitude) : that.locationLongitude != null)
			return false;
		if (postContent != null ? !postContent.equals(that.postContent) : that.postContent != null) return false;
		if (blogId != null ? !blogId.equals(that.blogId) : that.blogId != null) return false;
		if (locationStatus != null ? !locationStatus.equals(that.locationStatus) : that.locationStatus != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (locationId ^ (locationId >>> 32));
		result = 31 * result + (locationSlug != null ? locationSlug.hashCode() : 0);
		result = 31 * result + (locationName != null ? locationName.hashCode() : 0);
		result = 31 * result + (int) (locationOwner ^ (locationOwner >>> 32));
		result = 31 * result + (locationAddress != null ? locationAddress.hashCode() : 0);
		result = 31 * result + (locationTown != null ? locationTown.hashCode() : 0);
		result = 31 * result + (locationState != null ? locationState.hashCode() : 0);
		result = 31 * result + (locationPostcode != null ? locationPostcode.hashCode() : 0);
		result = 31 * result + (locationRegion != null ? locationRegion.hashCode() : 0);
		result = 31 * result + (locationCountry != null ? locationCountry.hashCode() : 0);
		result = 31 * result + (locationLatitude != null ? locationLatitude.hashCode() : 0);
		result = 31 * result + (locationLongitude != null ? locationLongitude.hashCode() : 0);
		result = 31 * result + (postContent != null ? postContent.hashCode() : 0);
		result = 31 * result + (int) (postId ^ (postId >>> 32));
		result = 31 * result + (blogId != null ? blogId.hashCode() : 0);
		result = 31 * result + (locationStatus != null ? locationStatus.hashCode() : 0);
		result = 31 * result + (int) locationPrivate;
		return result;
	}
}
