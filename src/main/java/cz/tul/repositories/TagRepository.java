package cz.tul.repositories;

import cz.tul.data.Tag;
import cz.tul.data.TagId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ondrej Jakub on 4/30/2017.
 */

@Repository
public interface TagRepository extends CrudRepository<Tag, TagId> {
    @Query("select t from Tag as t where t.obrazekId = :obrazekId")
    public List<Tag> getTagyProObrazek(@Param("obrazekId") Integer imageId);
}
