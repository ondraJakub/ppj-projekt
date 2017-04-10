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

    @Test
    public void testVytvorObrazek() {

        User user = new User("imageCreator", "2008-01-01 00:00:01");
        userDao.create(user);
        user = userDao.getUser(user.getJmeno());

        Obrazek image = new Obrazek(user,"http://test", "titulek", "2008-01-01 00:00:01");
        assertTrue("Image creation should return true", obrazekDao.create(image));

        Obrazek created = obrazekDao.getObrazek(image.getUrl());

        assertEquals("Return image from the database", created.getUrl(), image.getUrl());

        obrazekDao.deleteObrazek(created.getId_obrazek());
        userDao.deleteUser(user.getId_user());
    }

    @Test
    public void testLikeDislike() {

        User creator = new User("imageCreator", "2008-01-01 00:00:01");
        userDao.create(creator);
        creator = userDao.getUser(creator.getJmeno());

        Obrazek obrazek = new Obrazek(creator,"http://liketest", "like", "2008-01-01 00:00:01");
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        assertEquals("Likes at 0", (int)obrazek.getObrazek_pocet_likes(), 0);
        obrazekDao.changeLikes(obrazek, true);
        assertEquals("Likes at 1", (int)obrazek.getObrazek_pocet_likes(), 1);
        obrazekDao.changeLikes(obrazek, false);
        assertEquals("Likes at 0 again", (int)obrazek.getObrazek_pocet_likes(), 0);

        obrazekDao.deleteObrazek(obrazek.getId_obrazek());
        userDao.deleteUser(creator.getId_user());
    }

    @Test
    public void testZmenObrazek() {

        User creator = new User("imageCreator", "2008-01-01 00:00:01");
        userDao.create(creator);
        creator = userDao.getUser(creator.getJmeno());

        Obrazek obrazek = new Obrazek(creator,"http://liketest", "original", "2008-01-01 00:00:01");
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        Obrazek original = new Obrazek(obrazek.getId_obrazek(), obrazek.getNazev(), obrazek.getUrl(), obrazek.getUser(),
                obrazek.getObrazek_datum_vytvoreni(), obrazek.getObrazek_datum_vytvoreni(), obrazek.getObrazek_pocet_likes());
        obrazek.setNazev("zmena");
        obrazekDao.update(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getId_obrazek());

        assertNotEquals("Check if changed", obrazek.getNazev(), original.getNazev());

        obrazekDao.deleteObrazek(obrazek.getId_obrazek());
        userDao.deleteUser(creator.getId_user());
    }



}
