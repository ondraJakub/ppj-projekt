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

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TagDaoTests {

    @Autowired
    private ObrazekService obrazekService;
    @Autowired
    private UserService userService;
    @Autowired
    private KomentarService komentarService;
    @Autowired
    private TagService tagService;

    private User user = new User("imageCreator", new Date());

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        komentarService.deleteKomentare();
        tagService.deleteTagy();
        obrazekService.deleteObrazky();
        userService.deleteUsers();
    }

    @Test
    public void testVytvorTag() {
        userService.create(user);
        user = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(user, "http://test", "titulek", new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        Tag tag = new Tag("titulek", obrazek.getId());
        tagService.create(tag);
        assertEquals(1, tagService.getTagy().size());
    }

    @Test
    public void testDeleteAGetTagyProObrazek() {
        userService.create(user);
        user = userService.getUser(user.getId());

        Obrazek obrazek = new Obrazek(user, "http://test", "titulek", new Date());
        obrazekService.create(obrazek);
        obrazek = obrazekService.getObrazek(obrazek.getId());

        Tag tag = new Tag("titulek", obrazek.getId());
        tagService.create(tag);
        assertEquals(1, tagService.getTagyProObrazek(obrazek.getId()).size());

        PersistenceUnitUtil util = entityManagerFactory.getPersistenceUnitUtil();
        TagId tagId = (TagId) util.getIdentifier(tag);
        tagService.deleteTag(tagId);
        System.out.println(obrazek.getId());
        List<Tag> tagy = tagService.getTagyProObrazek(obrazek.getId());
        System.out.println(tagy);
        assertEquals(0, tagy.size());
    }

}