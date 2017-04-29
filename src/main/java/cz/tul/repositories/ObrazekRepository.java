package cz.tul.repositories;

import cz.tul.data.Obrazek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ondrej Jakub on 4/29/2017.
 */

@Repository
public interface ObrazekRepository extends CrudRepository<Obrazek, Integer> {
}
