package rs.raf.demo.services;

import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.repositories.kategorije.KategorijaRepository;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KategorijeService {

    @Inject
    KategorijaRepository kategorijaRepository;

    public Map<String, Object> getKategorijePaginated(String currentPage, String pageLimit) {

        int current=Integer.parseInt(currentPage);
        int plimit=Integer.parseInt(pageLimit);
        int start = (current-1) * plimit;
        int end = start+plimit;
        Map<String,Object> response = new LinkedHashMap<>();

        List<Kategorija> kategorije = kategorijaRepository.getKategorije(start+1,end+1);
        Integer brKategorija = kategorijaRepository.getKategorijeBr();

        response.put("kategorije",kategorije);
        response.put("brojKategorija",brKategorija);

        return response;
    }

    public boolean deleteKategorijaById(Integer id) {
     return this.kategorijaRepository.deleteKategorijaById(id);
    }

    public Kategorija findKategorijaByName(String ime) {
        return this.kategorijaRepository.findKategorijaByName(ime);
    }

    public void insertKategorija(String ime, String opis) {
        this.kategorijaRepository.insertKategorija(ime,opis);
    }

    public Kategorija getKategorijaById(Integer id) {
        return this.kategorijaRepository.getKategorijaById(id);
    }

    public void updateKategorija(Integer id, String ime, String opis) {
        this.kategorijaRepository.updateKategorija(id,ime,opis);
    }

    public List<String> getKategorijeAll() {
        return this.kategorijaRepository.getKategorijeAll();
    }

    public Kategorija getKategorijaByName(String kategorija) {
        return this.kategorijaRepository.findKategorijaByName(kategorija);
    }
}
