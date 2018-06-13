package pl.java.ebookcase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
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


    public User(String login, String name, String surname, String password, String confirmPassword, String email) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        log.info("User created: " + this);
    }

}
