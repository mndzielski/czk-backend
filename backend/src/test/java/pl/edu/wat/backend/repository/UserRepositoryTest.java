package pl.edu.wat.backend.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.wat.backend.context.BaseTestContext;
import pl.edu.wat.domain.entity.User;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTestContext.class})
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private final String email1 = "user1@czk.pl";
    private final String email2 = "user2@czk.pl";

    @Transactional
    @Before
    public void setUp() {
        User user1 = User.builder().name("USER 1").email(email1).password("password").enabled(true)
                .roles(new ArrayList<>()).build();
        entityManager.persist(user1);
    }

    @Test
    public void findByEmail() {
        User byEmail = userRepository.findByEmail(email1);
        assertNotNull(byEmail);
    }

    @Test
    public void findByEmail_AndExpectNull() {
        User byEmail = userRepository.findByEmail(email2);
        assertNull(byEmail);
    }
}