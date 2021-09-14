package com.udemy.socialnetwork.tests.randomdata;


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
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SocialNetworkApplication.class)
//@Transactional

public class BulkTest {

    @Autowired
    private AppUserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private InterestService interestService;

    private static String namesFile = "names.txt";
    private static String interestsFile= "hobbies.txt";
    private List<String> loadFile (String filename, int maxLength) throws IOException {
        Path filePath = new ClassPathResource(filename).getFile().toPath();

        Stream<String> stream = Files.lines(filePath);
        List<String> result = stream
                .filter(line -> !line.isEmpty())
                .map(line -> line.trim())
                .filter(line -> line.length() <= maxLength)
                .map(line -> line.substring(0, 1).toUpperCase() + line.substring(1).toLowerCase())
                .collect(Collectors.toList());
        stream.close();
        return result;
    }

    //@Ignore
    @Test
    public void createTestData () throws IOException {

        Random random = new Random();

        List<String> interests = loadFile(interestsFile, 25);
        List<String> names = loadFile(namesFile, 25);

        for(int numUser = 0; numUser < 200; numUser++) {
            String firstName = names.get(random.nextInt(names.size()));
            String lastName = names.get(random.nextInt(names.size()));

            String email = firstName.toLowerCase(Locale.ROOT) + lastName.toLowerCase(Locale.ROOT) + "@example.com";
            if (userService.get(email) != null) {
                continue;
            }

            String password = "pass" + firstName.toLowerCase(Locale.ROOT);
            password = password.substring(0, Math.min(15, password.length()));

            assertTrue(password.length() <= 15);

            AppUser user = new AppUser(email, password, firstName, lastName);
            user.setEnabled(random.nextInt(5) != 0);

            userService.register(user);

            Profile profile = new Profile(user);

            int numOfInterests = random.nextInt(7);
            Set<Interest> userInterests = new HashSet<Interest>();

            for(int i = 0; i < numOfInterests; i++) {
                String interestText = interests.get(random.nextInt(interests.size()));
                Interest interest = interestService.createIfNotExists(interestText);

                userInterests.add(interest);
            }
            profile.setInterests(userInterests);
            profileService.save(profile);

            System.out.println(profile);
        }

        assertTrue(true);
    }
}
