package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@Transactional
public class ObrazekDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Obrazek> getObrazky() {
        Criteria criteria = session().createCriteria(Obrazek.class);
        return criteria.list();
    }

    public void update(Obrazek obrazek) {
        session().saveOrUpdate(obrazek);
    }

    public void changeLikes(Obrazek obrazek, boolean like) {
        if (like) {
            obrazek.setObrazek_pocet_likes(obrazek.getObrazek_pocet_likes() + 1);
        } else {
            obrazek.setObrazek_pocet_likes(obrazek.getObrazek_pocet_likes() - 1);
        }
        update(obrazek);
    }

    public void create(Obrazek obrazek) {
        session().save(obrazek);
    }


    public Obrazek getObrazek(int id_obrazek) {
        Criteria criteria = session().createCriteria(Obrazek.class);
        criteria.add(Restrictions.idEq(id_obrazek));

        return (Obrazek) criteria.uniqueResult();
    }

    public Obrazek getObrazek(String url) {
        Criteria criteria = session().createCriteria(Obrazek.class);
        criteria.add(Restrictions.eq("url", url));

        return (Obrazek) criteria.uniqueResult();
    }

    public boolean deleteObrazek(int id_obrazek) {
        Query query = session().createQuery("delete from Obrazek where id_obrazek=:id_obrazek");
        query.setLong("id_obrazek", id_obrazek);
        return query.executeUpdate() == 1;
    }

    public void deleteObrazky() {
        session().createQuery("delete from Obrazek").executeUpdate();
    }
}
