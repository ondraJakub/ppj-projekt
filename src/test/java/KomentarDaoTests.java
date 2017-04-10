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

        User imageCreator = new User("imageCreator", "2008-01-01 00:00:01");
        userDao.create(imageCreator);
        imageCreator = userDao.getUser(imageCreator.getJmeno());

        User commenter = new User("commenter", "2008-01-01 00:00:01");
        userDao.create(commenter);
        commenter = userDao.getUser(commenter.getJmeno());

        Obrazek obrazek = new Obrazek(imageCreator,"http://test", "titulek", "2008-01-01 00:00:01");
        obrazekDao.create(obrazek);
        obrazek = obrazekDao.getObrazek(obrazek.getUrl());


        obrazekDao.deleteObrazek(obrazek.getId_obrazek());
        userDao.deleteUser(commenter.getId_user());
        userDao.deleteUser(imageCreator.getId_user());
    }


}
