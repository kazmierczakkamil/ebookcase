package pl.java.ebookcase.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.java.ebookcase.model.Category;
import pl.java.ebookcase.service.CategoryService;

import java.util.List;

@AllArgsConstructor
@Controller
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categorires(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "categoriesList";
    }

    @GetMapping("/addCategory")
    public String newBook(@ModelAttribute("category") Category category, Model model) {
        return "newCategory";
    }
}
