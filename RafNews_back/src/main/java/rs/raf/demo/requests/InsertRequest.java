package rs.raf.demo.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InsertRequest {

    @NotNull(message = "Ime is required")
    @NotEmpty(message = "Ime is required")
    private String ime;

    @NotNull(message = "prezime is required")
    @NotEmpty(message = "prezime is required")
    private String prezime;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "tip is required")
    @NotEmpty(message = "tip is required")
    private String tip;

    @NotNull(message = "lozinka is required")
    @NotEmpty(message = "lozinka is required")
    private String lozinka;

    @NotNull(message = "confirm is required")
    @NotEmpty(message = "confirm is required")
    private String confirmLozinka;


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getConfirmLozinka() {
        return confirmLozinka;
    }

    public void setConfirmLozinka(String confirmLozinka) {
        this.confirmLozinka = confirmLozinka;
    }
}
