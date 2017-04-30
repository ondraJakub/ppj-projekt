package cz.tul.repositories;

import cz.tul.data.Komentar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ondrej Jakub on 4/30/2017.
 */
@Repository
public interface KomentarRepository extends CrudRepository<Komentar, Integer> {
}
