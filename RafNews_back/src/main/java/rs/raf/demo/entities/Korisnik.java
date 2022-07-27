package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Korisnik {

    private Integer id;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String email;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String ime;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String prezime;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private int tip;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private int status;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String password;

    public Korisnik() {
    }

    public Korisnik(Integer id, String email, String ime, String prezime, int tip, int status, String password) {
        this();
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.tip = tip;
        this.status = status;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  id + email + ime + prezime + tip + status + password;
    }
}
