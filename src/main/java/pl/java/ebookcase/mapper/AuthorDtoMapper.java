package pl.java.ebookcase.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.java.ebookcase.dto.AuthorDto;
import pl.java.ebookcase.model.Author;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.service.BookService;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AuthorDtoMapper implements DtoMapper<Author, AuthorDto> {

    private BookService bookService;

    @Override
    public Author mapToEntity(AuthorDto dto) {
        return new Author(
                dto.getId(),
                dto.getName(),
                dto.getSurname(),
                bookService.getBooksByAuthorId(dto.getId())
        );
    }

    @Override
    public AuthorDto mapToDto(Author entity) {
        return new AuthorDto(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toSet())
        );
    }
}
