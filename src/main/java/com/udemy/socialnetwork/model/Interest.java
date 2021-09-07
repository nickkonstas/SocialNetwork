package com.udemy.socialnetwork.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "interests")
public class Interest implements Comparable<Interest>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="interest_name", unique = true, length = 25)
    private String name;

    public Interest(String name) {
        this.name = name;
    }

    public Interest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interest interest = (Interest) o;
        return name.equals(interest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Interest interest) {
        return this.name.compareTo(interest.name);
    }
}
