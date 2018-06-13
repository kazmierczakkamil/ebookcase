package pl.java.ebookcase.services;

import org.springframework.stereotype.Service;
import pl.java.ebookcase.model.Category;
import pl.java.ebookcase.repositories.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
}
