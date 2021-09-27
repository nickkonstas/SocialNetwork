package com.udemy.socialnetwork.tests;


import com.udemy.socialnetwork.SocialNetworkApplication;
import com.udemy.socialnetwork.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SocialNetworkApplication.class)
@Transactional
@TestPropertySource(locations = "classpath:test.properties")

public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Value("${photo.upload.directory}")
    private String photoUploadDirectory;

    @Test
    public void testGetExtension() throws Exception{

        //Using Reflection to access private methods in FileService.class
        Method method = FileService.class.getDeclaredMethod("getFileExtension", String.class);
        method.setAccessible(true);

        assertEquals("png", (String)method.invoke(fileService, "test.png"), "Should be png");
        assertEquals("doc", (String)method.invoke(fileService, "s.doc"), "Should be doc");
        assertEquals("jpeg", (String)method.invoke(fileService, "test.jpeg"), "Should be jpeg");
        assertEquals(null, (String)method.invoke(fileService, "test"), "Should be null");

    }

    @Test
    public void testIsImageExtension() throws Exception{

        //Using Reflection to access private methods in FileService.class
        Method method = FileService.class.getDeclaredMethod("isImageExtension", String.class);
        method.setAccessible(true);

        assertTrue((Boolean)method.invoke(fileService, "png"), "png should be valid" );
        assertTrue((Boolean)method.invoke(fileService, "PNG"), "PNG should be valid" );
        assertTrue((Boolean)method.invoke(fileService, "jpg"), "jpg should be valid" );
        assertTrue((Boolean)method.invoke(fileService, "gif"), "gif should be valid" );
        assertTrue((Boolean)method.invoke(fileService, "GIF"), "GIF should be valid" );
        assertFalse((Boolean)method.invoke(fileService, "doc"), "doc should be invalid" );
        assertFalse((Boolean)method.invoke(fileService, "gify"), "gify should be invalid" );
        assertFalse((Boolean)method.invoke(fileService, "df"), "df should be invalid" );
    }

    @Test
    public void testCreateDirectory() throws Exception{

        //Using Reflection to access private methods in FileService.class
        Method method = FileService.class.getDeclaredMethod("makeSubDirectory", String.class, String.class);
        method.setAccessible(true);

        for(int i = 0; i < 1000; i++) {

            File created = (File)method.invoke(fileService, photoUploadDirectory, "photo");

            assertTrue(created.exists(), "Directory should exist" + created.getAbsolutePath());
        }


    }
}
