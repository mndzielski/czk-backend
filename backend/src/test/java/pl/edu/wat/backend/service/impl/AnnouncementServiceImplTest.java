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
import pl.edu.wat.backend.service.AnnouncementService;
import pl.edu.wat.domain.entity.Announcement;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTestContext.class})
@DataJpaTest
public class AnnouncementServiceImplTest {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private TestEntityManager entityManager;


    @Transactional
    @Before
    public void setUp() {
        final String[] provinces = {"10", "20", "18", "20", "20", "12"};

        for (int i = 0; i < provinces.length; i++) {
            Announcement announcement = Announcement.builder().title("Title " + i).description("Description " + i).province(provinces[i]).build();
            entityManager.persist(announcement);
        }
    }

    @Test
    public void findPaginatedByProvinces() {
        Page<Announcement> paginatedByProvinces = announcementService.findPaginatedByProvinces(new ArrayList<>(Arrays.asList("20", "18")), PageRequest.of(0, 10));
        assertEquals(4, paginatedByProvinces.getContent().size());
    }

    @Transactional
    @Test
    public void save() {
        Announcement announcement = Announcement.builder().title("Title new").description("Description new").province("20").build();
        Announcement save = announcementService.save(announcement);
        assertNotNull(save.getId());
    }

    @Test
    public void findAll() {
        List<Announcement> all = announcementService.findAll();
        assertNotEquals(0, all.size());
    }

    @Transactional
    @Test
    public void deleteById() {
        List<Announcement> all = announcementService.findAll();
        assertNotEquals(0, all.size());
        for (Announcement announcement : all) {
            announcementService.deleteById(announcement.getId());
        }

        List<Announcement> allAfterDelete = announcementService.findAll();
        assertEquals(0, allAfterDelete.size());
    }

}