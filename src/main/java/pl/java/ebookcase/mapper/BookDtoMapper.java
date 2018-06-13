package pl.java.ebookcase.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.java.ebookcase.dto.BookDto;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.service.AuthorService;
import pl.java.ebookcase.service.CategoryService;

@AllArgsConstructor
@Component
public class BookDtoMapper implements DtoMapper<Book, BookDto> {

    private CategoryService categoryService;
    private AuthorService authorService;

    @Override
    public Book mapToEntity(BookDto dto) {
        return new Book(
                dto.getId(),
                dto.getTitle(),
                dto.getAmountOfPages(),
                categoryService.getCategoryById(dto.getCategoryId()),
                authorService.getAuthorById(dto.getAuthorId())
        );
    }

    @Override
    public BookDto mapToDto(Book entity) {
        return new BookDto(
                entity.getId(),
                entity.getTitle(),
                entity.getAmountOfPages(),
                entity.getCategory().getId(),
                entity.getAuthor().getId()
        );
    }
}
