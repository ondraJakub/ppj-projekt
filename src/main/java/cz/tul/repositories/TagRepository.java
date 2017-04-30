package cz.tul.repositories;

import cz.tul.data.Tag;
import cz.tul.data.TagId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ondrej Jakub on 4/30/2017.
 */

@Repository
public interface TagRepository extends CrudRepository<Tag, TagId> {
}
