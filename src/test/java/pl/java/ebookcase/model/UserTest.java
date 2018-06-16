package pl.java.ebookcase.model;

import org.junit.Assert;
import org.junit.Test;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class UserTest {
    public UserTest() {

    }

    @Test
    public void typeAnnotations() {
        pl.java.ebookcase.model.AssertAnnotations.assertType(User.class, Entity.class, Table.class);
    }

    @Test
    public void fieldAnnotations() {
        AssertAnnotations.assertField(User.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(User.class, "login");
        AssertAnnotations.assertField(User.class, "name");
        AssertAnnotations.assertField(User.class, "surname");
        AssertAnnotations.assertField(User.class, "bookcase", OneToOne.class);
        AssertAnnotations.assertField(User.class, "password", Transient.class, NotNull.class, Size.class);
        AssertAnnotations.assertField(User.class, "confirmPassword", Transient.class, NotNull.class, Size.class);
        AssertAnnotations.assertField(User.class, "email");
        AssertAnnotations.assertField(User.class, "passwordEncrypted");
    }

    @Test
    public void entity() {
        Entity a = (Entity)ReflectTool.getClassAnnotation(User.class, Entity.class);
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        Table t = (Table)ReflectTool.getClassAnnotation(User.class, Table.class);
        Assert.assertEquals("USERS", t.name());

    }

    @Test public void userConstructorTest() {
        User user = new User("log", "name", "surname", "pass", "cpass", "mail");
        assertEquals("log", user.getLogin());
        assertEquals("name", user.getName());
        assertEquals("surname", user.getSurname());
        assertEquals("pass", user.getPassword());
        assertEquals("cpass", user.getConfirmPassword());
        assertEquals("mail", user.getEmail());
    }

    @Test public void userNoArgsConstructorTest() {
        User user = new User();
        assertNull(user.getLogin());
        assertNull(user.getName());
        assertNull(user.getSurname());
        assertNull(user.getPassword());
        assertNull(user.getConfirmPassword());
        assertNull(user.getEmail());
    }

    @Test public void userAllArgsConstructorTest() {
        User user0 = new User("log", "name", "surname", "pass", "cpass", "mail");
        Set<Book> books = new HashSet<>();
        Book book = new Book();
        books.add(book);
        Bookcase bookcase = new Bookcase(1L, user0, books);
        User user = new User(1L, "log", "name", "surname", bookcase, "pass", "cpass", "mail", "passen");
        assertEquals(Long.valueOf(1L), user.getId());
        assertEquals("log", user.getLogin());
        assertEquals("name", user.getName());
        assertEquals("surname", user.getSurname());
        assertEquals(bookcase, user.getBookcase());
        assertEquals("pass", user.getPassword());
        assertEquals("cpass", user.getConfirmPassword());
        assertEquals("mail", user.getEmail());
        assertEquals("passen", user.getPasswordEncrypted());
    }
}