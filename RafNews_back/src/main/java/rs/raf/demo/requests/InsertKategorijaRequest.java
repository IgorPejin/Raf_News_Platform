package rs.raf.demo.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InsertKategorijaRequest {

    @NotNull(message = "Ime is required")
    @NotEmpty(message = "Ime is required")
    private String ime;

    @NotNull(message = "prezime is required")
    @NotEmpty(message = "prezime is required")
    private String opis;

    public InsertKategorijaRequest() {
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
