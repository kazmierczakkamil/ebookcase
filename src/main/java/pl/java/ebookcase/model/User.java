package pl.java.ebookcase.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String name;
    private String surname;
    @OneToOne(mappedBy = "user")
    private Bookcase bookcase;
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

    public User(String name, String surname, String password, String confirmPassword, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

}
