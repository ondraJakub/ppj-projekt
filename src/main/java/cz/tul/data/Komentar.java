package cz.tul.data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@Entity
@Table(name = "komentar")
public class Komentar {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="text")
    private String text;

    @Column(name="datum_vytvoreni")
    private Date datum_vytvoreni;

    @Column(name="datum_editace")
    private Date datum_editace;

    @Column(name="pocet_likes")
    private Integer pocet_likes;

    @Column(name="pocet_dislikes")
    private Integer pocet_dislikes;

    @Column(name = "id_user")
    private Integer id_user;

    @Column(name = "id_obrazek")
    private Integer id_obrazek;

    public Komentar(Integer user, Integer id_obrazek) {
        this.id_user = user;
        this.id_obrazek =id_obrazek;
    }

    public Komentar() {
    }

    public Komentar(Integer user, Integer id_obrazek, Date datum_vytvoreni, String text) {
        this.id_user = user;
        this.id_obrazek = id_obrazek;
        this.datum_vytvoreni = datum_vytvoreni;
        this.datum_editace = datum_vytvoreni;
        this.pocet_likes = 0;
        this.pocet_dislikes = 0;
        this.text = text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(Integer user) {
        this.id_user = user;
    }

    public void setId_obrazek(Integer id_obrazek) {
        this.id_obrazek = id_obrazek;
    }

    public void setDatum_vytvoreni(Date datum_vytvoreni) {
        this.datum_vytvoreni = datum_vytvoreni;
    }

    public void setDatum_editace(Date datum_editace) {
        this.datum_editace = datum_editace;
    }

    public void setPocet_likes(Integer pocet_likes) {
        this.pocet_likes = pocet_likes;
    }


    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getUser() {
        return id_user;
    }

    public Integer getId_obrazek() {
        return id_obrazek;
    }

    public Date getDatum_vytvoreni() {
        return datum_vytvoreni;
    }

    public Date getDatum_editace() {
        return datum_editace;
    }

    public Integer getPocet_likes() {
        return pocet_likes;
    }

    public Integer getPocet_dislikes() {
        return pocet_dislikes;
    }

    public void setPocet_dislikes(Integer pocet_dislikes) {
        this.pocet_dislikes = pocet_dislikes;
    }

    @Override
    public String toString() {
        return "Komentar{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", autor=" + id_user +
                ", obrazek_id=" + id_obrazek +
                ", datum_vytvoreni=" + datum_vytvoreni +
                ", datum_editace=" + datum_editace +
                ", pocet_likes=" + pocet_likes +
                ", pocet_dislikes=" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Komentar komentar = (Komentar) o;

        if (id != null ? !id.equals(komentar.id) : komentar.id != null)
            return false;
        if (text != null ? !text.equals(komentar.text) : komentar.text != null) return false;
        if (datum_vytvoreni != null ? !datum_vytvoreni.equals(komentar.datum_vytvoreni) : komentar.datum_vytvoreni != null)
            return false;
        if (datum_editace != null ? !datum_editace.equals(komentar.datum_editace) : komentar.datum_editace != null)
            return false;
        if (pocet_likes != null ? !pocet_likes.equals(komentar.pocet_likes) : komentar.pocet_likes != null)
            return false;

        if (id_user != null ? !id_user.equals(komentar.id_user) : komentar.id_user != null) return false;
        return id_obrazek != null ? id_obrazek.equals(komentar.id_obrazek) : komentar.id_obrazek == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (datum_vytvoreni != null ? datum_vytvoreni.hashCode() : 0);
        result = 31 * result + (datum_editace != null ? datum_editace.hashCode() : 0);
        result = 31 * result + (pocet_likes != null ? pocet_likes.hashCode() : 0);
        result = 31 * result + (id_user != null ? id_user.hashCode() : 0);
        result = 31 * result + (id_obrazek != null ? id_obrazek.hashCode() : 0);
        return result;
    }
}
