package rs.raf.demo.repositories.korisnici;

import rs.raf.demo.entities.Korisnik;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlKorisniciRepository extends MySqlAbstractRepository implements KorisnikRepository {

    @Override
    public Korisnik findUser(String email, String passHash) {

        Korisnik korisnik = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik where email = ? and password = ?");
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,passHash);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                korisnik = new Korisnik(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),resultSet.getInt("tip"),resultSet.getInt("status"),resultSet.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return korisnik;
    }

    @Override
    public List<Korisnik> getKorisnici(int start ,int end) {
        List<Korisnik> korisnici = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik where id BETWEEN ? AND ? ");
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,end-1);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                korisnici.add(new Korisnik(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),resultSet.getInt("tip"),resultSet.getInt("status"),resultSet.getString("password")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return korisnici;
    }

    @Override
    public Integer getKorisniciBr() {
        List<Korisnik> korisnici = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik  ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                korisnici.add(new Korisnik(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),resultSet.getInt("tip"),resultSet.getInt("status"),resultSet.getString("password")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return korisnici.size();
    }

    @Override
    public boolean findUserByEmail(String email) {
        boolean found = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik where email = ? ");
            preparedStatement.setString(1,email);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                found=true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return found;
    }

    @Override
    public void insertKorisnik(String ime, String prezime, String email, String tip, String lozinka) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String[] generatedColumns = {"id"};

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO korisnik (email,ime,prezime,tip,status,password) values (?,?,?,?,?,?)",generatedColumns);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,ime);
            preparedStatement.setString(3,prezime);
            preparedStatement.setString(4,tip);
            preparedStatement.setString(5, String.valueOf(1)); // cim se unese korisnik odmah je aktivan
            preparedStatement.setString(6,lozinka);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public boolean activateUserById(Integer id) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik where id = ? ");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                if(resultSet.getInt("status")==1)
                {
                    preparedStatement = connection.prepareStatement("UPDATE korisnik SET status = ? WHERE id = ? ");
                    preparedStatement.setInt(1, 0);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                    success=true;
                }
                else
                {
                    preparedStatement = connection.prepareStatement("UPDATE korisnik SET status = ? WHERE id = ? ");
                    preparedStatement.setInt(1, 1);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                    success=true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return success;
    }

    @Override
    public Korisnik getKorisnikById(Integer id) {
        Korisnik korisnik = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik where id = ? ");
            preparedStatement.setString(1, String.valueOf(id));

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                korisnik = new Korisnik(resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("ime"),resultSet.getString("prezime"),resultSet.getInt("tip"),resultSet.getInt("status"),resultSet.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return korisnik;
    }

    @Override
    public void updateKorisnik(Integer id, String ime, String prezime, String email, String tip) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik where id = ? ");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                    preparedStatement = connection.prepareStatement("UPDATE korisnik SET ime = ? , prezime = ? ,email = ? , tip = ? WHERE id = ? ");
                    preparedStatement.setString(1, ime);
                    preparedStatement.setString(2, prezime);
                    preparedStatement.setString(3, email);
                    preparedStatement.setInt(4, Integer.parseInt(tip));
                    preparedStatement.setInt(5, id);
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
}
