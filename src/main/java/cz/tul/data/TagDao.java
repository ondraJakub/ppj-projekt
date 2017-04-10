package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
public class TagDao {
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<Tag> getTags() {

        return jdbc
                .query("SELECT * FROM tag, obrazek, user where tag.id_obrazek=obrazek.id_obrazek and obrazek.id_user=user.id_user",
                        (ResultSet rs, int rowNum) -> {
                            User user = new User();
                            user.setId(rs.getInt("id_user"));
                            user.setJmeno(rs.getString("jmeno"));
                            user.setDatum_registrace(rs.getString("user_datum_registrace"));

                            Obrazek obrazek = new Obrazek();
                            obrazek.setId(rs.getInt("id_obrazek"));
                            obrazek.setUrl(rs.getString("url"));
                            obrazek.setNazev(rs.getString("nazev"));
                            obrazek.setDatum_vytvoreni(rs.getString("obrazek_datum_vytvoreni"));
                            obrazek.setDatum_editace(rs.getString("obrazek_datum_editace"));
                            obrazek.setPocet_likes(rs.getInt("obrazek_pocet_likes"));
                            obrazek.setUser(user);

                            Tag tag = new Tag();
                            tag.setTitulek(rs.getString("tag_title"));
                            tag.setObrazek(obrazek);

                            return tag;
                        }
                );
    }

    public boolean create(Tag tag) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                tag);

        return jdbc
                .update("insert into tag (titulek, id_obrazek)" +
                                "values (:titulek, :obrazek.id_obrazek)",
                        params) == 1;
    }

    @Transactional
    public int[] create(List<Tag> tags) {

        SqlParameterSource[] params = SqlParameterSourceUtils
                .createBatch(tags.toArray());

        return jdbc
                .batchUpdate("insert into tag (titulek, id_obrazek)" +
                                "values (:titulek, :obrazek.id_obrazek)",
                        params);
    }

    public boolean delete(Tag tag) {
        MapSqlParameterSource params = new MapSqlParameterSource("id_obrazek", tag.getObrazek().getId());
        params.addValue("titulek", tag.getTitulek());

        return jdbc.update("delete from tag where id_obrazek=:id_obrazek and titulek=:titulek", params) == 1;
    }

    public Tag getTag(Tag tag) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_obrazek", tag.getObrazek().getId());
        params.addValue("titulek", tag.getTitulek());


        return jdbc.queryForObject("SELECT * FROM tag, obrazek, user where tag.id_obrazek=obrazek.id_obrazek and obrazek.id_user=user.id_user", params,
                new RowMapper<Tag>() {

                    public Tag mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        User user = new User();
                        user.setId(rs.getInt("id_user"));
                        user.setJmeno(rs.getString("jmeno"));
                        user.setDatum_registrace(rs.getString("user_datum_registrace"));

                        Obrazek obrazek = new Obrazek();
                        obrazek.setId(rs.getInt("id_obrazek"));
                        obrazek.setUrl(rs.getString("url"));
                        obrazek.setNazev(rs.getString("nazev"));
                        obrazek.setDatum_vytvoreni(rs.getString("obrazek_datum_vytvoreni"));
                        obrazek.setDatum_editace(rs.getString("obrazek_datum_editace"));
                        obrazek.setPocet_likes(rs.getInt("obrazek_pocet_likes"));
                        obrazek.setUser(user);

                        Tag tag = new Tag();
                        tag.setTitulek(rs.getString("tag_title"));
                        tag.setObrazek(obrazek);

                        return tag;
                    }

                });
    }


    public void deleteTags() {
        jdbc.getJdbcOperations().execute("DELETE FROM tag");
    }
}
