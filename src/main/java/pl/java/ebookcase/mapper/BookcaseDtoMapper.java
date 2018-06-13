package pl.java.ebookcase.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.java.ebookcase.dto.BookcaseDto;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Bookcase;
import pl.java.ebookcase.service.BookService;
import pl.java.ebookcase.service.UserService;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class BookcaseDtoMapper implements DtoMapper<Bookcase, BookcaseDto> {

    private UserService userService;
    private BookService bookService;

    @Override
    public Bookcase mapToEntity(BookcaseDto dto) {
        return new Bookcase(
                dto.getId(),
                userService.getUserById(dto.getUserId()),
                bookService.getBooksById(dto.getBookIds())
        );
    }

    @Override
    public BookcaseDto mapToDto(Bookcase entity) {
        return new BookcaseDto(
                entity.getId(),
                entity.getUser().getId(),
                entity.getBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toSet())
        );
    }
}
