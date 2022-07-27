package rs.raf.demo.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FormRequest {

    private Integer id;

    @NotNull(message = "Ime is required")
    @NotEmpty(message = "Ime is required")
    private String ime;

    @NotNull(message = "teks is required")
    @NotEmpty(message = "tekst is required")
    private String tekst;

    public FormRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
}
