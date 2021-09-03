package com.udemy.socialnetwork.model;


import org.owasp.html.PolicyFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.nio.file.Path;
import java.nio.file.Paths;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(name = "about", length = 5000)
    @Size(max = 5000, message = "{editprofile.about.size}")
    private String about;

    @Column(name = "photo_name",length = 20)
    private String photoName;

    @Column(name = "photo_directory", length = 10)
    private String photoDirectory;

    @Column(name = "photo_extension", length = 5)
    private String photoExtension;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void safeCopyFrom(Profile other) {
        if (other.about != null) {
            this.about = other.about;
        }
    }

    public void safeMergeFrom(Profile webProfile, PolicyFactory htmlPolicy) {
        this.about = htmlPolicy.sanitize(webProfile.about);
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDirectory() {
        return photoDirectory;
    }

    public void setPhotoDirectory(String photoDirectory) {
        this.photoDirectory = photoDirectory;
    }

    public String getPhotoExtension() {
        return photoExtension;
    }

    public void setPhotoExtension(String photoExtension) {
        this.photoExtension = photoExtension;
    }

    public void setPhotoDetails(FileInfo info) {
        photoDirectory = info.getSubDirectory();
        photoName = info.getBaseName();
        photoExtension = info.getExtension();
    }

    public Path getPhotoPath(String baseDirectory) {
        if (photoName == null) {
            return  null;
        }

        return Paths.get(baseDirectory, photoDirectory, photoName + "." + photoExtension);
    }
}
