package pl.java.ebookcase.model;

import org.junit.Assert;
import org.junit.Test;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class CategoryTest {
    public CategoryTest() {

    }

    @Test
    public void typeAnnotations() {
        pl.java.ebookcase.model.AssertAnnotations.assertType(Category.class, Entity.class, Table.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(Category.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(Category.class, "name");
        AssertAnnotations.assertField(Category.class, "books", OneToMany.class);
    }

    @Test
    public void entity() {
        Entity a = (Entity)ReflectTool.getClassAnnotation(Category.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = (Table)ReflectTool.getClassAnnotation(Category.class, Table.class);
        Assert.assertEquals("CATEGORIES", t.name());

    }

    @Test public void categoryConstructorTest() {
        Category category = new Category("kat");
        assertEquals("kat", category.getName());
    }

    @Test public void categoryNoArgsConstructorTest() {
        Category category = new Category();
        assertNull(category.getName());
    }

    @Test public void categoryAllArgsConstructorTest() {
        Set<Book> books = new HashSet<>();
        Book book = new Book();
        books.add(book);
        Category category = new Category(1L, "kat", books);
        assertEquals(Long.valueOf(1L), category.getId());
        assertEquals("kat", category.getName());
        assertEquals(books, category.getBooks());
    }
}