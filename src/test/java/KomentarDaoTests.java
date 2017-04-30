import cz.tul.Main;
import cz.tul.data.*;
import cz.tul.services.*;
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
    private ObrazekService obrazekService;
    @Autowired
    private UserService userService;
    @Autowired
    private KomentarService komentarService;


    private User user = new User("imageCreator", new Date());

    @Before
    public void init() {
        komentarService.deleteKomentare();
//        tagService.deleteTags();
        obrazekService.deleteObrazky();
        userService.deleteUsers();
    }

    @Test
    public void testVytvorKomentar() {
        userService.create(user);
        user = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(user, "http://testovaci_obrazek", "titulek", new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        Komentar komentar = new Komentar(user, obrazek, new Date(), "test");
        komentarService.create(komentar);

        List<Komentar> kometare = komentarService.getKomentare();
        assertEquals(1, kometare.size());
    }
}