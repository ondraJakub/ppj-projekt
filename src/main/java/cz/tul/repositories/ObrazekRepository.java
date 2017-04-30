package cz.tul.repositories;

import cz.tul.data.Obrazek;
import cz.tul.data.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ondrej Jakub on 4/29/2017.
 */

@Repository
public interface ObrazekRepository extends CrudRepository<Obrazek, Integer> {
    @Query("select i from Obrazek as i where i.user=:user")
    public List<Obrazek> najdiPodleAutora(@Param("user") User user);

    @Query("select i from Obrazek as i where i.nazev=:nazev")
    public List<Obrazek> najdiPodleNazvu(@Param("nazev") String nazev);

    @Query("select i from Obrazek as i where i.id in (select distinct(t.obrazekId) from Tag as t where t.titulek = :tagy)")
    public List<Obrazek> najdiPodleTagu(@Param("tagy") List<String> tagy);
}
