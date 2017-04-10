package cz.tul.data;

/**
 * Created by Ondrej Jakub on 4/3/2017.
 */
public class Komentar {
    private Integer id_komentar;
    private String text;

    private String komentar_datum_vytvoreni;
    private String komentar_datum_editace;
    private Integer komentar_pocet_likes;

    private User user;
    private Obrazek obrazek;


    public Komentar(int id_komentar, String text, User user, Obrazek obrazek, String datum_vytvoreni, String datum_editace, Integer pocet_likes, Integer pocet_dislikes) {
        this.id_komentar = id_komentar;
        this.text = text;
        this.user = user;
        this.obrazek = obrazek;
        this.komentar_datum_vytvoreni = datum_vytvoreni;
        this.komentar_datum_editace = datum_editace;
        this.komentar_pocet_likes = pocet_likes;
    }

    public Komentar() {
    }

    public void setId_komentar(Integer id_komentar) {
        this.id_komentar = id_komentar;
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

    public void setKomentar_datum_vytvoreni(String datum_vytvoreni) {
        this.komentar_datum_vytvoreni = datum_vytvoreni;
    }

    public void setKomentar_datum_editace(String datum_editace) {
        this.komentar_datum_editace = datum_editace;
    }

    public void setKomentar_pocet_likes(Integer pocet_likes) {
        this.komentar_pocet_likes = pocet_likes;
    }


    public Integer getId_komentar() {
        return id_komentar;
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

    public String getKomentar_datum_vytvoreni() {
        return komentar_datum_vytvoreni;
    }

    public String getKomentar_datum_editace() {
        return komentar_datum_editace;
    }

    public Integer getKomentar_pocet_likes() {
        return komentar_pocet_likes;
    }


    @Override
    public String toString() {
        return "Komentar{" +
                "id='" + id_komentar + '\'' +
                ", text='" + text + '\'' +
                ", autor=" + user +
                ", obrazek=" + obrazek +
                ", datum_vytvoreni=" + komentar_datum_vytvoreni +
                ", datum_editace=" + komentar_datum_editace +
                ", pocet_likes=" + komentar_pocet_likes +
                ", pocet_dislikes=" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Komentar komentar = (Komentar) o;

        if (id_komentar != null ? !id_komentar.equals(komentar.id_komentar) : komentar.id_komentar != null)
            return false;
        if (text != null ? !text.equals(komentar.text) : komentar.text != null) return false;
        if (komentar_datum_vytvoreni != null ? !komentar_datum_vytvoreni.equals(komentar.komentar_datum_vytvoreni) : komentar.komentar_datum_vytvoreni != null)
            return false;
        if (komentar_datum_editace != null ? !komentar_datum_editace.equals(komentar.komentar_datum_editace) : komentar.komentar_datum_editace != null)
            return false;
        if (komentar_pocet_likes != null ? !komentar_pocet_likes.equals(komentar.komentar_pocet_likes) : komentar.komentar_pocet_likes != null)
            return false;

        if (user != null ? !user.equals(komentar.user) : komentar.user != null) return false;
        return obrazek != null ? obrazek.equals(komentar.obrazek) : komentar.obrazek == null;

    }

    @Override
    public int hashCode() {
        int result = id_komentar != null ? id_komentar.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (komentar_datum_vytvoreni != null ? komentar_datum_vytvoreni.hashCode() : 0);
        result = 31 * result + (komentar_datum_editace != null ? komentar_datum_editace.hashCode() : 0);
        result = 31 * result + (komentar_pocet_likes != null ? komentar_pocet_likes.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (obrazek != null ? obrazek.hashCode() : 0);
        return result;
    }
}
