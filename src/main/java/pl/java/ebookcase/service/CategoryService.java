package pl.java.ebookcase.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Book;
import pl.java.ebookcase.model.Category;
import pl.java.ebookcase.repository.CategoryRepository;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }
    public Category getCategoryByBooks(Set<Book> books) {
        return categoryRepository.findCategoryByBooks(books);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
