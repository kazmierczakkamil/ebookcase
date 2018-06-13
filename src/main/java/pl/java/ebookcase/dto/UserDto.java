package pl.java.ebookcase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String login;
    private String name;
    private String surname;
    private Long bookcaseId;
    private String email;
    private String password;
    private String confirmPassword;
    private String passwordEncrypted;
    private String passwordSalt;
}
