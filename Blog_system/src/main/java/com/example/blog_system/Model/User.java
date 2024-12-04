package com.example.blog_system.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;


@Entity
@Check(constraints = "LENGTH(username) > 4 AND LENGTH(password) >= 7")
public class User {
    public User(Integer id, String username, String password, String email, LocalDateTime registrationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(10) not null")
    @NotEmpty(message = "Empty username")
    @Size(min = 5 ,message = "User name must be at least 5")
    private String username;
    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "Empty password")
    @Size(min = 7,message = "Password length must be at least 7")
    private String password;
    @Column(columnDefinition = "varchar(25) not null unique")
    @Email(message = "Enter valid email")
    @NotEmpty(message = "Empty email")
    private String email;
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "Empty username") @Size(min = 5, message = "User name must be at least 5") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Empty username") @Size(min = 5, message = "User name must be at least 5") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "Empty password") @Size(min = 7, message = "Password length must be at least 7") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Empty password") @Size(min = 7, message = "Password length must be at least 7") String password) {
        this.password = password;
    }

    public @Email(message = "Enter valid email") @NotEmpty(message = "Empty email") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Enter valid email") @NotEmpty(message = "Empty email") String email) {
        this.email = email;
    }

    public @FutureOrPresent(message = "Date should be present or future") LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(@FutureOrPresent(message = "Date should be present or future") LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}

