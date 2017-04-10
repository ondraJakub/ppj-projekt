package cz.tul;
import cz.tul.data.*;
import cz.tul.provisioning.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


import java.util.List;

@SpringBootApplication
public class Main {

    @Bean
    public UserDao autorDao() {
        return new UserDao();
    }

    @Bean
    public KomentarDao komentarDao() {
        return new KomentarDao();
    }

    @Bean
    public ObrazekDao obrazekDao() {
        return new ObrazekDao();
    }

    @Bean
    public TagDao tagDao() {
        return new TagDao();
    }

//    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) throws Exception {


        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        UserDao userDao = ctx.getBean(UserDao.class);
        List<User> users = userDao.getAllUsers();
        System.out.println(users);

        ObrazekDao obrazekDao = ctx.getBean(ObrazekDao.class);
        List<Obrazek> images = obrazekDao.getObrazky();
        System.out.println(images);

        KomentarDao komentarDao = ctx.getBean(KomentarDao.class);
        List<Komentar> comments = komentarDao.getKomentare();
        System.out.println(comments);

        TagDao tagDao = ctx.getBean(TagDao.class);
        List<Tag> tags = tagDao.getTags();
        System.out.println(tags);

    }

}