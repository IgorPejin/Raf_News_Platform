package rs.raf.demo.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateRequest {

    private Integer id;

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

    public UpdateRequest() {
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
