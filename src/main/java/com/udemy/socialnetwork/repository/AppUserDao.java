package com.udemy.socialnetwork.repository;


import com.udemy.socialnetwork.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserDao extends JpaRepository<AppUser, Long> {
    public AppUser findByEmail(String email);

}
