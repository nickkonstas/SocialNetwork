package com.udemy.socialnetwork.repository;


import com.udemy.socialnetwork.model.AppUser;
import com.udemy.socialnetwork.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends CrudRepository<Profile, Long> {

    Profile findByUser(AppUser user);
}
