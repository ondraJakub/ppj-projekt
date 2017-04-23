import cz.tul.Main;
import cz.tul.data.Obrazek;
import cz.tul.data.ObrazekDao;
import cz.tul.data.User;
import cz.tul.data.UserDao;
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

        obrazekDao.deleteObrazky();
        userDao.deleteUsers();

        User user = new User("imageCreator", "2016-10-20 00:00:01");
        userDao.create(user);
        user = userDao.getUser(user.getJmeno());

        Obrazek obrazek = new Obrazek(user,"http://test", "titulek", "2016-10-20 00:00:01");
        obrazekDao.create(obrazek);

        Obrazek created = obrazekDao.getObrazek(obrazek.getUrl());

        assertEquals(created.getUrl(), obrazek.getUrl());

        obrazekDao.deleteObrazek(created.getId_obrazek());
        userDao.deleteUser(user.getId_user());
    }

    @Test
    public void testLikeDislike() {
        obrazekDao.deleteObrazky();
        userDao.deleteUsers();

        User autor = new User("imageCreator", "2016-10-20 00:00:01");
        userDao.create(autor);
        autor = userDao.getUser(autor.getJmeno());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "like", "2016-10-20 00:00:01");
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        assertEquals((int)obrazek.getObrazek_pocet_likes(), 0);
        obrazekDao.changeLikes(obrazek, true);
        assertEquals((int)obrazek.getObrazek_pocet_likes(), 1);
        obrazekDao.changeLikes(obrazek, false);
        assertEquals((int)obrazek.getObrazek_pocet_likes(), 0);

        obrazekDao.deleteObrazek(obrazek.getId_obrazek());
        userDao.deleteUser(autor.getId_user());
    }

    @Test
    public void testZmenObrazek() {
        obrazekDao.deleteObrazky();
        userDao.deleteUsers();

        User autor = new User("imageCreator", "2016-10-20 00:00:01");
        userDao.create(autor);
        autor = userDao.getUser(autor.getJmeno());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "original", "2016-10-20 00:00:01");
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        Obrazek original = new Obrazek(obrazek.getId_obrazek(), obrazek.getNazev(), obrazek.getUrl(), obrazek.getUser(),
                obrazek.getObrazek_datum_vytvoreni(), obrazek.getObrazek_datum_vytvoreni(), obrazek.getObrazek_pocet_likes());
        obrazek.setNazev("zmena");
        obrazekDao.update(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getId_obrazek());

        assertNotEquals(obrazek.getNazev(), original.getNazev());

        obrazekDao.deleteObrazek(obrazek.getId_obrazek());
        userDao.deleteUser(autor.getId_user());
    }



}
