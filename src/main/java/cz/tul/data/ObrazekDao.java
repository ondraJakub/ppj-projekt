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

    public void pridejLike(Obrazek obrazek) {
        obrazek.setPocet_likes(obrazek.getPocet_likes() + 1);
        update(obrazek);
    }

    public void pridejDisLike(Obrazek obrazek) {
        obrazek.setPocet_dislikes(obrazek.getPocet_dislikes() + 1);
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

    public boolean deleteObrazek(int id) {
        Query query = session().createQuery("delete from Obrazek where id=:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public void deleteObrazky() {
        if(getObrazky()!=null){
            session().createQuery("delete from Obrazek").executeUpdate();
        }
    }
}
