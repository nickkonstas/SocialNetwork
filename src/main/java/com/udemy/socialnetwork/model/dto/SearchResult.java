package com.udemy.socialnetwork.model.dto;

import com.udemy.socialnetwork.model.entity.Interest;
import com.udemy.socialnetwork.model.entity.Profile;

import java.util.Set;

public class SearchResult {

    private Long userId;
    private String firstName;
    private String lastName;
    private Set<Interest> interests;

    public SearchResult(Profile profile) {
        userId = profile.getUser().getId();
        firstName = profile.getUser().getFirstName();
        lastName = profile.getUser().getLastName();
        interests = profile.getInterests();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", interests=" + interests +
                '}';
    }
}
