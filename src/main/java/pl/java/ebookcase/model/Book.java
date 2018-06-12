package pl.java.ebookcase.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private int pages;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "CATEGORY_ID_FK"))
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "AUTHOR_ID_FK"))
    private Author author;


    public Book(Integer id, String title, Author author, int pages, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.category = category;
    }

    public Book(Book bookToCopy) {
        this(
                bookToCopy.getId(),
                bookToCopy.getTitle(),
                bookToCopy.getAuthor(),
                bookToCopy.getPages(),
                bookToCopy.getCategory()
        );
    }

}
