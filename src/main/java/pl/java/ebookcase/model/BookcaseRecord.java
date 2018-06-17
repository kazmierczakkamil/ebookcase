package pl.java.ebookcase.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOKCASE_RECORDS")
public class BookcaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateAdded = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "bookcase_id")
    private Bookcase bookcase;

    public BookcaseRecord(Book book, Bookcase bookcase) {
        this.book = book;
        this.bookcase = bookcase;
    }
}
