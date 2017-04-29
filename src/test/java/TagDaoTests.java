//import cz.tul.Main;
//import cz.tul.data.*;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
///**
// * Created by Ondrej Jakub on 4/3/2017.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {Main.class})
//@ActiveProfiles({"test"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class TagDaoTests {
//
//    @Autowired
//    private ObrazekDao obrazekDao;
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private KomentarDao komentarDao;
//    @Autowired
//    private TagDao tagDao;
//
//    private User user = new User("imageCreator", new Date());
//
//    @Before
//    public void init() {
//        komentarDao.deleteKomentare();
//        tagDao.deleteTags();
//        obrazekDao.deleteObrazky();
//        userDao.deleteUsers();
//    }
//
//    @Test
//    public void testVytvorTag() {
//        userDao.create(user);
//        user = userDao.getUser(user.getJmeno());
//
//        Obrazek obrazek = new Obrazek(user, "http://test", "titulek", new Date());
//        obrazekDao.create(obrazek);
//        obrazek = obrazekDao.getObrazek(obrazek.getUrl());
//
//        Tag tag = new Tag("titulek", obrazek);
//        tagDao.create(tag);
//        assertEquals(1, tagDao.getTagy().size());
//    }
//
//}