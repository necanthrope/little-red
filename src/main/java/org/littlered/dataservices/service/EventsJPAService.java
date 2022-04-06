package org.littlered.dataservices.service;

import org.littlered.dataservices.Constants;
import org.littlered.dataservices.dto.littlered.EventMetadataDTO;
import org.littlered.dataservices.dto.eventManager.CreateEventDTO;
import org.littlered.dataservices.entity.eventManager.*;
import org.littlered.dataservices.entity.eventManager.EmEvents;
import org.littlered.dataservices.entity.eventManager.EmTickets;
import org.littlered.dataservices.entity.wordpress.Postmeta;
import org.littlered.dataservices.entity.wordpress.Posts;
import org.littlered.dataservices.entity.wordpress.TermRelationships;
import org.littlered.dataservices.entity.wordpress.shrt.Users;
import org.littlered.dataservices.repository.eventManager.interfaces.*;
import org.littlered.dataservices.repository.wordpress.interfaces.PostMetaJPAInterface;
import org.littlered.dataservices.repository.wordpress.interfaces.PostsJPAInterface;
import org.littlered.dataservices.util.php.slug.WpSlugUtil;
import org.littlered.dataservices.repository.eventManager.interfaces.EventsJPARepositoryInterface;
import org.littlered.dataservices.repository.eventManager.interfaces.TermRelationshipsJPARepositoryInterface;
import org.littlered.dataservices.repository.eventManager.interfaces.TicketsRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeremy on 4/2/2017.
 */
@Service
@Transactional
public class EventsJPAService {

	@Autowired
	private PostsJPAInterface postsService;

	@Autowired
	private EventsJPARepositoryInterface eventsJPARepositoryService;

	@Autowired
	private TicketsRepositoryInterface ticketsService;

	@Autowired
	private PostMetaJPAInterface postMetaJPAService;

	@Autowired
	private TermRelationshipsJPARepositoryInterface termRelationshipsJPARepository;

	@Value("${display.year.filter}")
	private String yearFilter;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void saveEvents(CreateEventDTO saveEvent, Users user) throws Exception {

		Timestamp now = Timestamp.from(Instant.now());

		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DEFAULT_BOOKING_DATE_FORMAT);
		Date startDate = dateFormat.parse(Constants.DEFAULT_BOOKING_START_DATE);
		Date endDate = dateFormat.parse(Constants.DEFAULT_BOOKING_END_DATE);

		SimpleDateFormat timeFormat = new SimpleDateFormat(Constants.DEFAULT_BOOKING_TIME_FORMAT);
		Time startTime = new Time(timeFormat.parse(Constants.DEFAULT_BOOKING_START_TIME).getTime());
		Time endTime = new Time(timeFormat.parse(Constants.DEFAULT_BOOKING_END_TIME).getTime());

		String eventSlug = WpSlugUtil.slugify(saveEvent.getEventName());

		//EntityTransaction etx = em.getTransaction();

		//etx.begin();
		try {

			Posts post = new Posts();
			post.setPostAuthor(1L);
			post.setPostDate(now);
			post.setPostDateGmt(now);
			post.setPostContent(saveEvent.getEventDescription());
			post.setPostTitle(saveEvent.getEventName());
			post.setPostStatus("publish");
			post.setCommentStatus("open");
			post.setPingStatus("open");
			post.setPostName(eventSlug);
			post.setPostModified(now);
			post.setPostModifiedGmt(now);
			post.setPostParent(0L);
			post.setGuid("http://www.bigbadcon.com/?event=".concat(eventSlug));
			post.setPostType("event");
			post.setCommentCount(0L);
			post.setMenuOrder(0);
			post.setPostContentFiltered("");
			post.setPostExcerpt("");
			post.setPostMimeType("");
			post.setPostPassword("");
			post.setPingStatus("");
			post.setToPing("");
			post.setPinged("");
			post.setPingStatus("closed");
			postsService.saveAndFlush(post);

			EmEvents event = new EmEvents();
			// Fields from wp post object
			event.setEventOwner(user);
			event.setEventName(saveEvent.getEventName());
			event.setPostContent(saveEvent.getEventDescription());
			event.setPostId(post.getId());
			event.setEventSlug(eventSlug);
			event.setEventStatus(0);
			event.setEventStartDate(new java.sql.Date(startDate.getTime()));
			event.setEventEndDate(new java.sql.Date(endDate.getTime()));
			event.setEventStartTime(startTime);
			event.setEventEndTime(endTime);
			event.setEventRsvp((byte) 0b1);
			event.setEventSpaces(0);
			event.setLocationId(0L);
			event.setEventDateCreated(now);
			event.setEventDateModified(now);
			event.setGroupId(0L);
			event.setEventAllDay(0);
			event.setEventPrivate((byte) 0b0);
			event.setEventRsvpSpaces(0);
			eventsJPARepositoryService.saveAndFlush(event);

			EmTickets ticket = new EmTickets();
			ticket.setEventId(event.getEventId());
			ticket.setTicketPrice(BigDecimal.ZERO);
			ticket.setTicketStart(now);
			ticket.setTicketEnd(now);
			ticket.setTicketMin(1);
			ticket.setTicketMax(1);
			ticket.setTicketSpaces(Integer.valueOf(saveEvent.getPlaytest()));
			ticket.setTicketName("");
			ticketsService.save(ticket);


			Postmeta postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("System");
			postmeta.setMetaValue(saveEvent.getSystem());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("GM");
			postmeta.setMetaValue(saveEvent.getGm());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("Characters");
			postmeta.setMetaValue(saveEvent.getCharacters());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("Players");
			postmeta.setMetaValue(saveEvent.getPlayers());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("Length");
			postmeta.setMetaValue(saveEvent.getLength());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("player_age");
			postmeta.setMetaValue(saveEvent.getPlayerAge());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("gm_age");
			postmeta.setMetaValue(saveEvent.getGmAge());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("Playtest");
			postmeta.setMetaValue(saveEvent.getPlaytest());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("Scheduling_Pref");
			postmeta.setMetaValue(saveEvent.getSchedulingPref());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("safety_tools");
			postmeta.setMetaValue(saveEvent.getSafetyTools());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("room");
			postmeta.setMetaValue("");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("table");
			postmeta.setMetaValue("");
			postMetaJPAService.save(postmeta);

			/*
			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("game_image");
			postmeta.setMetaValue("");
			postMetaJPAService.save(postmeta);
			*/

			// WP/EM bookeeping from here on
			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_eventId");
			postmeta.setMetaValue(event.getEventId().toString());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_start_time");
			postmeta.setMetaValue(Constants.DEFAULT_WP_BOOKING_START_TIME);
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_end_time");
			postmeta.setMetaValue(Constants.DEFAULT_WP_BOOKING_END_TIME);
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_all_day");
			postmeta.setMetaValue("0");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_start_date");
			postmeta.setMetaValue(Constants.DEFAULT_BOOKING_START_DATE);
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_end_date");
			postmeta.setMetaValue(Constants.DEFAULT_BOOKING_END_DATE);
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_rsvp");
			postmeta.setMetaValue("1");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_rsvp_date");
			postmeta.setMetaValue(Constants.DEFAULT_BOOKING_RSVP_DATE);
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_rsvp_time");
			postmeta.setMetaValue(Constants.DEFAULT_WP_BOOKING_RSVP_TIME);
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_rsvp_spaces");
			postmeta.setMetaValue("0");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_spaces");
			postmeta.setMetaValue("0");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_locationId");
			postmeta.setMetaValue("0");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_recurrenceId");
			postmeta.setMetaValue("");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_status");
			postmeta.setMetaValue("1");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_private");
			postmeta.setMetaValue("0");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_blogId");
			postmeta.setMetaValue(null);
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_groupId");
			postmeta.setMetaValue("0");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_recurrence");
			postmeta.setMetaValue("0");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_recurrence_interval");
			postmeta.setMetaValue("");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_recurrence_freq");
			postmeta.setMetaValue("");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_recurrence_days");
			postmeta.setMetaValue("0");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_recurrence_byday");
			postmeta.setMetaValue("");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_recurrence_byweekno");
			postmeta.setMetaValue("");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_recurrence_rsvp_days");
			postmeta.setMetaValue("");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_start_ts");
			postmeta.setMetaValue(String.valueOf(startDate.getTime()));
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_end_ts");
			postmeta.setMetaValue(String.valueOf(endDate.getTime()));
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_wp_old_slug");
			postmeta.setMetaValue(eventSlug);
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_start");
			postmeta.setMetaValue(Constants.DEFAULT_BOOKING_START_DATE.concat(" ")
					.concat(Constants.DEFAULT_WP_BOOKING_START_TIME));
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_end");
			postmeta.setMetaValue(Constants.DEFAULT_BOOKING_END_DATE.concat(" ")
					.concat(Constants.DEFAULT_WP_BOOKING_END_TIME));
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_timezone");
			postmeta.setMetaValue("America\\/Los_Angeles");
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_start_local");
			postmeta.setMetaValue(Constants.DEFAULT_BOOKING_START_DATE.concat(" ")
					.concat(Constants.DEFAULT_WP_BOOKING_START_TIME));
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("_event_end_local");
			postmeta.setMetaValue(Constants.DEFAULT_BOOKING_END_DATE.concat(" ")
					.concat(Constants.DEFAULT_WP_BOOKING_END_TIME));
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("user_display_name");
			postmeta.setMetaValue(saveEvent.getUserDisplayName());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("other_info");
			postmeta.setMetaValue(saveEvent.getOtherInfo());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("request_private_room");
			postmeta.setMetaValue(Boolean.toString(saveEvent.isRequestPrivateRoom()));
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("request_media_room");
			postmeta.setMetaValue(Boolean.toString(saveEvent.isRequestMediaRoom()));
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("additional_gms");
			postmeta.setMetaValue(saveEvent.getAdditionalGms());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("request_media_equipment");
			postmeta.setMetaValue(saveEvent.getRequestMediaEquipment());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("additional_requirements");
			postmeta.setMetaValue(saveEvent.getAdditionalRequirements());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("table_type");
			postmeta.setMetaValue(saveEvent.getTableType());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("content_advisory");
			postmeta.setMetaValue(Boolean.toString(saveEvent.isContentAdvisory()));
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("trigger_warnings");
			postmeta.setMetaValue(saveEvent.getTriggerWarnings());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("accessability_options");
			postmeta.setMetaValue(saveEvent.getAccessabilityOptions());
			postMetaJPAService.save(postmeta);

			postmeta = new Postmeta();
			postmeta.setPostId(post.getId());
			postmeta.setMetaKey("event_facilitators");
			postmeta.setMetaValue(saveEvent.getEventFacilitators());
			postMetaJPAService.save(postmeta);

			for (Long metaId : saveEvent.getEventMetadataIds()) {
				TermRelationships rel = new TermRelationships();
				rel.setObjectId(post.getId());
				rel.setTermTaxonomyId(metaId);
				rel.setTermOrder(0);
				termRelationshipsJPARepository.save(rel);
			}

			//etx.commit();

		} catch(Exception e) {
			e.printStackTrace();
			//etx.rollback();
			throw e;
		}

	}

	public List<EventMetadataDTO> getEventMetadata() throws Exception {
		List<EventMetadataDTO> ret = new ArrayList<>();

		List<Object[]> rawData = eventsJPARepositoryService.findEventMetadata();
		for (Object[] row : rawData) {
			EventMetadataDTO data = new EventMetadataDTO();
			data.setName((String)row[0]);
			data.setSlug((String)row[1]);
			data.setId((Long)row[2]);
			data.setType((String)row[3]);
			ret.add(data);
		}

		return ret;
	}

}
