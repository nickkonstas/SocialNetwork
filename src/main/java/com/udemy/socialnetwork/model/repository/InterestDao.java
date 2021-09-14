package com.udemy.socialnetwork.model.repository;

import com.udemy.socialnetwork.model.entity.Interest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InterestDao extends CrudRepository<Interest, Long> {

    Interest findByName(String name);
}
