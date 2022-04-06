package org.littlered.dataservices.repository.eventManager.interfaces;

import org.littlered.dataservices.entity.eventManager.pub.EmEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeremy on 3/25/2017.
 */
@Repository
@Component
public interface EventsPublicRepositoryInterface extends PagingAndSortingRepository<EmEvents, Long> {

	// Public events list (no login) queries
	@Query(value = "SELECT DISTINCT c.eventId from BbcEventCategories c where c.slug not in ( " +
			"'%\\_shift'" +
			") " +
			"and FUNCTION('YEAR', c.eventId.eventStartDate) = ?1")
	List<EmEvents> findPublicByYear(Integer year);

	@Query(value = "SELECT e from EmEvents e WHERE FUNCTION('YEAR', e.eventStartDate) = ?1 and e.eventSlug not like 'verify%badge%'")
	List<EmEvents> findPublicByYearPaginated(Integer year, Pageable limit);

	@Query(value = "SELECT DISTINCT b.eventId from EmBookings b " +
			" where (b.eventId.lastUpdated > FUNCTION('from_unixtime', ?1)  " +
			"    or b.lastUpdated > FUNCTION('from_unixtime', ?1)) " +
			"   and FUNCTION('YEAR', b.eventId.eventStartDate) = ?2")
	List<EmEvents> findUpdatedPublicAfterEpochDate(Long epochTime, Integer year);

}
