package pl.edu.wat.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.wat.domain.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Page<Category> findPaginated(Pageable pageable);

    Category save(Category category);

    void deleteById(Long id);
}
