package data.model;

import java.time.LocalDate;
import java.util.Collection;

public interface OrdersDAO {
    public boolean insert(Order order);

    public boolean delete(Order order);

    public boolean update(Order order);

    public boolean saveOrUpdate(Order order);

    public Collection<Order> findByDate(LocalDate date);

    public Collection<Order> findByOfficiant(Officiant officiant);
}
