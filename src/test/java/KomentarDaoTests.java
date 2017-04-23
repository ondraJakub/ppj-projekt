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

    @Test
    public void testVytvorKomentar() {
        obrazekDao.deleteObrazky();
        userDao.deleteUsers();
        komentarDao.deleteKomentare();


        User autor = new User("imageCreator", "2016-10-20 00:00:01");
        userDao.create(autor);
        autor = userDao.getUser(autor.getJmeno());

        User komentator = new User("commenter", "2016-10-20 00:00:01");
        userDao.create(komentator);
        komentator = userDao.getUser(komentator.getJmeno());

        Obrazek obrazek = new Obrazek(autor,"http://test", "titulek", "2016-10-20 00:00:01");
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        Komentar komentar = new Komentar(komentator, obrazek, "2016-10-20 00:00:01", "2016-10-20 00:00:01", "test");
        komentarDao.create(komentar);

        Komentar novy = komentarDao.getKomentar(komentar.getText());
        assertEquals(novy.getText(), komentar.getText());

        komentarDao.deleteKomentar(novy.getId_komentar());
        obrazekDao.deleteObrazek(obrazek.getId_obrazek());
        userDao.deleteUser(komentator.getId_user());
        userDao.deleteUser(autor.getId_user());
    }


}
