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
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KomentarDaoTests {

    @Autowired
    private ObrazekDao obrazekDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private KomentarDao komentarDao;
    @Autowired
    private TagDao tagDao;

    private User user = new User("imageCreator", new Date());

    @Before
    public void init() {
        komentarDao.deleteKomentare();
        tagDao.deleteTags();
        obrazekDao.deleteObrazky();
        userDao.deleteUsers();
    }

    @Test
    public void testVytvorKomentar() {
        userDao.create(user);
        user = userDao.getUser(user.getJmeno());

        Obrazek obrazek = new Obrazek(user, "http://testovaci_obrazek", "titulek", new Date());
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        Komentar komentar = new Komentar(user, obrazek, new Date(), "test");
        komentarDao.create(komentar);

        List<Komentar> kometare = komentarDao.getKomentare();
        assertEquals(1, kometare.size());
    }
}