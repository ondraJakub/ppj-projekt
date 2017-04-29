package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.*;

import java.util.List;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
public class TagDao {
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<Tag> getTags() {
        return jdbc.query("SELECT * FROM tag", BeanPropertyRowMapper.newInstance(Tag.class));

    }

    public boolean create(Tag tag) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("titulek", tag.getTitulek());
        params.addValue("id_obrazek", tag.getObrazek().getId_obrazek());
        System.out.println(params.getValues());

        return jdbc.update("insert into tag (titulek, id_obrazek) values (:titulek, :id_obrazek)", params) == 1;
    }

    public boolean update(Tag tag) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("titulek", tag.getTitulek());
        params.addValue("id_obrazek", tag.getObrazek().getId_obrazek());
        return jdbc.update("update tag set   titulek=:titulek  where id_obrazek=:id_obrazek", params) == 1;
    }

    public boolean delete(Tag tag) {
        MapSqlParameterSource params = new MapSqlParameterSource("id_obrazek", tag.getObrazek().getId_obrazek());
        params.addValue("titulek", tag.getTitulek());

        return jdbc.update("delete from tag where id_obrazek=:id_obrazek and titulek=:titulek", params) == 1;
    }

    public void deleteTags() {
        jdbc.getJdbcOperations().execute("DELETE FROM tag");
    }
}
