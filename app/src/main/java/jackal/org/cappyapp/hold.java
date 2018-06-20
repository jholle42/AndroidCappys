package jackal.org.cappyapp;

/**
 * Created by jholle42 on 6/15/18.
 */

public class hold {
    public String name;
    public String number;
    public String itemName;
    public String quantity;

    public hold(String name, String number, String itemName, String quantity) {
        this.name = name;
        this.number = number;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
