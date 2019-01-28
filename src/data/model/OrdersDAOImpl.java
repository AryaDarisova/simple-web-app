package data.model;

import data.storage.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class OrdersDAOImpl implements  OrdersDAO {
    DataSource dataSource = DataSourceFactory.createDataSource();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    @Override
    public boolean insert(Order order) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            return statement.execute("INSERT INTO orders (orders.id, orders.date, orders.officiant_id) VALUES ('" + order.getId() + "', '" + Date.valueOf(order.getDate()) + "', '" + order.getOfficiant().getId() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Order order) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            return statement.execute("DELETE FROM orders WHERE orders.id = '" + order.getId() + "' AND orders.date = '" + Date.valueOf(order.getDate()) + "' AND orders.officiant_id ='" + order.getOfficiant().getId() +"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Order order) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            return statement.execute("UPDATE orders SET orders.date = '" + Date.valueOf(order.getDate()) + "', orders.officiant_id ='" + order.getOfficiant().getId() + "' WHERE orders.id ='" + order.getId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveOrUpdate(Order order) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM orders WHERE orders.id ='" + order.getId() + "'");
            if (resultSet.next()) {
                return update(order);
            }
            else {
                return insert(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<Order> findByDate(LocalDate date) {
        ArrayList<Order> arrayList = new ArrayList<>();
        Order order;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM orders WHERE orders.date ='" + date + "'");
            while (resultSet.next()) {
                order = new Order();
                OfficiantsDAOImpl officiantsDAOImpl = new OfficiantsDAOImpl();
                order.setId(Integer.parseInt(resultSet.getString("id")));
                order.setDate(date);
                order.setOfficiant(officiantsDAOImpl.findByID(Integer.parseInt(resultSet.getString("officiant_id"))));
                arrayList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public Collection<Order> findByOfficiant(Officiant officiant) {
        ArrayList<Order> arrayList = new ArrayList<>();
        Order order;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM orders WHERE orders.officiant_id ='" + officiant.getId() + "'");
            while (resultSet.next()) {
                order = new Order();
                order.setId(Integer.parseInt(resultSet.getString("id")));
                order.setDate(LocalDate.parse(resultSet.getString("date")));
                order.setOfficiant(officiant);
                arrayList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
