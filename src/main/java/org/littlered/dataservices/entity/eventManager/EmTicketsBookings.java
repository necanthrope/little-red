package org.littlered.dataservices.entity.eventManager;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Jeremy on 3/19/2017.
 */
@Entity
public class EmTicketsBookings {
	private Long ticketBookingId;
	private EmBookings bookingId;
	private Long ticketId;
	private Integer ticketBookingSpaces;
	private BigDecimal ticketBookingPrice;

	private String ticketUuid = "";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ticket_booking_id")
	public Long getTicketBookingId() {
		return ticketBookingId;
	}

	public void setTicketBookingId(Long ticketBookingId) {
		this.ticketBookingId = ticketBookingId;
	}

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="booking_id")
	public EmBookings getBookingId() {
		return bookingId;
	}

	public void setBookingId(EmBookings booking) {
		this.bookingId = booking;
	}

	@Basic
	@Column(name = "ticket_id")
	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	@Basic
	@Column(name = "ticket_booking_spaces")
	public Integer getTicketBookingSpaces() {
		return ticketBookingSpaces;
	}

	public void setTicketBookingSpaces(Integer ticketBookingSpaces) {
		this.ticketBookingSpaces = ticketBookingSpaces;
	}

	@Basic
	@Column(name = "ticket_booking_price")
	public BigDecimal getTicketBookingPrice() {
		return ticketBookingPrice;
	}

	public void setTicketBookingPrice(BigDecimal ticketBookingPrice) {
		this.ticketBookingPrice = ticketBookingPrice;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EmTicketsBookings that = (EmTicketsBookings) o;

		if (ticketBookingId != that.ticketBookingId) return false;
		if (bookingId != that.bookingId) return false;
		if (ticketId != that.ticketId) return false;
		if (ticketBookingSpaces != that.ticketBookingSpaces) return false;
		if (ticketBookingPrice != null ? !ticketBookingPrice.equals(that.ticketBookingPrice) : that.ticketBookingPrice != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (ticketBookingId ^ (ticketBookingId >>> 32));
		result = 31 * result + (int) (bookingId.getBookingId() ^ (bookingId.getBookingId() >>> 32));
		result = 31 * result + (int) (ticketId ^ (ticketId >>> 32));
		result = 31 * result + ticketBookingSpaces;
		result = 31 * result + (ticketBookingPrice != null ? ticketBookingPrice.hashCode() : 0);
		return result;
	}

	@Column(name = "ticket_uuid", columnDefinition="char")
	public String getTicketUuid() {
		return ticketUuid;
	}

	public void setTicketUuid(String ticketUuid) {
		this.ticketUuid = ticketUuid;
	}
}
