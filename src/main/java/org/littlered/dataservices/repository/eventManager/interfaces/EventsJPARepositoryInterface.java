package org.littlered.dataservices.repository.eventManager.interfaces;

import org.littlered.dataservices.entity.eventManager.EmEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeremy on 3/25/2017.
 */
@Repository
public interface EventsJPARepositoryInterface extends JpaRepository<EmEvents, Long> {

	@Query(value =
			"select terms.name, terms.slug, terms.termId, tax.taxonomy " +
			"  from Terms terms, TermTaxonomy tax " +
			" where tax.termId = terms.termId " +
			"   and tax.taxonomy like 'event-%' " +
			" order by tax.taxonomy, terms.slug ")
	List<Object[]> findEventMetadata();

}
