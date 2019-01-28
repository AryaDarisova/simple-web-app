package data.storage;

import data.model.*;

public class sqlPerRequestDAOFactory extends DAOFactory{

    @Override
    public OrdersDAO getOrdersDAO() {
        return new OrdersDAOImpl();
    }

    @Override
    public OfficiantsDAO getOfficiantsDAO() {
        return new OfficiantsDAOImpl();
    }

    @Override
    public ItemsDAO getItemsDAO() {
        return new ItemsDAOImpl();
    }
}
