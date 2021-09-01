package com.udemy.socialnetwork.model;


import org.owasp.html.PolicyFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
}
