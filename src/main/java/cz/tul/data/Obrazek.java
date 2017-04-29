package cz.tul.data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@Entity
@Table(name = "obrazek")
public class Obrazek {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nazev")
    private String nazev;

    @Column(name = "url")
    private String url;

    @Column(name = "datum_vytvoreni")
    private Date datum_vytvoreni;

    @Column(name = "datum_editace")
    private Date datum_editace;

    @Column(name = "pocet_likes")
    private Integer pocet_likes;

    @Column(name = "pocet_dislikes")
    private Integer pocet_dislikes;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    public Obrazek(int id, String nazev, String url, User user, Date datum_vytvoreni, Date datum_editace, Integer pocet_likes, Integer pocet_dislikes) {
        this.id = id;
        this.nazev = nazev;
        this.url = url;
        this.user = user;
        this.datum_vytvoreni = datum_vytvoreni;
        this.datum_editace = datum_editace;
        this.pocet_likes = pocet_likes;
        this.pocet_dislikes = pocet_dislikes;
    }

    public Obrazek(User user, String url, String nazev, Date datum_vytvoreni) {
        this.user = user;
        this.url = url;
        this.nazev = nazev;
        this.datum_vytvoreni = datum_vytvoreni;
        this.datum_editace = datum_vytvoreni;
        this.pocet_likes = 0;
        this.pocet_dislikes = 0;
    }

    public Obrazek() {
    }

    public Obrazek(Obrazek obrazek) {
        this.nazev = obrazek.nazev;
        this.url = obrazek.url;
        this.user = obrazek.user;
        this.datum_vytvoreni = obrazek.datum_vytvoreni;
        this.datum_editace = obrazek.datum_editace;
        this.pocet_likes = obrazek.pocet_likes;
        this.pocet_dislikes = obrazek.pocet_dislikes;
    }


    public Integer getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public String getUrl() {
        return url;
    }

    public User getUser() {
        return user;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDatum_vytvoreni(Date datum_vytvoreni) {
        this.datum_vytvoreni = datum_vytvoreni;
    }

    public void setDatum_editace(Date datum_editace) {
        this.datum_editace = datum_editace;
    }

    public Integer getPocet_dislikes() {
        return pocet_dislikes;
    }

    public void setPocet_dislikes(Integer pocet_dislikes) {
        this.pocet_dislikes = pocet_dislikes;
    }

    public void setPocet_likes(Integer pocet_likes) {
        this.pocet_likes = pocet_likes;
    }

    @Override
    public String toString() {
        return "Obrazek{" +
                "id='" + id + '\'' +
                ", nazev='" + nazev + '\'' +
                ", url='" + url + '\'' +
                ", autor=" + user +
                ", datum_vytvoreni=" + datum_vytvoreni +
                ", datum_editace=" + datum_editace +
                ", pocet_likes=" + pocet_likes +
                ", pocet_dislikes=" +
                '}';
    }
}
