package org.littlered.dataservices.repository.wordpress.interfaces;

import org.littlered.dataservices.entity.wordpress.BbcUserFavorites;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeremy on 3/25/2017.
 */
@Repository
public interface BbcUserFavoritesInterface extends PagingAndSortingRepository<BbcUserFavorites, Long> {

	@Query("select f from BbcUserFavorites f where f.userId=?1 and f.eventId.eventId = ?2")
	BbcUserFavorites findByUserIdAndEventId(long userId, long eventId);

}
