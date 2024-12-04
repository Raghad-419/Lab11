package com.example.blog_system.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "Empty category id")
    private Integer categoryId;
    @Column(columnDefinition = "varchar(60) not null")
    @NotEmpty(message = "Empty title")
    private String title;
    @Column(columnDefinition = "varchar(200) not null")
    @NotEmpty(message = "Empty content")
    private String content;
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishDate;



    public Post(Integer id, Integer categoryId, String title, String content, Integer userId, LocalDateTime publishDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.publishDate = publishDate;
    }

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Empty category id") Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull(message = "Empty category id") Integer categoryId) {
        this.categoryId = categoryId;
    }

    public @NotEmpty(message = "Empty title") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Empty title") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Empty content") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "Empty content") String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
}
