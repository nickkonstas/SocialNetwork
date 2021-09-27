package com.udemy.socialnetwork.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;



import com.udemy.socialnetwork.SocialNetworkApplication;
import com.udemy.socialnetwork.model.entity.AppUser;
import com.udemy.socialnetwork.model.entity.Interest;
import com.udemy.socialnetwork.model.entity.Profile;
import com.udemy.socialnetwork.service.AppUserService;
import com.udemy.socialnetwork.service.InterestService;
import com.udemy.socialnetwork.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SocialNetworkApplication.class)
@Transactional
@TestPropertySource(locations = "classpath:test.properties")

public class ProfileTest {


    @Autowired
    private AppUserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private InterestService interestService;


    private AppUser[] users = {
            new AppUser("test12@emample.com", "hello", "maria", "nikolaidi"),
            new AppUser("test13@emample.com", "hello", "giannis", "agiannis"),
            new AppUser("test14@emample.com", "hello", "predrak", "djordjevic"),
    };

    private String[][] interests = {
            {"music", "guitar_xxx", "plants"},
            {"music", "music", "philosophy123"},
            {"philosophy123", "football"}
    };


    @Test
    public void testInterests() {

        for(int i = 0; i < users.length; i++) {
            AppUser user = users[i];
            String[] interestArray = interests[i];

            userService.register(user);

            Set<Interest> interestList = new HashSet<>();

            for(String interestText:interestArray) {
                Interest interest = interestService.createIfNotExists(interestText);
                interestList.add(interest);

                assertNotNull("Interest should not be null", interest);
                assertNotNull("Interest should have ID", interest.getId());

                assertEquals("Text should match", interestText, interest.getName());
            }

            Profile profile = new Profile(user);
            profile.setInterests(interestList);
            profileService.save(profile);

            Profile retrievedProfile = profileService.getUserProfile(user);

            assertEquals("Interest sets should match", interestList, retrievedProfile.getInterests());
        }

    }
}
