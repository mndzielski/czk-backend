package pl.edu.wat.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.repository.CategoryRepository;
import pl.edu.wat.backend.service.CategoryService;
import pl.edu.wat.domain.entity.Category;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Category> findPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
