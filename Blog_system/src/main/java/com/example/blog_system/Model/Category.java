package com.example.blog_system.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(10) not null unique")
    @NotEmpty(message = "Empty Category")
    private String name;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "Empty Category") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Empty Category") String name) {
        this.name = name;
    }
}

