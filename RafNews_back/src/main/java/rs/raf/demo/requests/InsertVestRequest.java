package rs.raf.demo.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InsertVestRequest {

    @NotNull(message = "naslov is required")
    @NotEmpty(message = "naslov is required")
    private String naslov;

    @NotNull(message = "tekst is required")
    @NotEmpty(message = "tekst is required")
    private String tekst;

    @NotNull(message = "autor is required")
    @NotEmpty(message = "autor is required")
    private String autor;

    @NotNull(message = "tag is required")
    @NotEmpty(message = "tag is required")
    private String tag;

    @NotNull(message = "kategorija is required")
    @NotEmpty(message = "kategorija is required")
    private String kategorija;

    public InsertVestRequest() {
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }
}
