package pl.edu.wat.backend.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.wat.backend.context.BaseTestContext;
import pl.edu.wat.backend.service.TypeService;
import pl.edu.wat.domain.entity.Type;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTestContext.class})
@DataJpaTest
public class TypeServiceImplTest {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TestEntityManager entityManager;

    @Transactional
    @Before
    public void setUp() {
        Type type1 = Type.builder().name("name1").icon("icon1").build();
        entityManager.persist(type1);

        Type type2 = Type.builder().name("name2").icon("icon2").build();
        entityManager.persist(type2);
    }

    @Test
    public void findAll() {
        List<Type> all = typeService.findAll();
        assertEquals(2, all.size());
    }
}