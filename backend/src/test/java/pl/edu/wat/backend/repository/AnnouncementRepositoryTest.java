package pl.edu.wat.backend.repository;

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
import pl.edu.wat.domain.entity.Announcement;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTestContext.class})
@DataJpaTest
public class AnnouncementRepositoryTest {

    @Autowired
    private AnnouncementRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private String[] provinces = {"10", "20", "18", "20", "20", "12"};


    @Transactional
    @Before
    public void setUp() {
        for (int i = 0; i < provinces.length; i++) {
            Announcement announcement = Announcement.builder().title("Title " + i).description("Description " + i).province(provinces[i]).build();
            entityManager.persist(announcement);
        }
    }

    @Test
    public void findPaginatedByProvinces() {
        Page<Announcement> paginatedByProvinces = repository.findPaginatedByProvinces(new ArrayList<>(Arrays.asList("20", "18")), PageRequest.of(0, 10));
        assertEquals(4, paginatedByProvinces.getContent().size());
    }
}