package com.example.bookshop.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "books")
@Data
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name", nullable = false, length = 30)
    private String bookName;

    @Column(name = "seq_num", unique = true, nullable = false)
    private Integer seqNum;

    @Column(name = "is_on_shelf")
    private Boolean isOnShelf;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private Author author;
}
