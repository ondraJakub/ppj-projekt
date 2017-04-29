package cz.tul.repositories;

import cz.tul.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ondrej Jakub on 4/29/2017.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
