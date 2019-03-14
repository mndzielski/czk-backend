package pl.edu.wat.backend.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.wat.backend.context.BaseTestContext;
import pl.edu.wat.backend.service.MyUserDetailsService;
import pl.edu.wat.domain.entity.User;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTestContext.class})
@DataJpaTest
public class MyUserDetailsServiceImplTest {

    @Autowired
    private MyUserDetailsService userDetailsService;

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
    public void loadUserByUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email1);
        assertNotNull(userDetails);
        assertEquals(email1, userDetails.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_AndExpectUsernameNotFoundException() {
        userDetailsService.loadUserByUsername(email2);
    }
}