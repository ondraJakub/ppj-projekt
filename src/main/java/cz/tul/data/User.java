package cz.tul.data;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="jmeno")
    private String jmeno;

    @Column(name="datum_registrace")
    private Date datum_registrace;

    public User() {
    }

    public User(String jmeno) {
        this.jmeno = jmeno;
    }

    public User(int id, String jmeno, Date datum_registrace) {
        this.id = id;
        this.jmeno = jmeno;
        this.datum_registrace = datum_registrace;
    }

    public User(String jmeno, Date datum_registrace) {
        this.jmeno = jmeno;
        this.datum_registrace = datum_registrace;
    }

    public Integer getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public Date getDatum_registrace() {
        return datum_registrace;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setDatum_registrace(Date datum_registrace) {
        this.datum_registrace = datum_registrace;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", jmeno='" + jmeno + '\'' +
                ", datum_registrace='" + datum_registrace + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (jmeno != null ? !jmeno.equals(user.jmeno) : user.jmeno != null) return false;
        return datum_registrace != null ? datum_registrace.equals(user.datum_registrace) : user.datum_registrace == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jmeno != null ? jmeno.hashCode() : 0);
        result = 31 * result + (datum_registrace != null ? datum_registrace.hashCode() : 0);
        return result;
    }
}
