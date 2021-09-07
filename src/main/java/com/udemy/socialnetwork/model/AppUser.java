package com.udemy.socialnetwork.model;

import com.udemy.socialnetwork.validation.PasswordMatch;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@PasswordMatch(message = "{register.repeatpassword.mismatch}")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    @Email(message = "{register.email.invalid}")
    @NotBlank(message = "{register.email.invalid}")
    private String email;

    @Transient
    @Size(min = 5, max = 15, message = "{register.password.size}")
    private String plainPassword;

    @Transient
    private String repeatPassword;

    @Column(name = "password")
    private String password;

    @Column(name= "role", length = 20)
    private String role;

    @Column(name = "enabled")
    private Boolean enabled = false;

    public AppUser() {

    }

    public AppUser(String email, String password) {
        this.email = email;
        this.plainPassword = password;
        this.repeatPassword = password;
        this.enabled = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.password = new BCryptPasswordEncoder().encode(plainPassword);
        this.plainPassword = plainPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
