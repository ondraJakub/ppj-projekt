import cz.tul.Main;
import cz.tul.data.User;
import cz.tul.services.KomentarService;
import cz.tul.services.ObrazekService;
import cz.tul.services.UserService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTests {

    @Autowired
    private ObrazekService obrazekService;
    @Autowired
    private UserService userService;
    @Autowired
    private KomentarService komentarService;
//    @Autowired
//    private TagService tagService;


    @Before
    public void init() {
        komentarService.deleteKomentare();
//        tagService.deleteTags();
        obrazekService.deleteObrazky();
        userService.deleteUsers();
    }

    @Test
    public void testVytvorUser() {
        User user = new User("testUser", new Date());
        userService.create(user);
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    public void testZmenJmenoUser(){
        User user = new User("testUser", new Date());
        userService.create(user);
        user = userService.getUser(user.getId());
        user.setJmeno("pepa");
        userService.update(user);
        assertEquals("pepa", userService.getUser(user.getId()).getJmeno());
    }

}