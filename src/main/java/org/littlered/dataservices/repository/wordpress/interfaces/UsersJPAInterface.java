package org.littlered.dataservices.repository.wordpress.interfaces;

import org.littlered.dataservices.entity.wordpress.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersJPAInterface extends JpaRepository<Users, Long> {

	@Query(value = "select u from org.littlered.dataservices.entity.wordpress.Users u where u.userLogin = ?1")
	List<Users> findUsersByUserLogin(String pUserLogin) throws Exception;

	@Query(value = "select u from org.littlered.dataservices.entity.wordpress.Users u where u.userEmail = ?1")
	List<Users> findUsersByUserEmail(String pUserLogin) throws Exception;

	@Query(value = "select u from org.littlered.dataservices.entity.wordpress.Users u where u.userActivationKey = ?1")
	List<Users> findUsersByUserActivationKey(String pUserActivationKey) throws Exception;

}
