package com.udemy.socialnetwork.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification_token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "token")
    private String token;

    @JoinColumn(name = "user_id" , nullable = false)
    @OneToOne(targetEntity = AppUser.class)
    private AppUser user;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiry;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType type;

    @PrePersist
    private void setDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 60);
        expiry = c.getTime();
    }

    public VerificationToken(String token, AppUser user, TokenType type) {
        this.token = token;
        this.user = user;
        this.type = type;
    }

    public VerificationToken() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
}
