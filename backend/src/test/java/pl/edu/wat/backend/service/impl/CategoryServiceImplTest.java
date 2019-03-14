package pl.edu.wat.backend.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.wat.backend.context.BaseTestContext;
import pl.edu.wat.backend.service.CategoryService;
import pl.edu.wat.domain.entity.Category;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTestContext.class})
@DataJpaTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TestEntityManager entityManager;

    private final int SIZE = 10;


    @Transactional
    @Before
    public void setUp() {
        for (int i = 0; i < SIZE; i++) {
            Category category = Category.builder().name("category " + i).icon("icon " + i).description("description " + i).build();
            entityManager.persist(category);
        }
    }

    @Test
    public void findAll() {
        List<Category> all = categoryService.findAll();
        assertEquals(SIZE, all.size());
    }

    @Test
    public void findPaginated() {
        final int size = 5;
        Page<Category> paginated = categoryService.findPaginated(PageRequest.of(0, size));
        assertEquals(SIZE, paginated.getTotalElements());
        assertEquals(size, paginated.getContent().size());
    }

    @Transactional
    @Test
    public void save() {
        Category category = Category.builder().name("category").icon("icon").description("description").build();
        Category save = categoryService.save(category);
        assertNotNull(save.getId());
    }

    @Transactional
    @Test
    public void deleteById() {
        List<Category> all = categoryService.findAll();
        assertNotEquals(0, all.size());
        for (Category category : all) {
            categoryService.deleteById(category.getId());
        }

        List<Category> allAfterDelete = categoryService.findAll();
        assertEquals(0, allAfterDelete.size());
    }
}