package com.udemy.socialnetwork.model.repository;


import com.udemy.socialnetwork.model.entity.AppUser;
import com.udemy.socialnetwork.model.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileDao extends CrudRepository<Profile, Long> {

    Profile findByUser(AppUser user);

    List<Profile> findByInterestsNameContainingIgnoreCase(String text);
    Page<Profile> findByInterestsNameContainingIgnoreCase(String text, Pageable request);

}
