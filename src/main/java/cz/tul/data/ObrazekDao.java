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
public class ObrazekDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<Obrazek> getObrazky() {

        return jdbc
                .query("select * from obrazek, user where obrazek.id_user=user.id_user",
                        (ResultSet rs, int rowNum) -> {
                            User user = new User();
                            user.setId_user(rs.getInt("id_user"));
                            user.setJmeno(rs.getString("jmeno"));
                            user.setDatum_registrace(rs.getString("user_datum_registrace"));

                            Obrazek obrazek = new Obrazek();
                            obrazek.setId_obrazek(rs.getInt("id_obrazek"));
                            obrazek.setUrl(rs.getString("url"));
                            obrazek.setNazev(rs.getString("nazev"));
                            obrazek.setObrazek_datum_vytvoreni(rs.getString("obrazek_datum_vytvoreni"));
                            obrazek.setObrazek_datum_editace(rs.getString("obrazek_datum_editace"));
                            obrazek.setObrazek_pocet_likes(rs.getInt("obrazek_pocet_likes"));
                            obrazek.setUser(user);

                            return obrazek;
                        }
                );
    }


    public List<Obrazek> getObrazky_innerjoin() {

        return jdbc
                .query("select * from obrazek join user using (id_user)",
                        (ResultSet rs, int rowNum) -> {
                            User user = new User();
                            user.setId_user(rs.getInt("id_user"));
                            user.setJmeno(rs.getString("jmeno"));
                            user.setDatum_registrace(rs.getString("user_datum_registrace"));

                            Obrazek obrazek = new Obrazek();
                            obrazek.setId_obrazek(rs.getInt("id_obrazek"));
                            obrazek.setUrl(rs.getString("url"));
                            obrazek.setNazev(rs.getString("nazev"));
                            obrazek.setObrazek_datum_vytvoreni(rs.getString("obrazek_datum_vytvoreni"));
                            obrazek.setObrazek_datum_editace(rs.getString("obrazek_datum_editace"));
                            obrazek.setObrazek_pocet_likes(rs.getInt("obrazek_pocet_likes"));
                            obrazek.setUser(user);

                            return obrazek;
                        }
                );
    }

    public boolean update(Obrazek obrazek) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                obrazek);
        return jdbc.update("update obrazek set nazev=:nazev where id_obrazek=:id_obrazek", params) == 1;
    }

    public boolean changeLikes(Obrazek obrazek, boolean like) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_obrazek", obrazek.getId_obrazek());
        if (like) {
            obrazek.setObrazek_pocet_likes(obrazek.getObrazek_pocet_likes() + 1);
            params.addValue("obrazek_pocet_likes", obrazek.getObrazek_pocet_likes() + 1);
        } else {
            obrazek.setObrazek_pocet_likes(obrazek.getObrazek_pocet_likes() - 1);
            params.addValue("obrazek_pocet_likes", obrazek.getObrazek_pocet_likes() - 1);
        }
        return jdbc.update("update obrazek set obrazek_pocet_likes=:obrazek_pocet_likes where id_obrazek=:id_obrazek", params) == 1;
    }

    public boolean create(Obrazek obrazek) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                obrazek);

        return jdbc
                .update("insert into obrazek (id_user, url, nazev, obrazek_datum_vytvoreni, obrazek_datum_editace) values (:user.id_user, :url, :nazev, :obrazek_datum_vytvoreni, :obrazek_datum_editace)",
                        params) == 1;
    }

    @Transactional
    public int[] create(List<Obrazek> obrazky) {

        SqlParameterSource[] params = SqlParameterSourceUtils
                .createBatch(obrazky.toArray());

        return jdbc
                .batchUpdate("insert into obrazek (id_user, url, nazev, obrazek_datum_vytvoreni, obrazek_datum_editace) values (:user.id_user, :url, :nazev, :obrazek_datum_vytvoreni, :obrazek_datum_editace)",
                        params);
    }

    public boolean delete(int id_obrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource("id_obrazek", id_obrazek);

        return jdbc.update("delete from obrazek where id_obrazek=:id_obrazek", params) == 1;
    }

    public Obrazek getObrazek(int id_obrazek) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_obrazek", id_obrazek);

        return jdbc.queryForObject("select * from obrazek, user where obrazek.id_user=user.id_user and id_obrazek=:id_obrazek", params,
                new RowMapper<Obrazek>() {

                    public Obrazek mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        User user = new User();
                        user.setId_user(rs.getInt("id_user"));
                        user.setJmeno(rs.getString("jmeno"));
                        user.setDatum_registrace(rs.getString("user_datum_registrace"));

                        Obrazek obrazek = new Obrazek();
                        obrazek.setId_obrazek(rs.getInt("id_obrazek"));
                        obrazek.setUrl(rs.getString("url"));
                        obrazek.setNazev(rs.getString("nazev"));
                        obrazek.setObrazek_datum_vytvoreni(rs.getString("obrazek_datum_vytvoreni"));
                        obrazek.setObrazek_datum_editace(rs.getString("obrazek_datum_editace"));
                        obrazek.setObrazek_pocet_likes(rs.getInt("obrazek_pocet_likes"));
                        obrazek.setUser(user);

                        return obrazek;
                    }

                });
    }

    public Obrazek getObrazek(String url) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("url", url);

        return jdbc.queryForObject("select * from obrazek, user where obrazek.id_user=user.id_user and url=:url", params,
                new RowMapper<Obrazek>() {

                    public Obrazek mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        User user = new User();
                        user.setId_user(rs.getInt("id_user"));
                        user.setJmeno(rs.getString("jmeno"));
                        user.setDatum_registrace(rs.getString("user_datum_registrace"));

                        Obrazek obrazek = new Obrazek();
                        obrazek.setId_obrazek(rs.getInt("id_obrazek"));
                        obrazek.setUrl(rs.getString("url"));
                        obrazek.setNazev(rs.getString("nazev"));
                        obrazek.setObrazek_datum_vytvoreni(rs.getString("obrazek_datum_vytvoreni"));
                        obrazek.setObrazek_datum_editace(rs.getString("obrazek_datum_editace"));
                        obrazek.setObrazek_pocet_likes(rs.getInt("obrazek_pocet_likes"));
                        obrazek.setUser(user);

                        return obrazek;
                    }

                });
    }

    public void deleteObrazek(int id_obrazek) {
        String query = "DELETE FROM obrazek WHERE id_obrazek=" + id_obrazek;
        jdbc.getJdbcOperations().execute(query);
    }

    public void deleteObrazky() {
        jdbc.getJdbcOperations().execute("DELETE FROM komentar");
        jdbc.getJdbcOperations().execute("DELETE FROM obrazek");
    }
}
