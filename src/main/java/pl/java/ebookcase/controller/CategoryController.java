package pl.java.ebookcase.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.java.ebookcase.service.CategoryService;

@AllArgsConstructor
@Controller
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.getCategories() );
        return "category/allCategories";
    }

}
