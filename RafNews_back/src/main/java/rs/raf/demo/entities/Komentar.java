package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Komentar {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String autor;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String tekst;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String datum_kreiranja;

    private Integer fk_vestkomentar;

    public Komentar() {
    }

    public Komentar(Integer id, String autor, String tekst, String datum_kreiranja,Integer fk_vestkomentar) {
        this.id = id;
        this.autor = autor;
        this.tekst = tekst;
        this.datum_kreiranja = datum_kreiranja;
        this.fk_vestkomentar=fk_vestkomentar;
    }

    public Integer getFk_vestkomentar() {
        return fk_vestkomentar;
    }

    public void setFk_vestkomentar(Integer fk_vestkomentar) {
        this.fk_vestkomentar = fk_vestkomentar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getDatum_kreiranja() {
        return datum_kreiranja;
    }

    public void setDatum_kreiranja(String datum_kreiranja) {
        this.datum_kreiranja = datum_kreiranja;
    }
}
