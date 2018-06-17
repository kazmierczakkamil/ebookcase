package pl.java.ebookcase.model;

import org.junit.Assert;
import org.junit.Test;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class BookTest {
    public BookTest() {

    }

    @Test
    public void typeAnnotations() {
        pl.java.ebookcase.model.AssertAnnotations.assertType(Author.class, Entity.class, Table.class);
    }

    @Test
    public void fieldAnnotations() {

        AssertAnnotations.assertField(Book.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(Book.class, "title");
        AssertAnnotations.assertField(Book.class, "amountOfPages");
        AssertAnnotations.assertField(Book.class, "category", ManyToOne.class, JoinColumn.class);
        AssertAnnotations.assertField(Book.class, "author", ManyToOne.class, JoinColumn.class);
        AssertAnnotations.assertField(Book.class, "reviews", OneToMany.class);
    }

    @Test
    public void entity() {
        Entity a = (Entity)ReflectTool.getClassAnnotation(Book.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = (Table)ReflectTool.getClassAnnotation(Book.class, Table.class);
        Assert.assertEquals("BOOKS", t.name());

    }

    @Test public void bookConstructorTest() {
        Author author = new Author("cos", "ktos");
        Category category = new Category("kat");
        Book book = new Book("jakas", author, 10, category);
        assertEquals("jakas", book.getTitle());
        assertEquals("cos", book.getAuthor().getName());
        assertEquals("ktos", book.getAuthor().getSurname());
        assertEquals(10, book.getAmountOfPages());
        assertEquals("kat", book.getCategory().getName());
    }

    @Test public void bookNoArgsConstructorTest() {
        Book book = new Book();
        assertNull(book.getTitle());
        assertNull(book.getAuthor());
        assertEquals(0, book.getAmountOfPages());
        assertNull(book.getCategory());
        assertEquals(0, book.getReviews().size());
    }

    @Test public void bookAllArgsConstructorTest() {
        Author author = new Author("cos", "ktos");
        Category category = new Category("kat");
        Set<Review> reviews = new HashSet<>();
        Review review = new Review();
        reviews.add(review);
        Book book = new Book(1L,"jakas",10 , category, author, reviews);
        assertEquals(Long.valueOf(1L), book.getId());
        assertEquals("jakas", book.getTitle());
        assertEquals("cos", book.getAuthor().getName());
        assertEquals("ktos", book.getAuthor().getSurname());
        assertEquals(10, book.getAmountOfPages());
        assertEquals("kat", book.getCategory().getName());
        assertNotNull(book.getReviews());
    }
}