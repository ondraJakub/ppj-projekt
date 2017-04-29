package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        return jdbc.query("SELECT * FROM obrazek", BeanPropertyRowMapper.newInstance(Obrazek.class));
    }

    public boolean update(Obrazek obrazek) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_obrazek", obrazek.getId_obrazek());
        params.addValue("nazev", obrazek.getNazev());
        params.addValue("url", obrazek.getUrl());
        params.addValue("datum_vytvoreni", obrazek.getDatum_vytvoreni());
        params.addValue("datum_editace", obrazek.getDatum_editace());
        params.addValue("pocet_likes", obrazek.getPocet_likes());
        params.addValue("pocet_dislikes", obrazek.getPocet_dislikes());
        return jdbc.update("update obrazek set nazev=:nazev, url=:url, datum_vytvoreni=:datum_vytvoreni, datum_editace=:datum_editace, pocet_likes=:pocet_likes, pocet_dislikes=:pocet_dislikes  where id_obrazek=:id_obrazek", params) == 1;
    }

    public boolean pridejLike(Obrazek obrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        obrazek.setPocet_likes(obrazek.getPocet_likes() + 1);
        params.addValue("id_obrazek", obrazek.getId_obrazek());
        params.addValue("pocet_likes", obrazek.getPocet_likes() + 1);
        return jdbc.update("update obrazek set pocet_likes=:pocet_likes where id_obrazek=:id_obrazek", params) == 1;
    }

    public boolean pridejDislike(Obrazek obrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        obrazek.setPocet_dislikes(obrazek.getPocet_dislikes() + 1);
        params.addValue("id_obrazek", obrazek.getId_obrazek());
        params.addValue("pocet_dislikes", obrazek.getPocet_likes() + 1);
        return jdbc.update("update obrazek set pocet_dislikes=:pocet_dislikes where id_obrazek=:id_obrazek", params) == 1;
    }

    public boolean create(Obrazek obrazek) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_obrazek", obrazek.getId_obrazek());
        params.addValue("nazev", obrazek.getNazev());
        params.addValue("url", obrazek.getUrl());
        params.addValue("datum_vytvoreni", obrazek.getDatum_vytvoreni());
        params.addValue("datum_editace", obrazek.getDatum_editace());
        params.addValue("pocet_likes", obrazek.getPocet_likes());
        params.addValue("pocet_dislikes", obrazek.getPocet_dislikes());
        params.addValue("id_user", obrazek.getUser().getId_user());

        return jdbc.update("insert into obrazek (id_user, url, nazev, datum_vytvoreni, datum_editace, pocet_likes, pocet_dislikes) values (:id_user, :url, :nazev, :datum_vytvoreni, :datum_editace, :pocet_likes, :pocet_dislikes)",
                        params) == 1;
    }

    public boolean delete(int id_obrazek) {
        MapSqlParameterSource params = new MapSqlParameterSource("id_obrazek", id_obrazek);

        return jdbc.update("delete from obrazek where id_obrazek=:id_obrazek", params) == 1;
    }

    public Obrazek getObrazek(String url) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("url", url);

        return jdbc.queryForObject("select * from obrazek, user where obrazek.id_user=user.id_user and url=:url", params,
                (rs, rowNum) -> {
                    User user = new User(rs.getInt("id_user"), rs.getString("jmeno"), rs.getDate("datum_registrace"));
                    Obrazek obrazek = new Obrazek(rs.getInt("id_obrazek"), rs.getString("nazev"), rs.getString("url"), user, rs.getDate("datum_vytvoreni"), rs.getDate("datum_editace"), rs.getInt("pocet_likes"), rs.getInt("pocet_dislikes"));
                    obrazek.setUser(user);

                    return obrazek;
                });
    }

    public void deleteObrazek(int id_obrazek) {
        String query = "DELETE FROM obrazek WHERE id_obrazek=" + id_obrazek;
        jdbc.getJdbcOperations().execute(query);
    }

    public void deleteObrazky() {
        jdbc.getJdbcOperations().execute("DELETE FROM obrazek");
    }
}
