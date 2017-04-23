package cz.tul;
import cz.tul.data.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
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

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

    public static void main(String[] args) {


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
        List<Tag> tags = tagDao.getTagy();
        System.out.println(tags);

    }

}