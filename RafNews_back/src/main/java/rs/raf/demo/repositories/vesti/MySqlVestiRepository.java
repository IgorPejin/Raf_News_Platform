package rs.raf.demo.repositories.vesti;

import rs.raf.demo.entities.*;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlVestiRepository extends MySqlAbstractRepository implements VestiRepository {
    @Override
    public List<Vest> getVesti(int start, int end) {
        List<Vest> vesti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        System.out.println(start);
        System.out.println(end);

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM vest WHERE id BETWEEN ? AND ? ORDER BY vreme_kreiranja DESC ");
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,end-1);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"),resultSet.getString("naslov"),resultSet.getString("tekst"),resultSet.getDate("vreme_kreiranja").toString(),resultSet.getInt("broj_poseta"),resultSet.getString("autor"),resultSet.getInt("fk_kategorija"),resultSet.getInt("fk_korisnik")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vesti;
    }

    @Override
    public Integer getVestiBr() {
        List<Vest> vesti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM vest  ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"),resultSet.getString("naslov"),resultSet.getString("tekst"),resultSet.getDate("vreme_kreiranja").toString(),resultSet.getInt("broj_poseta"),resultSet.getString("autor"),resultSet.getInt("fk_kategorija"),resultSet.getInt("fk_korisnik")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vesti.size();
    }

    @Override
    public Tag insertTag(String tag) {

        Tag tagCreated = new Tag();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String[] generatedColumns = {"id"};
        ResultSet resultSet=null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO tag (rec) values (?)",generatedColumns);
            preparedStatement.setString(1,tag);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                tagCreated.setId(resultSet.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return tagCreated;
    }

    @Override
    public Vest insertVest(String naslov, String tekst, Date date, int brojPoseta, String autor,String kategorija, Integer fk_kategorija, Integer fk_korisnik) {
        Vest vest = new Vest();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String[] generatedColumns = {"id"};
        ResultSet resultSet=null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO vest (naslov,tekst,vreme_kreiranja,broj_poseta,autor,kategorija,fk_kategorija,fk_korisnik) values (?,?,?,?,?,?,?,?)",generatedColumns);
            preparedStatement.setString(1,naslov);
            preparedStatement.setString(2,tekst);
            preparedStatement.setDate(3,date);
            preparedStatement.setInt(4,brojPoseta);
            preparedStatement.setString(5, autor);
            preparedStatement.setString(6, kategorija);
            preparedStatement.setInt(7,fk_kategorija);
            preparedStatement.setInt(8,fk_korisnik);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                vest.setId(resultSet.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return vest;
    }

    @Override
    public void insert_Vest_Tag(Integer vestId, Integer tagId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO vest_tag (tagid,vestid) values (?,?)");
            preparedStatement.setInt(1,tagId);
            preparedStatement.setInt(2,vestId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public Vest getVestById(Integer id) {
        Vest vest = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM vest where id = ? ");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vest = new Vest(resultSet.getInt("id"),resultSet.getString("naslov"),resultSet.getString("tekst"),resultSet.getDate("vreme_kreiranja").toString(),resultSet.getInt("broj_poseta"),resultSet.getString("autor"),resultSet.getInt("fk_kategorija"),resultSet.getInt("fk_korisnik"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vest;
    }

    @Override
    public void updateVest(Integer id, String naslov, String tekst, Integer kategorijaId, String tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM vest where id = ? ");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                preparedStatement = connection.prepareStatement("UPDATE vest SET naslov = ? , tekst = ? , fk_kategorija=? WHERE id = ? ");
                preparedStatement.setString(1, naslov);
                preparedStatement.setString(2, tekst);
                preparedStatement.setInt(3, kategorijaId);
                preparedStatement.setInt(4,id);
                preparedStatement.executeUpdate();

                try {
                    preparedStatement = connection.prepareStatement("SELECT * FROM vest_tag WHERE vestid = ?");
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeQuery();

                    resultSet =preparedStatement.getResultSet();
                    Vest_Tag vest_tag = new Vest_Tag();

                    if (resultSet.next()) {
                        vest_tag.setTagid(resultSet.getInt("tagid"));
                    }
                    preparedStatement = connection.prepareStatement("UPDATE tag SET rec = ? WHERE id= ?");
                    preparedStatement.setString(1, tag);
                    preparedStatement.setInt(2, vest_tag.getTagid());
                    preparedStatement.executeUpdate();
                }catch (Exception e)
                {
                    System.out.println("Ne postoji tag za ovu vest! Update samo nad postojecim podacima.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteVestById(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM vest WHERE id = ? ");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return true;
    }

    @Override
    public List<Vest> getVestibyKategorija(int start, int end, Integer kategorijaId) {
        List<Vest> vesti = new ArrayList<>();
        List<Vest> vestiKat = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(" SELECT * FROM vest WHERE fk_kategorija = ?  ");
            preparedStatement.setInt(1,kategorijaId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                    vesti.add(new Vest(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("tekst"), resultSet.getDate("vreme_kreiranja").toString(), resultSet.getInt("broj_poseta"), resultSet.getString("autor"), resultSet.getInt("fk_kategorija"), resultSet.getInt("fk_korisnik")));
            }

            for(int i=1;i<=vesti.size();i++)
            {
                if(i>=start && i<=end-1)
                {
                    vestiKat.add(vesti.get(i-1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vestiKat;
    }

    @Override
    public Integer getVestiBrKategorija(Integer kategorijaId) {
        List<Vest> vesti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM vest where fk_kategorija = ? ");
            preparedStatement.setInt(1,kategorijaId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"),resultSet.getString("naslov"),resultSet.getString("tekst"),resultSet.getDate("vreme_kreiranja").toString(),resultSet.getInt("broj_poseta"),resultSet.getString("autor"),resultSet.getInt("fk_kategorija"),resultSet.getInt("fk_korisnik")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vesti.size();
    }

    @Override
    public List<Vest> getVestibyQuery(int start, int end, String query) {
        List<Vest> vesti = new ArrayList<>();
        List<Vest> vestiQuery = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(" SELECT * FROM vest WHERE naslov LIKE ? OR tekst LIKE ?   ");
            preparedStatement.setString(1,'%'+query+'%');
            preparedStatement.setString(2,'%'+query+'%');
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("tekst"), resultSet.getDate("vreme_kreiranja").toString(), resultSet.getInt("broj_poseta"), resultSet.getString("autor"), resultSet.getInt("fk_kategorija"), resultSet.getInt("fk_korisnik")));
            }

            for(int i=1;i<=vesti.size();i++)
            {
                if(i>=start && i<=end-1)
                {
                    vestiQuery.add(vesti.get(i-1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vestiQuery;
    }

    @Override
    public Integer getVestiBrQuery(String query) {
        List<Vest> vesti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM vest WHERE naslov LIKE  ? OR tekst LIKE ? ");
            preparedStatement.setString(1,'%'+query+'%');
            preparedStatement.setString(2,'%'+query+'%');
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"),resultSet.getString("naslov"),resultSet.getString("tekst"),resultSet.getDate("vreme_kreiranja").toString(),resultSet.getInt("broj_poseta"),resultSet.getString("autor"),resultSet.getInt("fk_kategorija"),resultSet.getInt("fk_korisnik")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vesti.size();
    }

    @Override
    public List<Vest> getVestiHome(int start, int end) {
        List<Vest> vesti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM vest WHERE id BETWEEN ? AND ? ORDER BY vreme_kreiranja DESC ");
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,end-1);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vesti.add(new Vest(resultSet.getInt("id"),resultSet.getString("naslov"),resultSet.getString("tekst"),resultSet.getDate("vreme_kreiranja").toString(),resultSet.getInt("broj_poseta"),resultSet.getString("autor"),resultSet.getString("kategorija"),resultSet.getInt("fk_kategorija"),resultSet.getInt("fk_korisnik")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vesti;
    }

    @Override
    public String getTag(Integer vestId) {
        String tag = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM vest_tag where vestid = ? ");
            preparedStatement.setInt(1,vestId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                preparedStatement = connection.prepareStatement("SELECT * FROM tag  where id = ? ");
                preparedStatement.setInt(1,resultSet.getInt("tagid"));
                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()) {
                    tag = resultSet.getString("rec");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tag;
    }

    @Override
    public List<Komentar> getCommentsById(Integer vestId) {
        List<Komentar> komentari = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM komentar WHERE fk_vestkomentar = ?  ");
            preparedStatement.setInt(1,vestId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                komentari.add(new Komentar(resultSet.getInt("id"),resultSet.getString("autor"),resultSet.getString("tekst"),resultSet.getDate("datum_kreiranja").toString(),resultSet.getInt("fk_vestkomentar")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return komentari;
    }

    @Override
    public void insertComment(Integer id, String ime, String tekst, Date date) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String[] generatedColumns = {"id"};
        ResultSet resultSet=null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO komentar (autor,tekst,datum_kreiranja,fk_vestkomentar) values (?,?,?,?)",generatedColumns);
            preparedStatement.setString(1,ime);
            preparedStatement.setString(2,tekst);
            preparedStatement.setDate(3,date);
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<Komentar> getCommentsPaginated(int start, int end, Integer idVesti) {
        List<Komentar> komentari = new ArrayList<>();
        List<Komentar> komentariVest = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(" SELECT * FROM komentari WHERE fk_vestkategorija = ?  ");
            preparedStatement.setInt(1,idVesti);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                komentari.add(new Komentar(resultSet.getInt("id"), resultSet.getString("autor"), resultSet.getString("tekst"), resultSet.getDate("vreme_kreiranja").toString(), resultSet.getInt("fk_vestkomentar")));
            }

            for(int i=1;i<=komentari.size();i++)
            {
                if(i>=start && i<=end-1)
                {
                    komentariVest.add(komentari.get(i-1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return komentariVest;
    }

    @Override
    public Integer getCommentsPaginatedBr(Integer idVesti) {
        int br=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM komentari where fk_vestkomentar = ? ");
            preparedStatement.setInt(1,idVesti);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                br++;
             }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return br;
    }
}
