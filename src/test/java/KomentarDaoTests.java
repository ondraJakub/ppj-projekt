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

import static org.junit.Assert.*;

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
    @Autowired
    private TagService tagService;


    private User user1 = new User("franta", new Date());
    private User user2 = new User("pepa", new Date());

    @Before
    public void init() {
        komentarService.deleteKomentare();
        tagService.deleteTagy();
        obrazekService.deleteObrazky();
        userService.deleteUsers();
    }

    @Test
    public void testVytvorKomentar() {
        userService.create(user1);
        user1 = userService.getUser(user1.getId());

        Obrazek obrazek = new Obrazek(user1, "http://testovaci_obrazek", "titulek", new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        Komentar komentar = new Komentar(user1.getId(), obrazek.getId(), new Date(), "test");
        komentarService.create(komentar);

        List<Komentar> kometare = komentarService.getKomentare();
        assertEquals(1, kometare.size());
    }

    @Test
    public void testUpdate() {
        userService.create(user1);
        user1 = userService.getUser(user1.getId());

        Obrazek obrazek = new Obrazek(user1, "http://testovaci_obrazek", "titulek", new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        Komentar komentar = new Komentar(user1.getId(), obrazek.getId(), new Date(), "test");
        komentarService.create(komentar);

        komentar.setId_obrazek(obrazek.getId());

        komentarService.create(komentar);
        List<Komentar> komentare = komentarService.getKomentare();
        assertNotNull(komentare);
        assertEquals(1, komentare.size());

        komentar.setText("newText");
        komentarService.update(komentar);
        List<Komentar> komentare2 = komentarService.getKomentare();
        assertNotNull(komentare2);
        assertEquals(1, komentare2.size());

        assertNotEquals(komentare, komentare2);
    }

    @Test
    public void testLikeDislike() {
        userService.create(user1);
        user1 = userService.getUser(user1.getId());

        Obrazek obrazek = new Obrazek(user1, "http://testovaci_obrazek", "titulek", new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        Komentar komentar = new Komentar(user1.getId(), obrazek.getId(), new Date(), "test");
        komentarService.create(komentar);
        komentar = komentarService.getKomentar(komentar.getId());

        assertEquals((int) komentar.getPocet_likes(), 0);
        komentarService.pridejLike(komentar);
        assertEquals((int) komentar.getPocet_likes(), 1);
        komentarService.pridejDisLike(komentar);
        assertEquals((int) komentar.getPocet_dislikes(), 1);
    }

    @Test
    public void testCommentsForImage(){
        userService.create(user1);
        user1 = userService.getUser(user1.getId());

        Obrazek obrazek = new Obrazek(user1, "http://testovaci_obrazek", "titulek", new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        Komentar komentar = new Komentar(user1.getId(), obrazek.getId(), new Date(), "test");
        komentarService.create(komentar);
        List<Komentar> komentare = komentarService.najdiKomentareObrazku(obrazek.getId());
        assertNotNull(komentare);
        assertEquals(1, komentare.size());

        komentarService.deleteKomentare();
        List<Komentar> komentare2 = komentarService.najdiKomentareObrazku(obrazek.getId());
        assertEquals(0, komentare2.size());
    }


}