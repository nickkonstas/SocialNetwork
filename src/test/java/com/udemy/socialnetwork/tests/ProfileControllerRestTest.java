package com.udemy.socialnetwork.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.udemy.socialnetwork.SocialNetworkApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.udemy.socialnetwork.model.Interest;
import com.udemy.socialnetwork.model.Profile;
import com.udemy.socialnetwork.model.AppUser;
import com.udemy.socialnetwork.service.InterestService;
import com.udemy.socialnetwork.service.ProfileService;
import com.udemy.socialnetwork.service.AppUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = SocialNetworkApplication.class)
@Transactional
public class ProfileControllerRestTest {

    @Autowired
    private AppUserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private InterestService interestService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    @WithUserDetails("someone@example.com")
    public void testSaveAndDeleteInterest() throws Exception {

        String interestText = "some interest_here";

        mockMvc.perform(post("/save-interest").param("name", interestText)).andExpect(status().isOk());

        Interest interest = interestService.get(interestText);

        assertNotNull("Interest should exist", interest);
        assertEquals("Retrieved interest text should match", interestText, interest.getName());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        AppUser user = userService.get(email);
        Profile profile = profileService.getUserProfile(user);

        assertTrue("Profile should contain interest", profile.getInterests().contains(new Interest(interestText)));

        mockMvc.perform(post("/delete-interest").param("name", interestText)).andExpect(status().isOk());

        profile = profileService.getUserProfile(user);

        assertFalse("Profile should not contain interest", profile.getInterests().contains(new Interest(interestText)));
    }
}
