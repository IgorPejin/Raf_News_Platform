package rs.raf.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.Korisnik;
import rs.raf.demo.repositories.korisnici.KorisnikRepository;

import javax.inject.Inject;
import java.util.*;

public class KorisniciService {

    @Inject
    KorisnikRepository korisnikRepository;

    public String login(String email, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        Korisnik korisnik = this.korisnikRepository.findUser(email,hashedPassword);
        if (korisnik == null || !korisnik.getPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("id",korisnik.getId())
                .withClaim("ime",korisnik.getIme())
                .withClaim("prezime",korisnik.getPrezime())
                .withClaim("password",hashedPassword)
                .withClaim("tip", korisnik.getTip())
                .withClaim("status", korisnik.getStatus())
                .sign(algorithm);
    }

    public Map<String,Object> getKorisniciPaginated(String currentPage, String pageLimit)
    {
        int current=Integer.parseInt(currentPage);
        int plimit=Integer.parseInt(pageLimit);
        int start = (current-1) * plimit;
        int end = start+plimit;
        Map<String,Object> response = new LinkedHashMap<>();

        List<Korisnik> korisnici = korisnikRepository.getKorisnici(start+1,end+1);
        Integer brKorisnika = korisnikRepository.getKorisniciBr();

        response.put("korisnici",korisnici);
        response.put("brojKorisnika",brKorisnika);

        return response;

//        if(end >= korisnici.size() )
//        {
//            int ostatak = korisnici.size()-start;
//            List<Korisnik> subKorisniciEnd=korisnici.subList(start,start+ostatak);
//            return subKorisniciEnd;
//        }
//        else
//        {
//            List<Korisnik> subKorisnici=korisnici.subList(start,end);
//            return subKorisnici;
//        }
    }

//    public Integer getKorisniciSize()
//    {
//        List<Korisnik> korisnici = korisnikRepository.getKorisnici();
//        return korisnici.size();
//    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject();
        String password = jwt.getClaim("password").asString();

        Korisnik korisnik = this.korisnikRepository.findUser(username,password);

        if (korisnik == null){
            return false;
        }
        return true;
    }

    public boolean findUserByEmail(String email) {
        boolean found = this.korisnikRepository.findUserByEmail(email);
        return found;
    }

    public void insertKorisnik(String ime, String prezime, String email, String tip, String lozinka) {
        this.korisnikRepository.insertKorisnik(ime,prezime,email,tip,lozinka);
    }

    public boolean activateUserById(Integer id) {
        return this.korisnikRepository.activateUserById(id);
    }

    public Korisnik getKorisnikById(Integer id) {
        return this.korisnikRepository.getKorisnikById(id);
    }

    public void updateKorisnik(Integer id, String ime, String prezime, String email, String tip) {
        this.korisnikRepository.updateKorisnik(id,ime,prezime,email,tip);
    }
}
