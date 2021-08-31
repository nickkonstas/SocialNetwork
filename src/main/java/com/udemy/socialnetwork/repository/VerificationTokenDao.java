package com.udemy.socialnetwork.repository;


import com.udemy.socialnetwork.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenDao extends CrudRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
}
