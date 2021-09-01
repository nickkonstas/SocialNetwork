package com.udemy.socialnetwork.service;


import com.udemy.socialnetwork.model.AppUser;
import com.udemy.socialnetwork.model.Profile;
import com.udemy.socialnetwork.repository.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileDao profileDao;

    public void save(Profile profile) {
        profileDao.save(profile);
    }

    public Profile getUserProfile(AppUser user) {
        return profileDao.findByUser(user);
    }
}
