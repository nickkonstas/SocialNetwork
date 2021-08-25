package com.udemy.socialnetwork.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import com.udemy.socialnetwork.SocialNetworkApplication;
import com.udemy.socialnetwork.model.StatusUpdate;
import com.udemy.socialnetwork.repository.StatusUpdateDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SocialNetworkApplication.class)
@Transactional
public class StatusTest {

    @Autowired
    private StatusUpdateDao statusUpdateDao;

    @Test
    public void testSave() {

        StatusUpdate status = new StatusUpdate("This is a test status update");
        statusUpdateDao.save(status);

        assertNotNull("No null id", status.getId());
        assertNotNull("No null date", status.getAdded());
    }

    @Test
    public void testFindLatest() {

        Calendar calendar = Calendar.getInstance();

        StatusUpdate lastStatusUpdate = null;

        for(int i=0; i<10; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);

            StatusUpdate status = new StatusUpdate("Status update " + i, calendar.getTime());

            statusUpdateDao.save(status);

            lastStatusUpdate = status;
        }

        StatusUpdate retrieved = statusUpdateDao.findFirstByOrderByAddedDesc();

        assertEquals("Latest status update", lastStatusUpdate, retrieved);
    }
}
