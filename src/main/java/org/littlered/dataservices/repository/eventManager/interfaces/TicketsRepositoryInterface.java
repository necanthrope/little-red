package org.littlered.dataservices.repository.eventManager.interfaces;

import org.littlered.dataservices.entity.eventManager.EmTickets;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeremy on 8/13/2017.
 */
@Repository
public interface TicketsRepositoryInterface extends PagingAndSortingRepository<EmTickets, Long> {

	@Query("SELECT t from EmTickets t where t.eventId = ?1")
	EmTickets findTicketByEventId(Long eventId);

}
