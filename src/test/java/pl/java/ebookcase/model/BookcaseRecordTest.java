package pl.java.ebookcase.model;

import org.junit.Assert;
import org.junit.Test;
import javax.persistence.*;

import static org.junit.Assert.*;

public class BookcaseRecordTest {
    public BookcaseRecordTest() {

    }

    @Test
    public void typeAnnotations() {
        pl.java.ebookcase.model.AssertAnnotations.assertType(BookcaseRecord.class, Entity.class, Table.class);
    }

    @Test
    public void fieldAnnotations() {

        AssertAnnotations.assertField(BookcaseRecord.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(BookcaseRecord.class, "dateAdded");
        AssertAnnotations.assertField(BookcaseRecord.class, "book", ManyToOne.class, JoinColumn.class);
        AssertAnnotations.assertField(BookcaseRecord.class, "bookcase", ManyToOne.class, JoinColumn.class);
    }

    @Test
    public void entity() {
        Entity a = (Entity)ReflectTool.getClassAnnotation(BookcaseRecord.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = (Table)ReflectTool.getClassAnnotation(BookcaseRecord.class, Table.class);
        Assert.assertEquals("BOOKCASE_RECORDS", t.name());

    }

    @Test public void bookcaseRecordConstructorTest() {
        Author author = new Author("cos", "ktos");
        Category category = new Category("kat");
        Book book = new Book("jakas", author, 10, category);
        Bookcase bookcase = new Bookcase();
        BookcaseRecord bookcaseRecord = new BookcaseRecord(book, bookcase);
        assertEquals("jakas", bookcaseRecord.getBook().getTitle());
        assertEquals("cos", bookcaseRecord.getBook().getAuthor().getName());
        assertEquals("ktos", bookcaseRecord.getBook().getAuthor().getSurname());
        assertEquals(10, bookcaseRecord.getBook().getAmountOfPages());
        assertEquals("kat", bookcaseRecord.getBook().getCategory().getName());
        assertNotNull(bookcaseRecord.getBookcase());
    }

    @Test public void bookcaseRecordNoArgsConstructorTest() {
        BookcaseRecord bookcaseRecord = new BookcaseRecord();
        assertNull(bookcaseRecord.getId());
        assertNotNull(bookcaseRecord.getDateAdded());
        assertNull(bookcaseRecord.getBook());
        assertNull(bookcaseRecord.getBookcase());
    }
}