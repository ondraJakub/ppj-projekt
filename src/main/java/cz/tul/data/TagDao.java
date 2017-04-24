package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@Transactional
public class TagDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(Tag tag) {
        session().save(tag);
    }

    public List<Tag> getTagy() {
        Criteria criteria = session().createCriteria(Tag.class);
        return criteria.list();
    }

    public void deleteTagy() {
        session().createQuery("delete from Tag").executeUpdate();
    }

}
