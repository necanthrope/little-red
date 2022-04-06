package org.littlered.dataservices.repository.eventManager.interfaces;

import org.littlered.dataservices.entity.eventManager.EmTicketsBookings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeremy on 8/13/2017.
 */
@Repository
public interface TicketsBookingsRepositoryInterface extends PagingAndSortingRepository<EmTicketsBookings, Long> {

	@Query(value = "SELECT tb from EmTicketsBookings tb where tb.bookingId.eventId.eventId = ?1 ")
	List<EmTicketsBookings> findForEvent(Long eventId);

	@Query(value = "SELECT tb from EmTicketsBookings tb where tb.bookingId.eventId.eventId = ?1 and tb.bookingId.user.id = ?2 and tb.bookingId.bookingStatus = 1" )
	List<EmTicketsBookings> findForEventAndUser(Long eventId, Long userId);

}
