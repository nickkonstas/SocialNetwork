package com.udemy.socialnetwork.service;


import com.udemy.socialnetwork.model.StatusUpdate;
import com.udemy.socialnetwork.repository.StatusUpdateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StatusUpdateService {

    private final static int PAGESIZE = 3;

    @Autowired
    private StatusUpdateDao statusUpdateDao;

    public void save(StatusUpdate statusUpdate) {
        statusUpdateDao.save(statusUpdate);
    }

    public StatusUpdate getLatest() {
        return statusUpdateDao.findFirstByOrderByAddedDesc();
    }

    public Page<StatusUpdate> getPage(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGESIZE, Sort.Direction.DESC, "added");
        return statusUpdateDao.findAll(pageRequest);
    }

}
