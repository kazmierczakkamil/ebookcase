package pl.java.ebookcase.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {
    // do walidacji mozna uzyc @Pattern(regexp = "")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String login;
    private String name;
    private String surname;
    @Transient
    @NotNull
    @Size(min = 5, max = 15)
    private String password;
    @Transient
    @NotNull
    @Size(min = 5, max = 15)
    private String confirmPassword;
    private String email;
    private String passwordEncrypted;   // password in encrypted form
    private String passwordSalt;        // random number added to the password

    public User() {
    }

    public User(String name, String surname, String password, String confirmPassword, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
