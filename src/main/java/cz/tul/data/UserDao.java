package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;

public class UserDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(User user) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("jmeno", user.getJmeno());
        params.addValue("datum_registrace", user.getDatum_registrace());

        return jdbc.update("insert into user (jmeno, datum_registrace) values (:jmeno, :datum_registrace)", params) == 1;
    }

    public User getUser(String jmeno) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("jmeno", jmeno);

        return jdbc.queryForObject("select * from user where jmeno=:jmeno", params,
                (ResultSet rs, int rowNum) -> {
                    return new User(rs.getInt("id_user"),rs.getString("jmeno"), rs.getDate("datum_registrace"));
                });
    }

    public boolean update(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_user", user.getId_user());
        params.addValue("jmeno", user.getJmeno());
        params.addValue("datum_registrace", user.getDatum_registrace());
        return jdbc.update("update user set jmeno=:jmeno, datum_registrace=:datum_registrace where id_user=:id_user", params) == 1;
    }



    public List<User> getAllUsers() {
        return jdbc.query("SELECT * FROM user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public void deleteUser(int id_user) {
        String query = "DELETE FROM user WHERE id_user="+id_user;
        jdbc.getJdbcOperations().execute(query);
    }

    public void deleteUsers() {
        jdbc.getJdbcOperations().execute("DELETE FROM user");
    }
}
