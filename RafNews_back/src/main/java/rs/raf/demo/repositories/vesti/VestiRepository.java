package rs.raf.demo.repositories.vesti;

import rs.raf.demo.entities.Komentar;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.entities.Vest;

import java.sql.Date;
import java.util.List;

public interface VestiRepository {
    List<Vest> getVesti(int i, int i1);

    Integer getVestiBr();

    Tag insertTag(String tag);

    Vest insertVest(String naslov, String tekst, Date date, int brojPoseta, String autor,String kategorija, Integer fk_kategorija, Integer fk_korisnik);

    void insert_Vest_Tag(Integer vestId, Integer tagId);

    Vest getVestById(Integer id);

    void updateVest(Integer id, String naslov, String tekst, Integer kategorijaId, String tag);

    boolean deleteVestById(Integer id);

    List<Vest> getVestibyKategorija(int start, int end, Integer kategorijaId);

    Integer getVestiBrKategorija(Integer kategorijaId);

    List<Vest> getVestibyQuery(int i, int i1, String query);

    Integer getVestiBrQuery(String query);

    List<Vest> getVestiHome(int i, int i1);

    String getTag(Integer vestId);

    List<Komentar> getCommentsById(Integer vestId);

    void insertComment(Integer id, String ime, String tekst, Date date);

    List<Komentar> getCommentsPaginated(int i, int i1, Integer idVesti);

    Integer getCommentsPaginatedBr(Integer idVesti);
}
