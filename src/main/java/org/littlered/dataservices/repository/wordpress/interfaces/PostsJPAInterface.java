package org.littlered.dataservices.repository.wordpress.interfaces;

import org.littlered.dataservices.entity.wordpress.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PostsJPAInterface extends JpaRepository<Posts, Long> {

	ArrayList<Posts> findById(Long id);

	@Query(value = "select p from Posts p where p.postName = ?1")
	ArrayList<Posts> findByEventSlug(String eventSlug);

}
