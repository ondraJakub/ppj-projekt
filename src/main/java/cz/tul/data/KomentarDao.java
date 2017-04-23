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
public class KomentarDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<Komentar> getKomentare() {
        Criteria criteriarit = session().createCriteria(Komentar.class);
        return criteriarit.list();
    }

    public void create(Komentar komentar) {
        session().save(komentar);
    }


    public boolean deleteKomentar(int id_komentar) {
        Query query = session().createQuery("delete from Komentar where id_komentar=:id_komentar");
        query.setLong("id_komentar", id_komentar);
        return query.executeUpdate() == 1;
    }

    public Komentar getKomentar(String text) {

        Criteria criteria = session().createCriteria(Komentar.class);
        criteria.add(Restrictions.eq("text", text));

        return (Komentar) criteria.uniqueResult();
    }

    public void deleteKomentare() {
        session().createQuery("delete from Komentar").executeUpdate();
    }
}
