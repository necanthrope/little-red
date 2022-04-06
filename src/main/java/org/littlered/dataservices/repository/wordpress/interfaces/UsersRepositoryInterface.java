package org.littlered.dataservices.repository.wordpress.interfaces;

import org.littlered.dataservices.entity.wordpress.Users;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by Jeremy on 3/25/2017.
 */
@Repository
public interface UsersRepositoryInterface extends PagingAndSortingRepository<Users, Long> {

	ArrayList<Users> findById(Long id);

	ArrayList<Users> findByUserLogin(String userName);

	ArrayList<Users> findByUserEmail(String userEmail);

}
