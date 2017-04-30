package cz.tul.repositories;

import cz.tul.data.Komentar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ondrej Jakub on 4/30/2017.
 */
@Repository
public interface KomentarRepository extends CrudRepository<Komentar, Integer> {
    @Query("select c from Komentar as c where c.id_obrazek = :id_obrazek")
    public List<Komentar> najdiKomentareObrazku(@Param("id_obrazek") Integer id_obrazek);
}
