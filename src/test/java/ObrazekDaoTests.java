import cz.tul.Main;
import cz.tul.data.*;
import cz.tul.services.*;
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

import java.util.ArrayList;
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
public class ObrazekDaoTests {

    @Autowired
    private ObrazekService obrazekService;
    @Autowired
    private UserService userService;
    @Autowired
    private KomentarService komentarService;
    @Autowired
    private TagService tagService;


    private User user = new User("imageCreator",  new Date());


    @Before
    public void init() {
        komentarService.deleteKomentare();
        tagService.deleteTagy();
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

        assertEquals((int) obrazek.getPocet_likes(), 0);
        obrazekService.pridejLike(obrazek);
        assertEquals((int) obrazek.getPocet_likes(), 1);
        obrazekService.pridejDisLike(obrazek);
        assertEquals((int) obrazek.getPocet_dislikes(), 1);
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

    @Test
    public void testGetObrazekPodleAutora() {
        userService.create(user);
        User autor = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "original",  new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        Obrazek obrazek2 = new Obrazek(autor,"http://liketest", "obrazek2",  new Date());
        obrazekService.create(obrazek2);
        obrazek2 = obrazekService.getObrazek(obrazek2.getId());

        List<Obrazek> obrazky = obrazekService.najdiPodleAutora(autor);
        assertNotNull(obrazky);
        assertEquals(2, obrazky.size());
    }

    @Test
    public void testGetById() {
        userService.create(user);
        User autor = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "original",  new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        Obrazek obrazek2 = obrazekService.getObrazek(obrazek.getId());
        assertNotNull(obrazek2);
        assertEquals(obrazek.getNazev(), obrazek2.getNazev());
    }

    @Test
    public void testGetByName() {
        userService.create(user);
        User autor = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "original",  new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        List<Obrazek> obrazky = obrazekService.najdiPodleNazvu("original");
        assertNotNull( obrazky);
        assertEquals(1, obrazky.size());

        List<Obrazek> obrazky1 = obrazekService.najdiPodleNazvu("test");
        assertEquals(0, obrazky1.size());
    }

    @Test
    public void testGetByTag(){
        userService.create(user);
        User autor = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(autor,"http://liketest", "original",  new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());
        Tag tag = new Tag("testTag", obrazek.getId());
        tagService.create(tag);

        List<String> tags1 = new ArrayList<>();
        tags1.add(tag.getTitulek());
        List<Obrazek> obrazky = obrazekService.najdiPodleTagu(tags1);
        assertNotNull(obrazky);
        assertEquals(1, obrazky.size());

        List<String> tags2 = new ArrayList<>();
        tags2.add("randomTag");
        List<Obrazek> images2 = obrazekService.najdiPodleTagu(tags2);
        assertEquals(0, images2.size());
    }



}