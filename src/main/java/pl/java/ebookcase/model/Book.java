package pl.java.ebookcase.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int amountOfPages;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(mappedBy = "books")
    private Set<Bookcase> bookcases = new HashSet<>();

    public Book(String title, Author author, int amountOfPages, Category category) {
        this.title = title;
        this.author = author;
        this.amountOfPages = amountOfPages;
        this.category = category;
    }

}
