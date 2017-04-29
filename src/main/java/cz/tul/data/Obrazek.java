package cz.tul.data;

import java.util.Date;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
public class Obrazek {
    private Integer id_obrazek;
    private String nazev;
    private String url;
    private Date datum_vytvoreni;
    private Date datum_editace;
    private Integer pocet_likes;
    private Integer pocet_dislikes;
    private User user;


    public Obrazek(int id_obrazek, String nazev, String url, User user, Date datum_vytvoreni, Date datum_editace, Integer pocet_likes, Integer pocet_dislikes) {
        this.id_obrazek = id_obrazek;
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

    public Integer getId_obrazek() {
        return id_obrazek;
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

    public void setId_obrazek(Integer id_obrazek) {
        this.id_obrazek = id_obrazek;
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

    public void setPocet_likes(Integer pocet_likes) {
        this.pocet_likes = pocet_likes;
    }

    public Integer getPocet_dislikes() {
        return pocet_dislikes;
    }

    public void setPocet_dislikes(Integer pocet_dislikes) {
        this.pocet_dislikes = pocet_dislikes;
    }

    @Override
    public String toString() {
        return "Obrazek{" +
                "id_obrazek='" + id_obrazek + '\'' +
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
