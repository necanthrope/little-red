package org.littlered.dataservices.entity.eventManager;

import org.littlered.dataservices.entity.wordpress.shrt.BbcUsersShort;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by Jeremy on 3/19/2017.
 */
@Entity
public class EmBookings implements Serializable {
	private Long bookingId;
	private Integer bookingSpaces;
	private String bookingComment;
	private Timestamp bookingDate;
	private Integer bookingStatus;
	private BigDecimal bookingPrice;
	private String bookingMeta;
	private BigDecimal bookingTaxRate;
	private BigDecimal bookingTaxes;
	private Timestamp lastUpdated;
	private String bookingUuid = "";

	private BbcUsersShort user;
	private EmEvents eventId;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "booking_id", nullable = false)
	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", referencedColumnName = "ID")
	public BbcUsersShort getUser() {
		return this.user;
	}

	public void setUser(BbcUsersShort user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "event_id")
	@JsonBackReference
	public EmEvents getEventId() {
		return this.eventId;
	}

	public void setEventId(EmEvents event) {
		this.eventId = event;
	}


	@Basic
	@Column(name = "booking_spaces")
	public Integer getBookingSpaces() {
		return bookingSpaces;
	}

	public void setBookingSpaces(Integer bookingSpaces) {
		this.bookingSpaces = bookingSpaces;
	}

	@Basic
	@Column(name = "booking_comment", columnDefinition = "TEXT")
	public String getBookingComment() {
		return bookingComment;
	}

	public void setBookingComment(String bookingComment) {
		this.bookingComment = bookingComment;
	}

	@Basic
	@Column(name = "booking_date")
	public Timestamp getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Basic
	@Column(name = "booking_status", columnDefinition = "TINYINT", length = 1)
	public Integer getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(Integer bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	@Basic
	@Column(name = "booking_price")
	public BigDecimal getBookingPrice() {
		return bookingPrice;
	}

	public void setBookingPrice(BigDecimal bookingPrice) {
		this.bookingPrice = bookingPrice;
	}

	@Basic
	@Lob
	@Column(name = "booking_meta")
	public String getBookingMeta() {
		return bookingMeta;
	}

	public void setBookingMeta(String bookingMeta) {
		this.bookingMeta = bookingMeta;
	}

	@Basic
	@Column(name = "booking_tax_rate")
	public BigDecimal getBookingTaxRate() {
		return bookingTaxRate;
	}

	public void setBookingTaxRate(BigDecimal bookingTaxRate) {
		this.bookingTaxRate = bookingTaxRate;
	}

	@Basic
	@Column(name = "booking_taxes")
	public BigDecimal getBookingTaxes() {
		return bookingTaxes;
	}

	public void setBookingTaxes(BigDecimal bookingTaxes) {
		this.bookingTaxes = bookingTaxes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EmBookings that = (EmBookings) o;

		if (bookingId != null ? !bookingId.equals(that.bookingId) : that.bookingId != null) return false;
		if (bookingSpaces != null ? !bookingSpaces.equals(that.bookingSpaces) : that.bookingSpaces != null)
			return false;
		if (bookingComment != null ? !bookingComment.equals(that.bookingComment) : that.bookingComment != null)
			return false;
		if (bookingDate != null ? !bookingDate.equals(that.bookingDate) : that.bookingDate != null) return false;
		if (bookingStatus != null ? !bookingStatus.equals(that.bookingStatus) : that.bookingStatus != null)
			return false;
		if (bookingPrice != null ? !bookingPrice.equals(that.bookingPrice) : that.bookingPrice != null) return false;
		if (bookingMeta != null ? !bookingMeta.equals(that.bookingMeta) : that.bookingMeta != null) return false;
		if (bookingTaxRate != null ? !bookingTaxRate.equals(that.bookingTaxRate) : that.bookingTaxRate != null)
			return false;
		if (bookingTaxes != null ? !bookingTaxes.equals(that.bookingTaxes) : that.bookingTaxes != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = bookingId != null ? bookingId.hashCode() : 0;
		result = 31 * result + (bookingSpaces != null ? bookingSpaces.hashCode() : 0);
		result = 31 * result + (bookingComment != null ? bookingComment.hashCode() : 0);
		result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
		result = 31 * result + (bookingStatus != null ? bookingStatus.hashCode() : 0);
		result = 31 * result + (bookingPrice != null ? bookingPrice.hashCode() : 0);
		result = 31 * result + (bookingMeta != null ? bookingMeta.hashCode() : 0);
		result = 31 * result + (bookingTaxRate != null ? bookingTaxRate.hashCode() : 0);
		result = 31 * result + (bookingTaxes != null ? bookingTaxes.hashCode() : 0);
		return result;
	}

	@Basic
	@Column(name = "last_updated")
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "booking_uuid", columnDefinition="char")
	public String getBookingUuid() {
		return bookingUuid;
	}

	public void setBookingUuid(String bookingUuid) {
		this.bookingUuid = bookingUuid;
	}
}
