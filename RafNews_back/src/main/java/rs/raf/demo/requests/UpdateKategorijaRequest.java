package rs.raf.demo.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateKategorijaRequest {

    private Integer id;

    @NotNull(message = "Ime is required")
    @NotEmpty(message = "Ime is required")
    private String ime;

    @NotNull(message = "opis is required")
    @NotEmpty(message = "opis is required")
    private String opis;

    public UpdateKategorijaRequest() {
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
