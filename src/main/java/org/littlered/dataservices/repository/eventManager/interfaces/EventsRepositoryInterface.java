package org.littlered.dataservices.repository.eventManager.interfaces;

import org.littlered.dataservices.entity.eventManager.EmBookings;
import org.littlered.dataservices.entity.eventManager.EmEvents;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeremy on 3/25/2017.
 */
@Repository
public interface EventsRepositoryInterface extends PagingAndSortingRepository<EmEvents, Long> {

	@Query(value = "SELECT * from " + "wp_tuiny5_em_events WHERE YEAR(event_start_date) = ?1 and event_slug not like 'verify%badge%'", nativeQuery = true)
	List<EmEvents> findByYear(Long year);

	@Query(value = "SELECT * from wp_tuiny5_em_events WHERE YEAR(event_start_date) = ?1 and event_slug not like 'verify%badge%' " +
			"LIMIT ?2 OFFSET ?3", nativeQuery = true)
	List<EmEvents> findByYearPaginated(Long year, Long length, Long offset);

	@Query(value = "SELECT b FROM EmBookings b WHERE b.eventId.eventStartDate = ?2 " +
			"AND b.eventId.eventStartTime <= ?3 AND b.eventId.eventEndTime > ?3 and b.user.id = ?1 and b.bookingStatus = 1")
	List<EmBookings> findConflictingEventsForUserAndDateTime(Long userId, Date checkDate, Time checkTime);

	//@Query(value = "SELECT e from Events e WHERE e.categories = ?1 ")
	List<EmEvents> findByCategories_slug(String category);

	@Query(value = "SELECT c.eventId from org.littlered.dataservices.entity.wordpress.BbcEventCategories c where c.slug = ?2 and FUNCTION('YEAR', c.eventId.eventStartDate) = ?1")
	List<EmEvents> findByCategoryAndYear(Integer year, String category);

	@Query(value = "SELECT count(*) from wp_tuiny5_em_events where YEAR(event_start_date) = ?1 and event_slug not like 'verify%badge%'", nativeQuery = true)
	Long getNumberOfEventsByYear(Long year);

	@Query(value = "SELECT DISTINCT b.eventId from EmBookings b " +
			" where (b.eventId.lastUpdated > FUNCTION('from_unixtime', ?1)  " +
			"    or b.lastUpdated > FUNCTION('from_unixtime', ?1)) " +
			"   and FUNCTION('YEAR', b.eventId.eventStartDate) = ?2")
	List<EmEvents> findUpdatedEventsAfterEpochDate(Long epochTime, Integer year);

	@Query(value = "SELECT e FROM EmEvents e, BbcUserFavorites f WHERE e.eventId = f.eventId and f.userId = ?1 AND FUNCTION('YEAR', e.eventStartDate) = ?2 ")
	List<EmEvents> findFavoritesForUser(Long userId, Integer year);
}
