package com.udemy.socialnetwork.service;


import com.udemy.socialnetwork.model.Interest;
import com.udemy.socialnetwork.repository.InterestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestService {

    @Autowired
    private InterestDao interestDao;

    public long count(Interest interest) {
        return interestDao.count();
    }

    public Interest get(String interestName) {
        return interestDao.findByName(interestName);
    }

    public void save(Interest interest) {
        interestDao.save(interest);
    }

    public Interest createIfNotExists(String interestText) {
        Interest interest = interestDao.findByName(interestText);

        if(interest == null) {
            interest = new Interest(interestText);
            interestDao.save(interest);
        }

        return interest;
    }
}
