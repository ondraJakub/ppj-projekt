package cz.tul.data;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime datum_vytvoreni;

    @Column(name="datum_editace")
    private LocalDateTime datum_editace;

    @Column(name="pocet_likes")
    private Integer pocet_likes;

    @Column(name="pocet_dislikes")
    private Integer pocet_dislikes;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_obrazek")
    private Obrazek obrazek;

    public Komentar() {
        this.user = new User();
        this.obrazek = new Obrazek();
    }

    public Komentar(User user, Obrazek obrazek, LocalDateTime datum_vytvoreni, String text) {
        this.user = user;
        this.obrazek = obrazek;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setObrazek(Obrazek obrazek) {
        this.obrazek = obrazek;
    }

    public void setDatum_vytvoreni(LocalDateTime datum_vytvoreni) {
        this.datum_vytvoreni = datum_vytvoreni;
    }

    public void setDatum_editace(LocalDateTime datum_editace) {
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

    public User getUser() {
        return user;
    }

    public Obrazek getObrazek() {
        return obrazek;
    }

    public LocalDateTime getDatum_vytvoreni() {
        return datum_vytvoreni;
    }

    public LocalDateTime getDatum_editace() {
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
                ", autor=" + user +
                ", obrazek=" + obrazek +
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

        if (user != null ? !user.equals(komentar.user) : komentar.user != null) return false;
        return obrazek != null ? obrazek.equals(komentar.obrazek) : komentar.obrazek == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (datum_vytvoreni != null ? datum_vytvoreni.hashCode() : 0);
        result = 31 * result + (datum_editace != null ? datum_editace.hashCode() : 0);
        result = 31 * result + (pocet_likes != null ? pocet_likes.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (obrazek != null ? obrazek.hashCode() : 0);
        return result;
    }
}
