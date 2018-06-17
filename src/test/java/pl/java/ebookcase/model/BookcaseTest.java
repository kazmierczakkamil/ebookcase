package pl.java.ebookcase.model;

import org.junit.Assert;
import org.junit.Test;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class BookcaseTest {
    public BookcaseTest() {

    }

    @Test
    public void typeAnnotations() {
        pl.java.ebookcase.model.AssertAnnotations.assertType(Bookcase.class, Entity.class, Table.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(Bookcase.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(Bookcase.class, "user", OneToOne.class, JoinColumn.class);
        AssertAnnotations.assertField(Bookcase.class, "records", OneToMany.class);
    }

    @Test
    public void entity() {
        Entity a = (Entity)ReflectTool.getClassAnnotation(Bookcase.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = (Table)ReflectTool.getClassAnnotation(Bookcase.class, Table.class);
        Assert.assertEquals("BOOKCASES", t.name());

    }

    @Test public void bookcaseNoArgsConstructorTest() {
        Bookcase bookcase = new Bookcase();
        assertNull(bookcase.getUser());
        assertEquals(0, bookcase.getRecords().size());
    }

    @Test public void authorAllArgsConstructorTest() {
        Set<BookcaseRecord> bookcaseRecords = new HashSet<>();
        BookcaseRecord bookcaseRecord = new BookcaseRecord();
        bookcaseRecords.add(bookcaseRecord);
        User user = new User("log", "n", "s", "123", "123", "@");
        Bookcase bookcase = new Bookcase(1L, user, bookcaseRecords);
        assertEquals(Long.valueOf(1L), bookcase.getId());
        assertEquals("log", bookcase.getUser().getLogin());
        assertEquals("n", bookcase.getUser().getName());
        assertEquals("s", bookcase.getUser().getSurname());
        assertEquals("123", bookcase.getUser().getPassword());
        assertEquals("123", bookcase.getUser().getConfirmPassword());
        assertEquals("@", bookcase.getUser().getEmail());
        assertEquals(bookcaseRecords, bookcase.getRecords());
    }
}