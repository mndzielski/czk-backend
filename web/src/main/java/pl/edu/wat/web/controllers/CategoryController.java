package pl.edu.wat.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.backend.service.CategoryService;
import pl.edu.wat.domain.entity.Category;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {

    private final CategoryService service;

    @RequestMapping("/api/secure/categories/save")
    public Category saveCategory(@RequestBody Category category) {
        return service.save(category);
    }

    @RequestMapping("/api/secure/categories/delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @RequestMapping("/api/secure/categories/fetch")
    public Page<Category> fetchCategories(Pageable pageable) {
        return service.findPaginated(pageable);
    }

    @RequestMapping("/api/secure/categories/find-all")
    public List<Category> findAllCategories() {
        return service.findAll();
    }
}
