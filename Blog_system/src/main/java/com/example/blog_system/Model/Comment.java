package com.example.blog_system.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "Empty user id")
    private Integer userId ;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "Empty post id")
    private Integer postId;
    @Column(columnDefinition = "varchar(80) not null")
    @NotEmpty(message = "Empty content")
    private String content;
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentDate;



    public Comment(Integer id, Integer userId, Integer postId, String content, LocalDateTime commentDate) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.commentDate = commentDate;
    }

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Empty user id") Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "Empty user id") Integer userId) {
        this.userId = userId;
    }

    public @NotNull(message = "Empty post id") Integer getPostId() {
        return postId;
    }

    public void setPostId(@NotNull(message = "Empty post id") Integer postId) {
        this.postId = postId;
    }

    public @NotEmpty(message = "Empty content") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "Empty content") String content) {
        this.content = content;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }
}
