package edu.calpoly.csc_308.cora.finian;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import edu.calpoly.csc_308.cora.data.users.UserDAO;
import edu.calpoly.csc_308.cora.data.users.UserRepository;
import edu.calpoly.csc_308.cora.entities.User;
import edu.calpoly.csc_308.cora.services.UserManager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AutoConfigureTestDatabase
@SpringBootTest
class UserManagerTest {
    @Autowired
    private UserManager userManager;

    @Autowired
    private UserRepository repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }

    @Test
    void testFindUsersByName(){
        assertThat(repo.findAll(), is(empty()));
        //User testUser = new User();
        //testUser.id
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDAO dao = repo.save(
                new UserDAO(
                        new UserDAO.ProfileInfo(
                                "Testy",
                                "Test, CA",
                                "tester",
                                "tester located in Test, CA",
                                new String[] {}),
                        -1L,
                        "test",
                        encoder.encode("test123"))
        );
        User testUser = new User(dao.getId(), dao.getName(), dao.getLocation(), dao.getUserType(), dao.getDescription(), dao.getSkillSet(), dao.getPhotoId());
        
        List<User> userList = userManager.findUsersByName("Testy");
        assertThat(userList, contains(new User[]{testUser}));

        assertThat(repo.findAll(), contains(new UserDAO[]{dao}));

    }

    @Test
    void testGetUser(){
        assertThat(repo.findAll(), is(empty()));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDAO dao = repo.save(
                new UserDAO(
                        new UserDAO.ProfileInfo(
                                "Testy",
                                "Test, CA",
                                "tester",
                                "tester located in Test, CA",
                                new String[] {}),
                        -1L,
                        "test",
                        encoder.encode("test123"))
        );
        User testUser = new User(dao.getId(), dao.getName(), dao.getLocation(), dao.getUserType(), dao.getDescription(), dao.getSkillSet(), dao.getPhotoId());

        User user1 = userManager.getUser(dao.getId());

        assertThat(user1, equalTo(testUser));
        assertThat(repo.findAll(), contains(new UserDAO[]{dao}));

    }

}
