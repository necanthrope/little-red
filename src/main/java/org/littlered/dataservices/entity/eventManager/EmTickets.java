package org.littlered.dataservices.entity.eventManager;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Jeremy on 3/19/2017.
 */
@Entity
//@Table(name = "em_tickets")
public class EmTickets {
	private long ticketId;
	private long eventId;
	private String ticketName;
	private String ticketDescription;
	private BigDecimal ticketPrice;
	private Timestamp ticketStart;
	private Timestamp ticketEnd;
	private Integer ticketMin;
	private Integer ticketMax;
	private Integer ticketSpaces;
	private Integer ticketMembers;
	private Integer ticketRequired;
	private String ticketMembersRoles;
	private Integer ticketGuests;
	private String ticketMeta;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	@Basic
	@Column(name = "event_id")
	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	@Basic
	@Column(name = "ticket_name", columnDefinition = "TINYTEXT")
	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	@Basic
	@Column(name = "ticket_description", columnDefinition = "TEXT")
	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	@Basic
	@Column(name = "ticket_price")
	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Basic
	@Column(name = "ticket_start")
	public Timestamp getTicketStart() {
		return ticketStart;
	}

	public void setTicketStart(Timestamp ticketStart) {
		this.ticketStart = ticketStart;
	}

	@Basic
	@Column(name = "ticket_end")
	public Timestamp getTicketEnd() {
		return ticketEnd;
	}

	public void setTicketEnd(Timestamp ticketEnd) {
		this.ticketEnd = ticketEnd;
	}

	@Basic
	@Column(name = "ticket_min")
	public Integer getTicketMin() {
		return ticketMin;
	}

	public void setTicketMin(Integer ticketMin) {
		this.ticketMin = ticketMin;
	}

	@Basic
	@Column(name = "ticket_max")
	public Integer getTicketMax() {
		return ticketMax;
	}

	public void setTicketMax(Integer ticketMax) {
		this.ticketMax = ticketMax;
	}

	@Basic
	@Column(name = "ticket_spaces")
	public Integer getTicketSpaces() {
		return ticketSpaces;
	}

	public void setTicketSpaces(Integer ticketSpaces) {
		this.ticketSpaces = ticketSpaces;
	}

	@Basic
	@Column(name = "ticket_members")
	public Integer getTicketMembers() {
		return ticketMembers;
	}

	public void setTicketMembers(Integer ticketMembers) {
		this.ticketMembers = ticketMembers;
	}

	@Basic
	@Column(name = "ticket_required")
	public Integer getTicketRequired() {
		return ticketRequired;
	}

	public void setTicketRequired(Integer ticketRequired) {
		this.ticketRequired = ticketRequired;
	}

	@Basic
	@Lob
	@Column(name = "ticket_members_roles")
	public String getTicketMembersRoles() {
		return ticketMembersRoles;
	}

	public void setTicketMembersRoles(String ticketMembersRoles) {
		this.ticketMembersRoles = ticketMembersRoles;
	}

	@Basic
	@Column(name = "ticket_guests")
	public Integer getTicketGuests() {
		return ticketGuests;
	}

	public void setTicketGuests(Integer ticketGuests) {
		this.ticketGuests = ticketGuests;
	}

	@Basic
	@Lob
	@Column(name = "ticket_meta")
	public String getTicketMeta() {
		return ticketMeta;
	}

	public void setTicketMeta(String ticketMeta) {
		this.ticketMeta = ticketMeta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EmTickets that = (EmTickets) o;

		if (ticketId != that.ticketId) return false;
		if (eventId != that.eventId) return false;
		if (ticketName != null ? !ticketName.equals(that.ticketName) : that.ticketName != null) return false;
		if (ticketDescription != null ? !ticketDescription.equals(that.ticketDescription) : that.ticketDescription != null)
			return false;
		if (ticketPrice != null ? !ticketPrice.equals(that.ticketPrice) : that.ticketPrice != null) return false;
		if (ticketStart != null ? !ticketStart.equals(that.ticketStart) : that.ticketStart != null) return false;
		if (ticketEnd != null ? !ticketEnd.equals(that.ticketEnd) : that.ticketEnd != null) return false;
		if (ticketMin != null ? !ticketMin.equals(that.ticketMin) : that.ticketMin != null) return false;
		if (ticketMax != null ? !ticketMax.equals(that.ticketMax) : that.ticketMax != null) return false;
		if (ticketSpaces != null ? !ticketSpaces.equals(that.ticketSpaces) : that.ticketSpaces != null) return false;
		if (ticketMembers != null ? !ticketMembers.equals(that.ticketMembers) : that.ticketMembers != null)
			return false;
		if (ticketRequired != null ? !ticketRequired.equals(that.ticketRequired) : that.ticketRequired != null)
			return false;
		if (ticketMembersRoles != null ? !ticketMembersRoles.equals(that.ticketMembersRoles) : that.ticketMembersRoles != null)
			return false;
		if (ticketGuests != null ? !ticketGuests.equals(that.ticketGuests) : that.ticketGuests != null) return false;
		if (ticketMeta != null ? !ticketMeta.equals(that.ticketMeta) : that.ticketMeta != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (ticketId ^ (ticketId >>> 32));
		result = 31 * result + (int) (eventId ^ (eventId >>> 32));
		result = 31 * result + (ticketName != null ? ticketName.hashCode() : 0);
		result = 31 * result + (ticketDescription != null ? ticketDescription.hashCode() : 0);
		result = 31 * result + (ticketPrice != null ? ticketPrice.hashCode() : 0);
		result = 31 * result + (ticketStart != null ? ticketStart.hashCode() : 0);
		result = 31 * result + (ticketEnd != null ? ticketEnd.hashCode() : 0);
		result = 31 * result + (ticketMin != null ? ticketMin.hashCode() : 0);
		result = 31 * result + (ticketMax != null ? ticketMax.hashCode() : 0);
		result = 31 * result + (ticketSpaces != null ? ticketSpaces.hashCode() : 0);
		result = 31 * result + (ticketMembers != null ? ticketMembers.hashCode() : 0);
		result = 31 * result + (ticketRequired != null ? ticketRequired.hashCode() : 0);
		result = 31 * result + (ticketMembersRoles != null ? ticketMembersRoles.hashCode() : 0);
		result = 31 * result + (ticketGuests != null ? ticketGuests.hashCode() : 0);
		result = 31 * result + (ticketMeta != null ? ticketMeta.hashCode() : 0);
		return result;
	}
}
