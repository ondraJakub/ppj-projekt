import cz.tul.Main;
import cz.tul.data.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void testVytvorUser() {

        User user = new User("testUser", "2008-01-01 00:00:01");

        assertTrue("User creation should return true", userDao.create(user));

        assertTrue("User should exist", userDao.exists(user.getJmeno()));

        User created = userDao.getUser(user.getJmeno());

        assertEquals("Return user from the database", created.getJmeno(), user.getJmeno());

        userDao.deleteUser(created.getId());
    }

}
