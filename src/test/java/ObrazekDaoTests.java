import cz.tul.Main;
import cz.tul.data.Obrazek;
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
public class ObrazekDaoTests {

    @Autowired
    private ObrazekService obrazekService;
    @Autowired
    private UserService userService;
    @Autowired
    private KomentarService komentarService;
//    @Autowired
//    private TagService tagService;


    private User user = new User("imageCreator",  new Date());


    @Before
    public void init() {
        komentarService.deleteKomentare();
//        tagService.deleteTags();
        obrazekService.deleteObrazky();
        userService.deleteUsers();
    }

    @Test
    public void testVytvorObrazek() {
        userService.create(user);
        user = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(user,"http://test", "titulek", new Date());
        obrazekService.create(obrazek);

        assertEquals(1, obrazekService.getObrazky().size());
    }

    @Test
    public void testLikeDislike() {
        userService.create(user);
        User autor = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "like",  new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        assertEquals((int)obrazek.getPocet_likes(), 0);
        obrazekService.pridejLike(obrazek);
        assertEquals((int)obrazek.getPocet_likes(), 1);
        obrazekService.pridejDisLike(obrazek);
        assertEquals((int)obrazek.getPocet_dislikes(), 1);
    }

    @Test
    public void testZmenObrazek() {

        userService.create(user);
        User autor = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "original",  new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());
        obrazek.setNazev("testZmena");
        obrazekService.update(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        assertEquals(obrazek.getNazev(), "testZmena");
    }



}