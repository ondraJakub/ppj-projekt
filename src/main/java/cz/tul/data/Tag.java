package cz.tul.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@Entity
@Table(name = "tag")
@IdClass(TagId.class)
public class Tag implements Serializable {

    @Id
    @Column(name = "titulek")
    private String titulek;

    @Id
    @Column(name = "obrazek_id")
    private Integer obrazekId;

    public Tag(String titulek, Integer obrazekId) {
        this.titulek = titulek;
        this.obrazekId = obrazekId;
    }

    public Tag() {
    }


    public String getTitulek() {
        return titulek;
    }

    public Integer getObrazekId() {
        return obrazekId;
    }


    public void setTitulek(String titulek) {
        this.titulek = titulek;
    }

    public void setObrazekId(Integer obrazekId) {
        this.obrazekId = obrazekId;
    }

    @Override
    public String toString() {
        return "Tag{titulek='" + titulek + '\'' +
                ", obrazekId=" + obrazekId +
                '}';
    }
}
