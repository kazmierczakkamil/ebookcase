package pl.java.ebookcase.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.java.ebookcase.dto.UserDto;
import pl.java.ebookcase.model.User;
import pl.java.ebookcase.service.BookcaseService;

@AllArgsConstructor
@Component
public class UserDtoMapper implements DtoMapper<User, UserDto> {

    private BookcaseService bookcaseService;

    @Override
    public User mapToEntity(UserDto dto) {
        return new User(
                dto.getId(),
                dto.getLogin(),
                dto.getName(),
                dto.getSurname(),
                bookcaseService.getBookcaseById(dto.getBookcaseId()),
                dto.getPassword(),
                dto.getConfirmPassword(),
                dto.getEmail(),
                dto.getPasswordEncrypted(),
                dto.getPasswordSalt()
        );
    }

    @Override
    public UserDto mapToDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getLogin(),
                entity.getName(),
                entity.getSurname(),
                entity.getBookcase().getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getConfirmPassword(),
                entity.getPasswordEncrypted(),
                entity.getPasswordSalt()
        );
    }
}
