package org.littlered.dataservices.service;

import org.littlered.dataservices.Constants;
import org.littlered.dataservices.entity.eventManager.EmEvents;
import org.littlered.dataservices.entity.wordpress.BbcUserFavorites;
import org.littlered.dataservices.exception.FavoritingException;
import org.littlered.dataservices.repository.eventManager.interfaces.BookingsRepositoryInterface;
import org.littlered.dataservices.repository.eventManager.interfaces.EventsPublicRepositoryInterface;
import org.littlered.dataservices.repository.eventManager.interfaces.EventsRepositoryInterface;
import org.littlered.dataservices.repository.wordpress.interfaces.BbcUserFavoritesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Jeremy on 4/2/2017.
 */
@Service
//@Transactional
public class FavoritesService {

	@Autowired
	private EventsRepositoryInterface eventsRepository;

	@Autowired
	private EventsPublicRepositoryInterface eventsPublicRepository;

	@Autowired
	private BookingsRepositoryInterface bookingsRepository;

	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private BbcUserFavoritesInterface bbcUserFavoritesInterface;

	@Value("${display.year.filter}")
	private String yearFilter;

	private String getFilterYear() {
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		if (yearFilter != null && !yearFilter.equals("")) {
			year = yearFilter;
		}
		return year;
	}

	public void createMyUserEventFavorite(Long eventId) throws Exception {
		Long myUserId = usersService.getCurrentUser().getId();
		createUserEventFavorite(myUserId, eventId);
	}

	public void createOtherUserEventFavorite(Long userId, Long eventId) throws Exception {
		securityService.checkRolesForCurrentUser(Constants.ROLE_LIST_ADMIN_ONLY);
		createUserEventFavorite(userId, eventId);
	}

	private void createUserEventFavorite(Long userId, Long eventId) throws Exception {
		if(eventId == null) {
			throw new FavoritingException("eventId cannot be null!");
		}

		if(userId == null) {
			throw new FavoritingException("userId cannot be null!");
		}

		EmEvents event = eventsRepository.findOne(eventId);
		if (event == null) {
			throw new FavoritingException("Event with id " + eventId + " not found!");
		}

		BbcUserFavorites favorite = bbcUserFavoritesInterface.findByUserIdAndEventId(userId, eventId);
		if (favorite != null) {
			throw new FavoritingException("User " + userId + " already favorited event " + eventId + "!");
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());

		favorite = new BbcUserFavorites();
		favorite.setUserId(userId);
		favorite.setEventId(event.getEventId());
		favorite.setCreateDate(now);
		favorite.setUpdateDate(now);
		favorite.setStatus((short) 1);

		bbcUserFavoritesInterface.save(favorite);
	}

	public void deleteMyUserEventFavorite(Long eventId) throws Exception {
		Long myUserId = usersService.getCurrentUser().getId();
		deleteUserEventFavorite(myUserId, eventId);
	}

	public void deleteOtherUserEventFavorite(Long userId, Long eventId) throws Exception{
		securityService.checkRolesForCurrentUser(Constants.ROLE_LIST_ADMIN_ONLY);
		deleteUserEventFavorite(userId, eventId);
	}

	private void deleteUserEventFavorite(Long userId, Long eventId) throws Exception{

		if(eventId == null) {
			throw new FavoritingException("eventId cannot be null!");
		}

		if(userId == null) {
			throw new FavoritingException("userId cannot be null!");
		}

		EmEvents event = eventsRepository.findOne(eventId);
		if (event == null) {
			throw new FavoritingException("Event with id " + eventId + " not found!");
		}

		BbcUserFavorites favorite = bbcUserFavoritesInterface.findByUserIdAndEventId(userId, eventId);
		if (favorite == null) {
			throw new FavoritingException("User " + userId + " has not favorited event " + eventId + "!");
		}
		bbcUserFavoritesInterface.delete(favorite);

	}

}
