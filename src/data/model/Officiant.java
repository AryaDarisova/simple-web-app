package data.model;

import java.util.Set;

public class Officiant {
    private int id;
    private String firstName;
    private String secondName;
    private Set<Order> orders;

    public Officiant() {

    }

    public Officiant(String firstName, String secondName, Set<Order> orders) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.orders = orders;
    }

    protected Officiant(String firstName, String secondName, Set<Order> orders, int id) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.orders = orders;
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void add(Order order) {
        orders.add(order);
    }

    public Order remove(Order order) {
        return orders.remove(order) ? order : null;
    }

    public boolean contains(Order order) {
        return orders.contains(orders);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setId() {

    }

    public int getId() {
        return id;
    }
}
