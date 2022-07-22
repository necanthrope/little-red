package org.littlered.dataservices.repository.wordpress.interfaces;

import org.littlered.dataservices.entity.wordpress.shrt.BbcUsersShort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by Jeremy on 3/25/2017.
 */
@Repository
public interface UsersShortRepositoryInterface extends PagingAndSortingRepository<BbcUsersShort, Long> {

	ArrayList<BbcUsersShort> findById(Long id);

}
