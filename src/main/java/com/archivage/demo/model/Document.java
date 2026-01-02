package com.archivage.demo.model;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.data.mongodb.core.mapping.Document(collection ="documents")
public class Document {

    @Id
    private String id;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdAt;
    private byte[] file;


    public Document() {
    }

    public Document(String title, String content, String userId, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
    }
    public Document(String title, String content, String userId, LocalDateTime createdAt, byte[] file) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

