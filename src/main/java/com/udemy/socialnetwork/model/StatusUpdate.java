package com.udemy.socialnetwork.model;

import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="status_update")
public class StatusUpdate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Size(min = 5, max = 255, message = "{addStatus.text.size}")
    @Column(name = "text")
    private String text;

    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;

    @PrePersist
    protected void onCreate() {
        if (added == null) {
            added = new Date();
        }
    }

    public StatusUpdate() {
    }

    public StatusUpdate(String text) {
        this.text = text;
    }

    public StatusUpdate(String text, Date added) {
        this.text = text;
        this.added = added;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusUpdate that = (StatusUpdate) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) && Objects.equals(added, that.added);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, added);
    }

    @Override
    public String toString() {
        return "StatusUpdate{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", added=" + added +
                '}';
    }
}
