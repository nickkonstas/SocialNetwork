package com.udemy.socialnetwork.service;


import com.udemy.socialnetwork.model.entity.StatusUpdate;
import com.udemy.socialnetwork.model.repository.StatusUpdateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusUpdateService {

    @Value("${status.pagesize}")
    private int pageSize;

    @Autowired
    private StatusUpdateDao statusUpdateDao;

    public void save(StatusUpdate statusUpdate) {
        statusUpdateDao.save(statusUpdate);
    }

    public StatusUpdate getLatest() {
        return statusUpdateDao.findFirstByOrderByAddedDesc();
    }

    public Page<StatusUpdate> getPage(int pageNumber) {

        //pageNumber - 1 because it's zero based
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by("added").descending());
        return statusUpdateDao.findAll(pageRequest);
    }

    public void delete(Long id) {
        statusUpdateDao.deleteById(id);
    }

    public Optional<StatusUpdate> get(Long id) {
       return statusUpdateDao.findById(id);
    }
}
