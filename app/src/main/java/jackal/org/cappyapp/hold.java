package jackal.org.cappyapp;

/**
 * Created by jholle42 on 6/15/18.
 */

public class hold {
    public String userName;
    public String name;
    public String number;
    public String itemName;
    public String quantity;
    public String message;
    public Integer approved;
    public String uid;

    //ADD TImestamp

    public hold(){

    }

    public hold( String name, String number, String itemName, String quantity, String message, Integer approved, String uid) {

        this.name = name;
        this.number = number;
        this.itemName = itemName;
        this.quantity = quantity;
        this.message = message;
        this.approved = approved;
        this.uid = uid;
    }

    public Integer getApproved() {
        return approved;
    }
    public String getUid(){return uid;}

    public String getUserName(){return userName;}
    public void setUserName(String userName){this.userName = userName;}

    public String getMessage(){ return message;}

    public void setMessage(String message){ this.message = message;}

    public void setApproved(Integer approved) { this.approved = approved;}

    public void setUid(String d){this.uid = d;}

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
