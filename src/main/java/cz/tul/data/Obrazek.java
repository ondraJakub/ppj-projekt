package cz.tul.data;

import javax.persistence.*;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
@Entity
@Table(name = "obrazek")
public class Obrazek {

    @Id
    @Column(name = "id_obrazek")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_obrazek;

    @Column(name = "nazev")
    private String nazev;

    @Column(name = "url")
    private String url;

    @Column(name = "obrazek_datum_vytvoreni")
    private String obrazek_datum_vytvoreni;

    @Column(name = "obrazek_datum_editace")
    private String obrazek_datum_editace;

    @Column(name = "obrazek_pocet_likes")
    private Integer obrazek_pocet_likes;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    public Obrazek(int id, String nazev, String url, User user, String datum_vytvoreni, String datum_editace, Integer pocet_likes) {
        this.id_obrazek = id;
        this.nazev = nazev;
        this.url = url;
        this.user = user;
        this.obrazek_datum_vytvoreni = datum_vytvoreni;
        this.obrazek_datum_editace = datum_editace;
        this.obrazek_pocet_likes = pocet_likes;
    }

    public Obrazek(User user, String url, String nazev, String datum_vytvoreni) {
        this.user = user;
        this.url = url;
        this.nazev = nazev;
        this.obrazek_datum_vytvoreni = datum_vytvoreni;
        this.obrazek_datum_editace = datum_vytvoreni;
        this.obrazek_pocet_likes = 0;
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

    public String getObrazek_datum_vytvoreni() {
        return obrazek_datum_vytvoreni;
    }

    public String getObrazek_datum_editace() {
        return obrazek_datum_editace;
    }

    public Integer getObrazek_pocet_likes() {
        return obrazek_pocet_likes;
    }

    public void setId_obrazek(Integer id) {
        this.id_obrazek = id;
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

    public void setObrazek_datum_vytvoreni(String obrazek_datum_vytvoreni) {
        this.obrazek_datum_vytvoreni = obrazek_datum_vytvoreni;
    }

    public void setObrazek_datum_editace(String obrazek_datum_editace) {
        this.obrazek_datum_editace = obrazek_datum_editace;
    }

    public void setObrazek_pocet_likes(Integer obrazek_pocet_likes) {
        this.obrazek_pocet_likes = obrazek_pocet_likes;
    }

    @Override
    public String toString() {
        return "Obrazek{" +
                "id='" + id_obrazek + '\'' +
                ", nazev='" + nazev + '\'' +
                ", url='" + url + '\'' +
                ", autor=" + user +
                ", datum_vytvoreni=" + obrazek_datum_vytvoreni +
                ", datum_editace=" + obrazek_datum_editace +
                ", pocet_likes=" + obrazek_pocet_likes +
                ", pocet_dislikes=" +
                '}';
    }
}
