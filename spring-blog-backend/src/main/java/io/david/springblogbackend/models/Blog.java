package io.david.springblogbackend.models;

import java.sql.Timestamp;

import javax.persistence.Column;
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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    private Timestamp publishedDate;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private Timestamp updatedDate;
    private String author;

    @Enumerated
    private Topic topic;

    public Blog() {
    }

    public Blog(Long id, String title, String body, Timestamp publishedDate, Timestamp updatedDate, String author,
            Topic topic) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.publishedDate = publishedDate;
        this.updatedDate = updatedDate;
        this.author = author;
        this.topic = topic;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
