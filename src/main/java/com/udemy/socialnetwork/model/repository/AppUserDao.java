package com.udemy.socialnetwork.model.repository;


import com.udemy.socialnetwork.model.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserDao extends CrudRepository<AppUser, Long> {
    public AppUser findByEmail(String email);
    public AppUser getAppUserById(Long id);

}
