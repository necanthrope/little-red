package org.littlered.dataservices.repository.eventManager.interfaces;

import org.littlered.dataservices.entity.wordpress.Options;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeremy on 8/13/2017.
 */
@Repository
public interface OptionsRepositoryInterface extends CrudRepository<Options, Long> {

	@Query("SELECT t from Options t where t.optionId = ?1")
	Options find(Long optionId);

	@Query("SELECT t from Options t where t.optionName = ?1")
	Options findByName(String optionName);

}
