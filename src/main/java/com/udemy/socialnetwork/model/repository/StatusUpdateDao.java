package com.udemy.socialnetwork.model.repository;

import com.udemy.socialnetwork.model.entity.StatusUpdate;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusUpdateDao extends PagingAndSortingRepository<StatusUpdate, Long> {
    StatusUpdate findFirstByOrderByAddedDesc();
}
