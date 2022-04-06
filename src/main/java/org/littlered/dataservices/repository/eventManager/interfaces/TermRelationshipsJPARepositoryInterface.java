package org.littlered.dataservices.repository.eventManager.interfaces;

import org.littlered.dataservices.entity.wordpress.TermRelationships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeremy on 3/25/2017.
 */
@Repository
public interface TermRelationshipsJPARepositoryInterface extends JpaRepository<TermRelationships, Long> {

}
