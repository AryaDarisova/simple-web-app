package data.model;

import data.storage.DataSourceFactory;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class ItemsDAOImpl implements ItemsDAO {
    DataSource dataSource = DataSourceFactory.createDataSource();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    @Override
    public boolean insert(Item item) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            return statement.execute("INSERT INTO items (items.id, items.name, items.description, items.cost) VALUES ('" + item.getId() + "', '" + item.getName() + "', '" + item.getDescription() + "', '" + item.getCost() +"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            return statement.execute("DELETE FROM items WHERE items.id = '" + item.getId() + "' AND items.name = '" + item.getName() + "' AND items.description ='" + item.getDescription() + "' AND items.cost ='" + item.getCost() +"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Item item) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            return statement.execute("UPDATE items SET items.name = '" + item.getName() + "', items.description ='" + item.getDescription() + "', items.cost ='" + item.getCost() +"' WHERE items.id ='" + item.getId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveOrUpdate(Item item) {
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM items WHERE items.id ='" + item.getId() + "'");
            if (resultSet.next()) {
                return update(item);
            }
            else {
                return insert(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Item findByID(int id) {
        Item item = new Item();
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM items WHERE items.id ='" + id + "'");
            resultSet.next();
            item.setId(id);
            item.setName(resultSet.getString("name"));
            item.setDescription(resultSet.getString("description"));
            item.setCost(Double.parseDouble(resultSet.getString("cost")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Collection<Item> findByName(String name) {
        ArrayList<Item> arrayList = new ArrayList<>();
        Item item;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM items WHERE items.name ='" + name + "'");
            while (resultSet.next()) {
                item = new Item();
                item.setId(Integer.parseInt(resultSet.getString("id")));
                item.setName(name);
                item.setDescription(resultSet.getString("description"));
                item.setCost(Double.parseDouble(resultSet.getString("cost")));
                arrayList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public Collection<Item> findByCost(Double cost) {
        ArrayList<Item> arrayList = new ArrayList<>();
        Item item;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM items WHERE items.cost ='" + cost + "'");
            while (resultSet.next()) {
                item = new Item();
                item.setId(Integer.parseInt(resultSet.getString("id")));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setCost(cost);
                arrayList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
