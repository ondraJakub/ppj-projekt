package cz.tul.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@Entity
@Table(name = "tag")
public class Tag implements Serializable {

    @Id
    @Column(name = "titulek")
    private String titulek;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_obrazek")
    private Obrazek obrazek;

    public Tag(String titulek, Obrazek obrazek) {
        this.titulek = titulek;
        this.obrazek = obrazek;
    }

    public Tag() {
    }


    public String getTitulek() {
        return titulek;
    }

    public Obrazek getObrazek() {
        return obrazek;
    }


    public void setTitulek(String titulek) {
        this.titulek = titulek;
    }

    public void setObrazek(Obrazek obrazek) {
        this.obrazek = obrazek;
    }

    @Override
    public String toString() {
        return "Tag{titulek='" + titulek + '\'' +
                ", obrazek=" + obrazek +
                '}';
    }
}
