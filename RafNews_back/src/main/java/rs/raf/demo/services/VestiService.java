package rs.raf.demo.services;

import rs.raf.demo.entities.Komentar;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.entities.Vest;
import rs.raf.demo.repositories.vesti.VestiRepository;

import javax.inject.Inject;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VestiService {

    @Inject
    VestiRepository vestiRepository;

    public Map<String, Object> getVestiPaginated(String currentPage, String pageLimit) {
        int current=Integer.parseInt(currentPage);
        int plimit=Integer.parseInt(pageLimit);
        int start = (current-1) * plimit;
        int end = start+plimit;
        Map<String,Object> response = new LinkedHashMap<>();

        List<Vest> vesti = vestiRepository.getVesti(start+1,end+1);
        Integer brVesti = vestiRepository.getVestiBr();

        response.put("vesti",vesti);
        response.put("brojVesti",brVesti);

        return response;
    }

    public Tag insertTag(String tag) {
      return this.vestiRepository.insertTag(tag);
    }

    public Vest insertVest(String naslov, String tekst, Date date, int brojPoseta, String autor,String kategorija, Integer fk_kategorija, Integer fk_korisnik) {
        return this.vestiRepository.insertVest(naslov,tekst,date,brojPoseta,autor,kategorija,fk_kategorija,fk_korisnik);
    }

    public void insert_Vest_Tag(Integer vestId, Integer tagId) {
        this.vestiRepository.insert_Vest_Tag(vestId,tagId);
    }

    public Vest getVestById(Integer id) {
        return this.vestiRepository.getVestById(id);
    }

    public void updateVest(Integer id, String naslov, String tekst, Integer kategorijaId, String tag) {

        this.vestiRepository.updateVest(id,naslov,tekst,kategorijaId,tag);
    }

    public boolean deleteVestById(Integer id) {
       return  this.vestiRepository.deleteVestById(id);
    }

    public Map<String, Object> getVestiPaginatedByKategorija(String currentPage, String pageLimit, Integer kategorijaId) {
        int current=Integer.parseInt(currentPage);
        int plimit=Integer.parseInt(pageLimit);
        int start = (current-1) * plimit;
        int end = start+plimit;
        Map<String,Object> response = new LinkedHashMap<>();

        List<Vest> vesti = vestiRepository.getVestibyKategorija(start+1,end+1,kategorijaId);
        Integer brVesti = vestiRepository.getVestiBrKategorija(kategorijaId);

        response.put("vesti",vesti);
        response.put("brojVesti",brVesti);

        return response;
    }

    public Map<String, Object> getVestiPaginatedByQuery(String currentPage, String pageLimit, String query) {
        int current=Integer.parseInt(currentPage);
        int plimit=Integer.parseInt(pageLimit);
        int start = (current-1) * plimit;
        int end = start+plimit;
        Map<String,Object> response = new LinkedHashMap<>();

        List<Vest> vesti = vestiRepository.getVestibyQuery(start+1,end+1,query);
        Integer brVesti = vestiRepository.getVestiBrQuery(query);

        response.put("vesti",vesti);
        response.put("brojVesti",brVesti);

        return response;
    }

    public Map<String, Object> getVestiPaginatedHomePage(String currentPage, String pageLimit) {
        int current=Integer.parseInt(currentPage);
        int plimit=Integer.parseInt(pageLimit);
        int start = (current-1) * plimit;
        int end = start+plimit;
        Map<String,Object> response = new LinkedHashMap<>();

        List<Vest> vesti = vestiRepository.getVestiHome(start+1,end+1);
        response.put("vesti",vesti);

        return response;
    }

    public String getTag(Integer vestId) {
        return this.vestiRepository.getTag(vestId);
    }

    public List<Komentar> getCommentsById(Integer vestId) {
        return this.vestiRepository.getCommentsById(vestId);
    }

    public void insertComment(Integer id, String ime, String tekst, Date date) {
        this.vestiRepository.insertComment(id,ime,tekst,date);
    }

    public Map<String, Object> getCommentsPaginated(String currentPage, String pageLimit, Integer idVesti) {
        int current=Integer.parseInt(currentPage);
        int plimit=Integer.parseInt(pageLimit);
        int start = (current-1) * plimit;
        int end = start+plimit;
        Map<String,Object> response = new LinkedHashMap<>();

        List<Komentar> komentari = vestiRepository.getCommentsPaginated(start+1,end+1,idVesti);
        Integer brKomentara = vestiRepository.getCommentsPaginatedBr(idVesti);

        response.put("komentari",komentari);
        response.put("brojKomentara",brKomentara);

        return response;
    }
}
