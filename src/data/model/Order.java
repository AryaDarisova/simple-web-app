package data.model;

import data.model.Item;
import data.model.Officiant;

import java.time.LocalDate;
import java.util.Map;

public class Order {
    private int id;
    private LocalDate date;
    private Officiant officiant;
    private Map<Item, Integer> items;

    public Order() {

    }

    public Order(Officiant officiant, LocalDate date, Map<Item, Integer> items) {
        this.officiant = officiant;
        this.date = date;
        this.items = items;
    }

    protected Order(Officiant officiant, LocalDate date, Map<Item, Integer> items, int id) {
        this.officiant = officiant;
        this.date = date;
        this.items = items;
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Officiant getOfficiant() {
        return officiant;
    }

    public void setOfficiant(Officiant officiant) {
        this.officiant = officiant;
    }

    /*если такой item уже есть, то
    увеличивается количество на 1, если нет – добавляется*/

    public void addItem(Item item) {
        addItem(item, 1);
    }

    /*если такой item уже есть, то увеличивается количество на quantity, если нет –
    добавляется*/

    public void addItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            items.compute(item, (key, value) -> value + quantity);
        }
        else {
            items.put(item, quantity);
        }
    }

    /*если такой item уже есть, то изменяется количество, если нет –
    добавляются item-ы в указанном количестве*/

    public void setQuantity (Item item, int quantity) {
        if (items.containsKey(item)) {
            items.compute(item, (key, value) -> quantity);
        }
        else {
            items.put(item, quantity);
        }

    }

    public void remove (Item item) {
        items.remove(item);
    }

    public Map<Item, Integer> getItemsMap() {
        return items;
    }

    protected void setId() {

    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
