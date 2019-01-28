package data.model;

public class Item {
    private int id;
    private String name;
    private String description;
    private double cost;

    public Item() {

    }

    public Item(String name, String description, double cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    protected Item(String name, String description, double cost, int id) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
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