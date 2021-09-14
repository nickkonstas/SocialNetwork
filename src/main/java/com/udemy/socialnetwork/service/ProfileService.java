package com.udemy.socialnetwork.service;


import com.udemy.socialnetwork.model.entity.AppUser;
import com.udemy.socialnetwork.model.entity.Profile;
import com.udemy.socialnetwork.model.repository.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileDao profileDao;

    //@PreAuthorize("isAuthenticated()")
    public void save(Profile profile) {
        profileDao.save(profile);
    }


    //@PreAuthorize("isAuthenticated()")
    public Profile getUserProfile(AppUser user) {
        return profileDao.findByUser(user);
    }
}
