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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Before
    public void init() {
        komentarDao.deleteKomentare();
        obrazekDao.deleteObrazky();
        userDao.deleteUsers();
    }

    @Test
    public void testVytvorObrazek() {

        obrazekDao.deleteObrazky();
        userDao.deleteUsers();

        User user = new User("Ondrej Jakub", LocalDateTime.now());
        userDao.create(user);
        user = userDao.getUser(user.getJmeno());

        Obrazek obrazek = new Obrazek(user,"http://test", "titulek", LocalDateTime.now());
        obrazekDao.create(obrazek);

        Obrazek created = obrazekDao.getObrazek(obrazek.getUrl());

        assertEquals(created.getUrl(), obrazek.getUrl());
    }

    @Test
    public void testLikeDislike() {

        User autor = new User("Ondrej Jakub", LocalDateTime.now());
        userDao.create(autor);
        autor = userDao.getUser(autor.getJmeno());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "like",LocalDateTime.now());
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        assertEquals((int)obrazek.getPocet_likes(), 0);
        obrazekDao.pridejLike(obrazek);
        assertEquals((int)obrazek.getPocet_likes(), 1);
        obrazekDao.pridejDisLike(obrazek);
        assertEquals((int)obrazek.getPocet_dislikes(), 1);
    }

    @Test
    public void testZmenObrazek() {
        User autor = new User("Ondrej Jakub", LocalDateTime.now());
        userDao.create(autor);
        autor = userDao.getUser(autor.getJmeno());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "original", LocalDateTime.now());
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        Obrazek original = new Obrazek(obrazek.getId(), obrazek.getNazev(), obrazek.getUrl(), obrazek.getUser(),
                obrazek.getDatum_vytvoreni(), obrazek.getDatum_vytvoreni(), obrazek.getPocet_likes(), obrazek.getPocet_dislikes());
        obrazek.setNazev("zmena");
        obrazekDao.update(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getId());

        assertNotEquals(obrazek.getNazev(), original.getNazev());
    }



}
