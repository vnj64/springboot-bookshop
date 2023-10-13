package com.example.bookshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String patronymic;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    public String biography;
}
