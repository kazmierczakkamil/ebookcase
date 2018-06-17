package pl.java.ebookcase.model;

import org.junit.Assert;
import org.junit.Test;
import javax.persistence.*;

import static org.junit.Assert.*;

public class ReviewTest {
    public ReviewTest() {

    }

    @Test
    public void typeAnnotations() {
        pl.java.ebookcase.model.AssertAnnotations.assertType(Review.class, Entity.class, Table.class);
    }

    @Test
    public void fieldAnnotations() {

        AssertAnnotations.assertField(Review.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(Review.class, "content");
        AssertAnnotations.assertField(Review.class, "book", ManyToOne.class, JoinColumn.class);
        AssertAnnotations.assertField(Review.class, "user", ManyToOne.class, JoinColumn.class);
    }

    @Test
    public void entity() {
        Entity a = (Entity)ReflectTool.getClassAnnotation(Review.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = (Table)ReflectTool.getClassAnnotation(Review.class, Table.class);
        Assert.assertEquals("REVIEWS", t.name());

    }

    @Test public void reviewConstructorTest() {
        Author author = new Author("cos", "ktos");
        Category category = new Category("kat");
        Book book = new Book("jakas", author, 10, category);
        User user = new User("log", "name", "surname", "pass", "cpass", "mail");
        Review review = new Review("opis", book, user);
        assertEquals("opis", review.getContent());
        assertEquals("jakas", review.getBook().getTitle());
        assertEquals("cos", review.getBook().getAuthor().getName());
        assertEquals("ktos", review.getBook().getAuthor().getSurname());
        assertEquals(10, review.getBook().getAmountOfPages());
        assertEquals("kat", review.getBook().getCategory().getName());
        assertEquals("log", review.getUser().getLogin());
        assertEquals("name", review.getUser().getName());
        assertEquals("surname", review.getUser().getSurname());
    }

    @Test public void reviewNoArgsConstructorTest() {
        Review review = new Review();
        assertNull(review.getId());
        assertNull(review.getContent());
        assertNull(review.getBook());
        assertNull(review.getUser());
    }
}