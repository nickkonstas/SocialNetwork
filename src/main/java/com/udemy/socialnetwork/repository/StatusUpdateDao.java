package com.udemy.socialnetwork.repository;

import com.udemy.socialnetwork.model.StatusUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusUpdateDao extends CrudRepository<StatusUpdate, Long> {
    StatusUpdate findFirstByOrderByAddedDesc();
}
