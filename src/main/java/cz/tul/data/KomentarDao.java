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
public class KomentarDao {
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<Komentar> getKomentare() {

        return jdbc
                .query("SELECT id_komentar, komentar.id_user as 'commenter_id', user2.jmeno as 'commenter_jmeno'," +
                                "user2.user_datum_registrace  as 'commenter_datum_registrace', komentar.id_obrazek," +
                                "komentar_datum_vytvoreni, komentar_datum_editace, komentar_pocet_likes, text, url, nazev, obrazek_datum_vytvoreni," +
                                "obrazek_datum_editace, obrazek_pocet_likes, obrazek.id_user as 'creator_id'," +
                                "user.jmeno as 'creator_jmeno', user.user_datum_registrace as 'creator_datum_registrace'" +
                                " FROM komentar, obrazek INNER JOIN user ON (obrazek.id_user=user.id_user)," +
                                "user AS user2 WHERE komentar.id_user=user2.id_user and komentar.id_obrazek=obrazek.id_obrazek",
                        (ResultSet rs, int rowNum) -> {
                            User commenter = new User();
                            commenter.setId_user(rs.getInt("commenter_id"));
                            commenter.setJmeno(rs.getString("commenter_jmeno"));
                            commenter.setDatum_registrace(rs.getString("commenter_datum_registrace"));

                            User creator = new User();
                            creator.setId_user(rs.getInt("creator_id"));
                            creator.setJmeno(rs.getString("creator_jmeno"));
                            creator.setDatum_registrace(rs.getString("creator_datum_registrace"));

                            Obrazek obrazek = new Obrazek();
                            obrazek.setId_obrazek(rs.getInt("id_obrazek"));
                            obrazek.setUrl(rs.getString("url"));
                            obrazek.setNazev(rs.getString("nazev"));
                            obrazek.setObrazek_datum_vytvoreni(rs.getString("obrazek_datum_vytvoreni"));
                            obrazek.setObrazek_datum_editace(rs.getString("obrazek_datum_editace"));
                            obrazek.setObrazek_pocet_likes(rs.getInt("obrazek_pocet_likes"));
                            obrazek.setUser(creator);

                            Komentar komentar = new Komentar();
                            komentar.setId_komentar(rs.getInt("id_obrazek"));
                            komentar.setKomentar_datum_vytvoreni(rs.getString("komentar_datum_vytvoreni"));
                            komentar.setKomentar_datum_editace(rs.getString("komentar_datum_editace"));
                            komentar.setKomentar_pocet_likes(rs.getInt("komentar_pocet_likes"));
                            komentar.setText(rs.getString("text"));
                            komentar.setUser(commenter);
                            komentar.setObrazek(obrazek);

                            return komentar;
                        }
                );
    }


    public boolean update(Komentar komentar) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                komentar);

        return jdbc.update("update komentar set text=:text where id_komentar=:id_komentar", params) == 1;
    }

    public boolean create(Komentar komentar) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                komentar);

        return jdbc
                .update("insert into komentar (id_autor, id_image, comment_creation, comment_lastedit, comment_likes, text)" +
                                "values (:user.id_user, :image.id_image, :comment_creation, :comment_lastedit, :comment_likes, :text)",
                        params) == 1;
    }

    public boolean changeLikes(Komentar komentar, boolean like) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_komentar", komentar.getId_komentar());
        if (like) {
            params.addValue("pocet_likes", komentar.getKomentar_pocet_likes() + 1);
        } else {
            params.addValue("pocet_dislikes", komentar.getKomentar_pocet_likes() - 1);
        }
        return jdbc.update("update komentar set pocet_likes=:pocet_likes where id_komentar=:id_komentar", params) == 1;
    }

    @Transactional
    public int[] create(List<Komentar> komentars) {

        SqlParameterSource[] params = SqlParameterSourceUtils
                .createBatch(komentars.toArray());

        return jdbc
                .batchUpdate("insert into komentar (id_user, id_image, comment_creation, comment_lastedit, comment_likes, text)" +
                                "values (:user.id_user, :image.id_image, :comment_creation, :comment_lastedit, :comment_likes, :text)",
                        params);
    }

    public boolean delete(int id_komentar) {
        MapSqlParameterSource params = new MapSqlParameterSource("id_komentar", id_komentar);

        return jdbc.update("delete from komentar where id_komentar=:id_komentar", params) == 1;
    }

    public Komentar getKomentar(int id_komentar) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_komentar", id_komentar);

        return jdbc.queryForObject("SELECT id_komentar, komentar.id_user as 'commenter_id', user2.jmeno as 'commenter_jmeno'," +
                        "user2.user_datum_registrace  as 'commenter_datum_registrace', komentar.id_obrazek," +
                        "komentar_datum_vytvoreni, komentar_datum_editace, komentar_pocet_likes, text, url, nazev, obrazek_datum_vytvoreni," +
                        "obrazek_datum_editace, obrazek_pocet_likes, obrazek.id_user as 'creator_id'," +
                        "user.jmeno as 'creator_jmeno', user.user_datum_registrace as 'creator_datum_registrace'" +
                        " FROM komentar, obrazek INNER JOIN user ON (obrazek.id_user=user.id_user)," +
                        "user AS user2 WHERE komentar.id_user=user2.id_user and komentar.id_obrazek=obrazek.id_obrazek", params,


                new RowMapper<Komentar>() {

                    public Komentar mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        User commenter = new User();
                        commenter.setId_user(rs.getInt("commenter_id"));
                        commenter.setJmeno(rs.getString("commenter_jmeno"));
                        commenter.setDatum_registrace(rs.getString("commenter_datum_registrace"));

                        User creator = new User();
                        creator.setId_user(rs.getInt("creator_id"));
                        creator.setJmeno(rs.getString("creator_jmeno"));
                        creator.setDatum_registrace(rs.getString("creator_datum_registrace"));

                        Obrazek obrazek = new Obrazek();
                        obrazek.setId_obrazek(rs.getInt("id_obrazek"));
                        obrazek.setUrl(rs.getString("url"));
                        obrazek.setNazev(rs.getString("nazev"));
                        obrazek.setObrazek_datum_vytvoreni(rs.getString("obrazek_datum_vytvoreni"));
                        obrazek.setObrazek_datum_editace(rs.getString("obrazek_datum_editace"));
                        obrazek.setObrazek_pocet_likes(rs.getInt("obrazek_pocet_likes"));
                        obrazek.setUser(creator);

                        Komentar komentar = new Komentar();
                        komentar.setId_komentar(rs.getInt("id_obrazek"));
                        komentar.setKomentar_datum_vytvoreni(rs.getString("datum_vytvoreni"));
                        komentar.setKomentar_datum_editace(rs.getString("datum_editace"));
                        komentar.setKomentar_pocet_likes(rs.getInt("pocet_likes"));
                        komentar.setText(rs.getString("text"));
                        komentar.setUser(commenter);
                        komentar.setObrazek(obrazek);

                        return komentar;
                    }

                });
    }


    public void deleteComments() {
        jdbc.getJdbcOperations().execute("DELETE FROM komentar");
    }
}
