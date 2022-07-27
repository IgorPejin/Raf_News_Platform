package rs.raf.demo.repositories.korisnici;

import rs.raf.demo.entities.Korisnik;

import java.util.List;

public interface KorisnikRepository {
     Korisnik findUser(String username, String passHash);
     List<Korisnik> getKorisnici(int start,int end);
     Integer getKorisniciBr();
     boolean findUserByEmail(String email);
     void insertKorisnik(String ime, String prezime, String email, String tip, String lozinka);

     boolean activateUserById(Integer id);

     Korisnik getKorisnikById(Integer id);

     void updateKorisnik(Integer id, String ime, String prezime, String email, String tip);
}
