package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Vest {

    private Integer id;
    @NotNull(message = "naslov field is required")
    @NotEmpty(message = "naslov field is required")
    private String naslov;
    @NotNull(message = "tekst field is required")
    @NotEmpty(message = "tekst field is required")
    private String tekst;
    @NotNull(message = "vreme field is required")
    @NotEmpty(message = "vreme field is required")
    private String vreme_kreiranja;
    @NotNull(message = "poseta field is required")
    @NotEmpty(message = "poseta field is required")
    private int broj_poseta;

    @NotNull(message = "autor field is required")
    @NotEmpty(message = "autor field is required")
    private String autor;

    @NotNull(message = "kategorija field is required")
    @NotEmpty(message = "kategorija field is required")
    private String kategorija;

    private Integer fk_kategorija;
    private Integer fk_korisnik;

    public Vest() {
    }

    public Vest(Integer id, String naslov, String tekst, String vreme_kreiranja, int broj_poseta, String autor, Integer fk_kategorija, Integer fk_korisnik) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.vreme_kreiranja = vreme_kreiranja;
        this.broj_poseta = broj_poseta;
        this.autor = autor;
        this.fk_kategorija = fk_kategorija;
        this.fk_korisnik = fk_korisnik;
    }

    public Vest(Integer id, String naslov, String tekst, String vreme_kreiranja, int broj_poseta, String autor, String kategorija, Integer fk_kategorija, Integer fk_korisnik) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.vreme_kreiranja = vreme_kreiranja;
        this.broj_poseta = broj_poseta;
        this.autor = autor;
        this.kategorija = kategorija;
        this.fk_kategorija = fk_kategorija;
        this.fk_korisnik = fk_korisnik;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public Integer getId() {
        return id;
    }



    public Integer getFk_kategorija() {
        return fk_kategorija;
    }

    public void setFk_kategorija(Integer fk_kategorija) {
        this.fk_kategorija = fk_kategorija;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public void setVreme_kreiranja(String vreme_kreiranja) {
        this.vreme_kreiranja = vreme_kreiranja;
    }

    public String getVreme_kreiranja() {
        return vreme_kreiranja;
    }

    public int getBroj_poseta() {
        return broj_poseta;
    }

    public void setBroj_poseta(int broj_poseta) {
        this.broj_poseta = broj_poseta;
    }
}
