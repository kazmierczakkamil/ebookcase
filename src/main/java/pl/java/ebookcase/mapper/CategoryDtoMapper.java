package pl.java.ebookcase.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.java.ebookcase.dto.CategoryDto;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Category;
import pl.java.ebookcase.service.BookService;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CategoryDtoMapper implements DtoMapper<Category, CategoryDto> {

    private BookService bookService;

    @Override
    public Category mapToEntity(CategoryDto dto) {
        return new Category(
                dto.getId(),
                dto.getName(),
                bookService.getBooksByCategoryId(dto.getId())
        );
    }

    @Override
    public CategoryDto mapToDto(Category entity) {
        return new CategoryDto(
                entity.getId(),
                entity.getName(),
                entity.getBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toSet())
        );
    }
}
