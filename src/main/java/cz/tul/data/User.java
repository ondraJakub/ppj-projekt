package cz.tul.data;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
public class User {

    private Integer id_user;
    private String jmeno;
    private String user_datum_registrace;

    public User() {
    }

    public User(int id_user, String jmeno, String datum_registrace) {
        this.id_user = id_user;
        this.jmeno = jmeno;
        this.user_datum_registrace = datum_registrace;
    }

    public User(String jmeno, String datum_registrace) {
        this.jmeno = jmeno;
        this.user_datum_registrace = datum_registrace;
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getDatum_registrace() {
        return user_datum_registrace;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setDatum_registrace(String datum_registrace) {
        this.user_datum_registrace = datum_registrace;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id_user + '\'' +
                ", jmeno='" + jmeno + '\'' +
                ", datum_registrace='" + user_datum_registrace + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id_user != null ? !id_user.equals(user.id_user) : user.id_user != null) return false;
        if (jmeno != null ? !jmeno.equals(user.jmeno) : user.jmeno != null) return false;
        return user_datum_registrace != null ? user_datum_registrace.equals(user.user_datum_registrace) : user.user_datum_registrace == null;

    }

    @Override
    public int hashCode() {
        int result = id_user != null ? id_user.hashCode() : 0;
        result = 31 * result + (jmeno != null ? jmeno.hashCode() : 0);
        result = 31 * result + (user_datum_registrace != null ? user_datum_registrace.hashCode() : 0);
        return result;
    }
}
