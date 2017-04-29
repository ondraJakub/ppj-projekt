import cz.tul.Main;
import cz.tul.data.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Date;

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
    private ObrazekDao obrazekDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private KomentarDao komentarDao;
    @Autowired
    private TagDao tagDao;


    @Before
    public void init() {
        komentarDao.deleteKomentare();
        tagDao.deleteTags();
        obrazekDao.deleteObrazky();
        userDao.deleteUsers();
    }

    @Test
    public void testVytvorUser() {
        User user = new User("testUser", new Date());
        assertTrue(userDao.create(user));
        assertEquals(1, userDao.getAllUsers().size());
    }

    @Test
    public void testZmenJmenoUser(){
        User user = new User("testUser", new Date());
        assertTrue(userDao.create(user));
        user = userDao.getUser("testUser");
        user.setJmeno("pepa");
        userDao.update(user);
        assertEquals("pepa", userDao.getUser(user.getJmeno()).getJmeno());
    }

}
