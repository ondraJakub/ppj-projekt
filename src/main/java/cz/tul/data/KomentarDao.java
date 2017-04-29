package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
public class KomentarDao {
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<Komentar> getKomentare() {
        return jdbc.query("SELECT * FROM komentar", BeanPropertyRowMapper.newInstance(Komentar.class));
    }


    public boolean update(Komentar komentar) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_komentar", komentar.getId_komentar());
        params.addValue("text", komentar.getText());

        return jdbc.update("update komentar set text=:text where id_komentar=:id_komentar", params) == 1;
    }

    public boolean create(Komentar komentar) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_komentar", komentar.getId_komentar());
        params.addValue("id_obrazek", komentar.getObrazek().getId_obrazek());
        params.addValue("id_user", komentar.getUser().getId_user());
        params.addValue("datum_vytvoreni", komentar.getDatum_vytvoreni());
        params.addValue("datum_editace", komentar.getDatum_editace());
        params.addValue("pocet_likes", komentar.getPocet_likes());
        params.addValue("pocet_dislikes", komentar.getPocet_dislikes());
        params.addValue("text", komentar.getText());

        return jdbc
                .update("insert into komentar (id_user, id_obrazek, datum_vytvoreni, datum_editace, pocet_likes, pocet_dislikes, text)" +
                                "values (:id_user, :id_obrazek, :datum_vytvoreni, :datum_editace, :pocet_likes, :pocet_dislikes, :text)",
                        params) == 1;
    }

    public boolean pridejLike(Komentar komentar) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_komentar", komentar.getId_komentar());
        params.addValue("pocet_likes", komentar.getPocet_likes() + 1);
        return jdbc.update("update komentar set pocet_likes=:pocet_likes where id_komentar=:id_komentar", params) == 1;
    }

    public boolean pridejDislike(Komentar komentar) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_komentar", komentar.getId_komentar());
        params.addValue("pocet_dislikes", komentar.getPocet_dislikes() + 1);
        return jdbc.update("update komentar set pocet_dislikes=:pocet_dislikes where id_komentar=:id_komentar", params) == 1;
    }

    public boolean delete(int id_komentar) {
        MapSqlParameterSource params = new MapSqlParameterSource("id_komentar", id_komentar);

        return jdbc.update("delete from komentar where id_komentar=:id_komentar", params) == 1;
    }

    public void deleteKomentare() {
        jdbc.getJdbcOperations().execute("DELETE FROM komentar");
    }
}
