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

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ObrazekDaoTests {

    @Autowired
    private ObrazekDao obrazekDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private KomentarDao komentarDao;
    @Autowired
    private TagDao tagDao;


    private User user = new User("imageCreator",  new Date());


    @Before
    public void init() {
        komentarDao.deleteKomentare();
        tagDao.deleteTags();
        obrazekDao.deleteObrazky();
        userDao.deleteUsers();
    }

    @Test
    public void testVytvorObrazek() {
        userDao.create(user);
        user = userDao.getUser(user.getJmeno());

        Obrazek obrazek = new Obrazek(user,"http://test", "titulek", new Date());
        assertTrue(obrazekDao.create(obrazek));

        assertEquals(1, obrazekDao.getObrazky().size());
    }

    @Test
    public void testLikeDislike() {
        userDao.create(user);
        User autor = userDao.getUser(user.getJmeno());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "like",  new Date());
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        assertEquals((int)obrazek.getPocet_likes(), 0);
        obrazekDao.pridejLike(obrazek);
        assertEquals((int)obrazek.getPocet_likes(), 1);
        obrazekDao.pridejDislike(obrazek);
        assertEquals((int)obrazek.getPocet_dislikes(), 1);
    }

    @Test
    public void testZmenObrazek() {

        userDao.create(user);
        User autor = userDao.getUser(user.getJmeno());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "original",  new Date());
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());
        obrazek.setNazev("testZmena");
        obrazekDao.update(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        assertEquals(obrazek.getNazev(), "testZmena");
    }



}
