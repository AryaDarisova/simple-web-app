package data.model;

import java.rmi.RemoteException;

public class testDB {
    public static void main(String[] args) throws RemoteException {
        ItemsDAOImpl itemsDAO = new ItemsDAOImpl();
        Item greekSalad = new Item("greekSalad", "Греческий салат", 235, 7);
        //itemsDAO.delete(greekSalad);
        System.out.println(itemsDAO.insert(greekSalad));
    }
}
