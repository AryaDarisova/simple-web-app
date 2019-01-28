package data.storage;

import data.model.ItemsDAO;
import data.model.OfficiantsDAO;
import data.model.OrdersDAO;

public abstract class DAOFactory {
    private static DAOFactory instance;

    protected DAOFactory() {

    }

    public static DAOFactory getDAOFactory(){
        if (instance == null) {
            instance = new sqlPerRequestDAOFactory();
        }
        return instance;
    }

    public abstract OrdersDAO getOrdersDAO ();
    public abstract OfficiantsDAO getOfficiantsDAO ();
    public abstract ItemsDAO getItemsDAO ();
}
