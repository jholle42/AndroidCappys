package jackal.org.cappyapp;

import com.google.firebase.database.PropertyName;

/**
 * Created by jholle42 on 6/15/18.
 */

public class User{

    User (String e,String a, String p, String f){
        this.address = a;
        this.email = e;
        this.fullname = f;
        this.phoneNumber = p;
    }

    public String email;
    public String address;
    public String phoneNumber;
    public String fullname;

    @PropertyName("email")
    public String getEmail(){
        return email;
    }
    @PropertyName("address")
    public String getAddress(){
        return address;
    }
    @PropertyName("phone_number")
    public String getPhoneNumber(){
        return phoneNumber;
    }
    @PropertyName("fullname")
    public String getFullname(){
        return fullname;
    }
    @PropertyName("fullname")
    public void setFullname(String f){
        fullname = f;
    }
    @PropertyName("email")
    public void setEmail(String e){
        email = e;
    }
    @PropertyName("address")
    public void setAddress(String a){
        address = a;
    }
    @PropertyName("phone_number")
    public void setPhoneNumber(String p){
        phoneNumber = p;
    }

}
// ...
