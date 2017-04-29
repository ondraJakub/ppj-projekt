package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(User user) {
        session().save(user);
    }

    public void update(User user) {
        session().saveOrUpdate(user);
    }

    public User getUser(String jmeno) {
        Criteria criteria = session().createCriteria(User.class);
        criteria.add(Restrictions.eq("jmeno", jmeno));

        return (User) criteria.uniqueResult();
    }


    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Criteria criteria = session().createCriteria(User.class);
        return criteria.list();
    }

    public boolean deleteUser(int id) {
        Query query = session().createQuery("delete from User where id=:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public void deleteUsers() {
        if (getAllUsers() != null) {
            session().createQuery("delete from User").executeUpdate();
        }
    }
}
