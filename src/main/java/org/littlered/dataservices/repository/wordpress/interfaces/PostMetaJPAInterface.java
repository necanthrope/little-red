package org.littlered.dataservices.repository.wordpress.interfaces;

import org.littlered.dataservices.entity.wordpress.Postmeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PostMetaJPAInterface extends JpaRepository<Postmeta, Long> {

	@Query("select p from Postmeta p where p.postId = ?1 and p.metaKey = ?2")
	ArrayList<Postmeta> findByPostIdAndMetaKey(Long postId, String metaKey);

}
