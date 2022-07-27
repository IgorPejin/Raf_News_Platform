package rs.raf.demo.repositories.kategorije;

import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlKategorijaRepository extends MySqlAbstractRepository implements KategorijaRepository {

    @Override
    public List<Kategorija> getKategorije(int start, int end) {
        List<Kategorija> kategorije = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorija where id BETWEEN ? AND ? ");
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,end-1);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                kategorije.add(new Kategorija(resultSet.getInt("id"),resultSet.getString("ime"),resultSet.getString("opis")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return kategorije;
    }

    @Override
    public Integer getKategorijeBr() {
        List<Kategorija> kategorije = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorija ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                kategorije.add(new Kategorija(resultSet.getInt("id"),resultSet.getString("ime"),resultSet.getString("opis")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return kategorije.size();
    }

    @Override
    public boolean deleteKategorijaById(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM kategorija WHERE id = ? ");
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
    public Kategorija findKategorijaByName(String ime) {
        Kategorija kategorija = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorija WHERE ime = ? ");
            preparedStatement.setString(1,ime);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                kategorija = new Kategorija(resultSet.getInt("id"),resultSet.getString("ime"),resultSet.getString("opis"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return kategorija;
    }

    @Override
    public void insertKategorija(String ime, String opis) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String[] generatedColumns = {"id"};

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO kategorija (ime,opis) values (?,?)",generatedColumns);
            preparedStatement.setString(1,ime);
            preparedStatement.setString(2,opis);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public Kategorija getKategorijaById(Integer id) {
        Kategorija kategorija = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorija where id = ? ");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                kategorija = new Kategorija(resultSet.getInt("id"),resultSet.getString("ime"),resultSet.getString("opis"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return kategorija;
    }

    @Override
    public void updateKategorija(Integer id, String ime, String opis) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorija where id = ? ");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                preparedStatement = connection.prepareStatement("UPDATE kategorija SET ime = ? , opis = ? WHERE id = ? ");
                preparedStatement.setString(1, ime);
                preparedStatement.setString(2, opis);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
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
    public List<String> getKategorijeAll() {
        List<String> kategorije = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM kategorija ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                kategorije.add(resultSet.getString("ime"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return kategorije;
    }
}
