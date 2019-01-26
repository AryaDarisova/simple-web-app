package data.model;

import data.storage.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class OfficiantsDAOImpl implements  OfficiantsDAO {
    DataSource dataSource = DataSourceFactory.createDataSource();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    @Override
    public boolean insert(Officiant officiant) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            return statement.execute("INSERT INTO officiants (officiants.id, officiants.first_name, officiants.second_name) VALUES ('" + officiant.getId() + "', '" + officiant.getFirstName() + "', '" + officiant.getSecondName() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Officiant officiant) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            return statement.execute("DELETE FROM officiants WHERE officiants.id = '" + officiant.getId() + "' AND officiants.first_name = '" + officiant.getFirstName() + "' AND officiants.second_name ='" + officiant.getSecondName() +"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Officiant officiant) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            return statement.execute("UPDATE officiants SET officiants.first_name = '" + officiant.getFirstName() + "', officiants.second_name ='" + officiant.getSecondName() + "' WHERE officiants.id ='" + officiant.getId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveOrUpdate(Officiant officiant) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM officiants WHERE officiant.id ='" + officiant.getId() + "'");
            if (resultSet.next()) {
                return update(officiant);
            }
            else {
                return insert(officiant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Officiant findByID(int id) {
        Officiant officiant = new Officiant();
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM officiants WHERE officiants.id ='" + id + "'");
            resultSet.next();
            officiant.setId(id);
            officiant.setFirstName(resultSet.getString("first_name"));
            officiant.setSecondName(resultSet.getString("second_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return officiant;
    }

    @Override
    public Collection<Officiant> findByName(String firstName, String secondName) {
        ArrayList<Officiant> arrayList = new ArrayList<>();
        Officiant officiant;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM officiants WHERE officiants.first_name='" + firstName + "' AND officiants.second_name = '" + secondName + "'");
            while (resultSet.next()) {
                officiant = new Officiant();
                officiant.setId(Integer.parseInt(resultSet.getString("id")));
                officiant.setFirstName(firstName);
                officiant.setSecondName(secondName);
                arrayList.add(officiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
