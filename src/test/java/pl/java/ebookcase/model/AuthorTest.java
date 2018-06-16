package pl.java.ebookcase.model;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AuthorTest {
    public AuthorTest() {

    }

    @Test
    public void typeAnnotations() {
        //, Data.class, Slf4j.class, NoArgsConstructor.class, AllArgsConstructor.class
        pl.java.ebookcase.model.AssertAnnotations.assertType(Author.class, Entity.class, Table.class);
    }

    @Test
    public void fieldAnnotations() {

        AssertAnnotations.assertField(Author.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(Author.class, "name");
        AssertAnnotations.assertField(Author.class, "surname");
        AssertAnnotations.assertField(Author.class, "books", OneToMany.class);
    }

    @Test
    public void entity() {
        Entity a = (Entity)ReflectTool.getClassAnnotation(Author.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = (Table)ReflectTool.getClassAnnotation(Author.class, Table.class);
        Assert.assertEquals("AUTHORS", t.name());

    }

    @Test public void authorConstructorTest() {
        Author author = new Author("cos", "ktos");
        assertEquals("cos", author.getName());
        assertEquals("ktos", author.getSurname());
    }

    @Test public void authorNoArgsConstructorTest() {
        Author author = new Author();
        assertNull(author.getName());
        assertNull(author.getSurname());
    }

    @Test public void authorAllArgsConstructorTest() {
        Set<Book> books = new HashSet<>();
        Book book = new Book();
        books.add(book);
        Author author = new Author(1L,"cos", "ktos", books);
        assertEquals(Long.valueOf(1L), author.getId());
        assertEquals("cos", author.getName());
        assertEquals("ktos", author.getSurname());
        assertEquals(books, author.getBooks());
    }
}