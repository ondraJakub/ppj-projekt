package cz.tul;
import cz.tul.data.*;
import cz.tul.services.*;
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
    public UserService autorService() {
        return new UserService();
    }

    @Bean
    public KomentarService komentarService() {
        return new KomentarService();
    }

    @Bean
    public ObrazekService obrazekService() {
        return new ObrazekService();
    }

//    @Bean
//    public TagService tagService() {
//        return new TagService();
//    }

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    public static void main(String[] args) {


        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        UserService userService = ctx.getBean(UserService.class);
        List<User> users = userService.getAllUsers();
        System.out.println(users);

        ObrazekService obrazekService = ctx.getBean(ObrazekService.class);
        List<Obrazek> images = obrazekService.getObrazky();
        System.out.println(images);

//        KomentarService komentarService = ctx.getBean(KomentarService.class);
//        List<Komentar> comments = komentarService.getKomentare();
//        System.out.println(comments);
//
//        TagService tagService = ctx.getBean(TagService.class);
//        List<Tag> tags = tagService.getTagy();
//        System.out.println(tags);

    }

}