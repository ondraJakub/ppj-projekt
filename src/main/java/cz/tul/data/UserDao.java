package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
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
        params.addValue("user_datum_registrace", user.getDatum_registrace());

        return jdbc.update("insert into user (jmeno, user_datum_registrace) values (:jmeno, :user_datum_registrace)", params) == 1;
    }

    public boolean exists(String jmeno) {
        return jdbc.queryForObject("select count(*) from user where jmeno=:jmeno",
                new MapSqlParameterSource("jmeno", jmeno), Integer.class) > 0;
    }

    public User getUser(int id_user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_user", id_user);

        return jdbc.queryForObject("select * from user where id_user=:id_user", params,
                (ResultSet rs, int rowNum) -> {
                    User user = new User();
                    user.setId_user(rs.getInt("id_user"));
                    user.setJmeno(rs.getString("jmeno"));
                    user.setDatum_registrace(rs.getString("user_datum_registrace"));

                    return user;
                });
    }

    public User getUser(String jmeno) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("jmeno", jmeno);

        return jdbc.queryForObject("select * from user where jmeno=:jmeno", params,
                (ResultSet rs, int rowNum) -> {
                    User user = new User();
                    user.setId_user(rs.getInt("id_user"));
                    user.setJmeno(rs.getString("jmeno"));
                    user.setDatum_registrace(rs.getString("user_datum_registrace"));

                    return user;
                });
    }



    public List<User> getAllUsers() {
        return jdbc
                .query("select * from user",
                        (ResultSet rs, int rowNum) -> {
                            User user = new User();
                            user.setId_user(rs.getInt("id_user"));
                            user.setJmeno(rs.getString("jmeno"));
                            user.setDatum_registrace(rs.getString("user_datum_registrace"));

                            return user;
                        }
                );
    }

    public void deleteUser(int id_user) {
        String query = "DELETE FROM user WHERE id_user="+id_user;
        jdbc.getJdbcOperations().execute(query);
    }

    public void deleteUsers() {
        jdbc.getJdbcOperations().execute("DELETE FROM user");
    }
}
