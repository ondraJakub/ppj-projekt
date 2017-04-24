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

    @Before
    public void init() {
        komentarDao.deleteKomentare();
        userDao.deleteUsers();
        obrazekDao.deleteObrazky();
    }

    @Test
    public void testVytvorKomentar() {
        User autor = new User("Ondrej Jakub", LocalDateTime.now());
        userDao.create(autor);
        autor = userDao.getUser(autor.getJmeno());

        Obrazek obrazek = new Obrazek(autor,"http://testovaci_obrazek", "titulek", LocalDateTime.now());
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());

        Komentar komentar = new Komentar(autor, obrazek, LocalDateTime.now(), "test");
        komentarDao.create(komentar);

        Komentar novy = komentarDao.getKomentar(komentar.getText());
        assertEquals(novy.getText(), komentar.getText());

    }


}
