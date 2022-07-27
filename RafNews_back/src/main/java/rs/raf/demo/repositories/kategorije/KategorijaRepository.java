package rs.raf.demo.repositories.kategorije;

import rs.raf.demo.entities.Kategorija;

import java.util.List;

public interface KategorijaRepository {
    List<Kategorija> getKategorije(int i, int i1);
    Integer getKategorijeBr();
    boolean deleteKategorijaById(Integer id);

    Kategorija findKategorijaByName(String ime);

    void insertKategorija(String ime, String opis);

    Kategorija getKategorijaById(Integer id);

    void updateKategorija(Integer id, String ime, String opis);

    List<String> getKategorijeAll();
}
