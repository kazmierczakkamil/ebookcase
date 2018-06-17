package pl.java.ebookcase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "book")
    private Set<Review> reviews = new HashSet<>();

    public Book(String title, Author author, int amountOfPages, Category category) {
        this.title = title;
        this.author = author;
        this.amountOfPages = amountOfPages;
        this.category = category;

        log.info("Book created: " + this);
    }

}
