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

    public boolean exists(String jmeno) {
        Criteria criteria = session().createCriteria(User.class);
        criteria.add(Restrictions.eq("jmeno", jmeno));
        User user = (User) criteria.uniqueResult();
        return user != null;
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

    public boolean deleteUser(int id_user) {
        Query query = session().createQuery("delete from User where id_user=:id_user");
        query.setLong("id_user", id_user);
        return query.executeUpdate() == 1;
    }

    public void deleteUsers() {
        session().createQuery("delete from User").executeUpdate();
    }
}
