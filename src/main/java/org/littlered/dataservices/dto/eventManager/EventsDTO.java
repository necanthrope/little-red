package org.littlered.dataservices.dto.eventManager;

import org.littlered.dataservices.entity.eventManager.EmBookings;
import org.littlered.dataservices.entity.eventManager.EmEvents;
import org.littlered.dataservices.entity.wordpress.shrt.BbcEventCategories;
import org.littlered.dataservices.entity.wordpress.Postmeta;
import org.littlered.dataservices.entity.wordpress.shrt.Users;
import de.ailis.pherialize.Mixed;
import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

/**
 * Created by Jeremy on 6/3/2017.
 */
public class EventsDTO implements Serializable{

	private Long eventId;
	private String eventSlug;
	private Users eventOwner;
	private Integer eventStatus;
	private String eventName;
	private Time eventStartTime;
	private Time eventEndTime;
	private Date eventStartDate;
	private Date eventEndDate;
	private Long eventCategoryId;
	private Date eventDateCreated;
	private Date eventDateModified;
	private Integer recurrenceInterval;
	private Long blogId;
	private Long groupId;
	private Long postId;
	private Integer eventAllDay;
	private Byte eventPrivate;
	private String eventRoom;
	private String eventTable;
	private Integer bookingExempt;
	private Long lastUpdated;

	private HashMap<String, String> eventAttributes;
	private String postContent;
	private ArrayList<Users> attendees = new ArrayList<>();
	private Set<BbcEventCategories> categories;
	private Set<Postmeta> metadata;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventSlug() {
		return eventSlug;
	}

	public void setEventSlug(String eventSlug) {
		this.eventSlug = eventSlug;
	}

	public Users getEventOwner() {
		return eventOwner;
	}

	public void setEventOwner(Users eventOwner) {
		this.eventOwner = eventOwner;
	}

	public Integer getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(Integer eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Time getEventStartTime() {
		return eventStartTime;
	}

	public void setEventStartTime(Time eventStartTime) {
		this.eventStartTime = eventStartTime;
	}

	public Time getEventEndTime() {
		return eventEndTime;
	}

	public void setEventEndTime(Time eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	public Date getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public Date getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public Long getEventCategoryId() {
		return eventCategoryId;
	}

	public void setEventCategoryId(Long eventCategoryId) {
		this.eventCategoryId = eventCategoryId;
	}

	public Date getEventDateCreated() {
		return eventDateCreated;
	}

	public void setEventDateCreated(Date eventDateCreated) {
		this.eventDateCreated = eventDateCreated;
	}

	public Date getEventDateModified() {
		return eventDateModified;
	}

	public void setEventDateModified(Date eventDateModified) {
		this.eventDateModified = eventDateModified;
	}

	public Integer getRecurrenceInterval() {
		return recurrenceInterval;
	}

	public void setRecurrenceInterval(Integer recurrenceInterval) {
		this.recurrenceInterval = recurrenceInterval;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Integer getEventAllDay() {
		return eventAllDay;
	}

	public void setEventAllDay(Integer eventAllDay) {
		this.eventAllDay = eventAllDay;
	}

	public Byte getEventPrivate() {
		return eventPrivate;
	}

	public void setEventPrivate(Byte eventPrivate) {
		this.eventPrivate = eventPrivate;
	}

	public String getEventRoom() {
		return eventRoom;
	}

	public void setEventRoom(String eventRoom) {
		this.eventRoom = eventRoom;
	}

	public String getEventTable() {
		return eventTable;
	}

	public void setEventTable(String eventTable) {
		this.eventTable = eventTable;
	}

	public Integer getBookingExempt() {
		return bookingExempt;
	}

	public void setBookingExempt(Integer bookingExempt) {
		this.bookingExempt = bookingExempt;
	}

	public HashMap<String, String> getEventAttributes() {
		return eventAttributes;
	}

	public void setEventAttributes(HashMap<String, String> eventAttributes) {
		this.eventAttributes = eventAttributes;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public ArrayList<Users> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<Users> attendees) {
		this.attendees = attendees;
	}

	public void wrapEntity(EmEvents entity) throws Exception{


		setEventId(entity.getEventId());
		setEventSlug(entity.getEventSlug());
		setEventStatus(entity.getEventStatus());
		setEventName(entity.getEventName());
		setEventStartTime(entity.getEventStartTime());
		setEventEndTime(entity.getEventEndTime());
		setEventOwner(entity.getEventOwner());
		setEventStartDate(entity.getEventStartDate());
		setEventEndDate(entity.getEventEndDate());
		setEventCategoryId(entity.getEventCategoryId());
		if(entity.getEventDateCreated() != null) {
			setEventDateCreated(new Date(entity.getEventDateCreated().getTime()));
		}
		if (entity.getEventDateModified() != null) {
			setEventDateModified(new Date(entity.getEventDateModified().getTime()));
		}
		setRecurrenceInterval(entity.getEventAllDay());
		setBlogId(entity.getEventId());
		setGroupId(entity.getEventId());
		setPostId(entity.getEventId());
		setEventAllDay(entity.getEventAllDay());
		setEventPrivate(entity.getEventPrivate());
		setEventRoom(entity.getEventRoom());
		setEventTable(entity.getEventTable());
		setBookingExempt(entity.getEventAllDay());
		setPostContent(entity.getPostContent());
		//setLastUpdated(entity.getLastUpdated().getTime() / 1000L);

		if (entity.getEventAttributes() != null) {
			try {
			//	setEventAttributes((HashMap<String, String>) new SerializedPhpParser(entity.getEventAttributes()).parse());
				HashMap<String, String> attributes = new HashMap<>();
				MixedArray list = Pherialize.unserialize(entity.getEventAttributes()).toArray();
				for (Map.Entry<Object, Object> entry : list.entrySet()) {
					Mixed key = (Mixed) entry.getKey();
					Mixed value = (Mixed) entry.getValue();
					attributes.put(key.toString(), value.toString());
				}
				setEventAttributes(attributes);

			} catch (Exception e) {
				System.out.println("Bad PHP serialization string for event " + entity.getEventId() + " : " + entity.getEventAttributes());
				e.printStackTrace();
			}
		}

		try {

			for (EmBookings booking : entity.getBookings()) {
				if (!booking.getBookingStatus().equals(1)) {
					continue;
				}
				this.getAttendees().add(booking.getUser());
				booking.getUser().setBookingComment(booking.getBookingComment());
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println();
		}

		this.setMetadata(entity.getMetadata());
		this.setCategories(entity.getCategories());

	}

	public Set<BbcEventCategories> getCategories() {
		return categories;
	}

	public void setCategories(Set<BbcEventCategories> categories) {
		this.categories = categories;
	}

	public Long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<Postmeta> getMetadata() {
		return metadata;
	}

	public void setMetadata(Set<Postmeta> metadata) {
		this.metadata = metadata;
	}
}
