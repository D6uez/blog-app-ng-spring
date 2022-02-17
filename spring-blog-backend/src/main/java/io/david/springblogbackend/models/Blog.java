package io.david.springblogbackend.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Blog {

    private enum Topic {
        GENERAL,
        TECH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String body;
    private Timestamp publishedDate;
    private Timestamp updatedDate;
    private Long userId;

    @Enumerated
    private Topic topic;

    public Blog() {
    }

    public Blog(Long id, String title, String body, Timestamp publishedDate, Timestamp updatedDate, Long userId,
            Topic topic) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.publishedDate = publishedDate;
        this.updatedDate = updatedDate;
        this.userId = userId;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getPublishedDate() {
        return publishedDate;
    }

    @PrePersist
    protected void onCreate() {
        publishedDate = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Timestamp(System.currentTimeMillis());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
