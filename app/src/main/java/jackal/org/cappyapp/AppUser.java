package jackal.org.cappyapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jholle42 on 6/5/18.
 */

public class AppUser{

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String fullname;

    public AppUser() {} // Empty Constructor required by Firebase

    public AppUser(String firstName,String lastName,String email,String address,String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    public void resetUser(){
        firstName = null;
        fullname = null;
        lastName = null;
        email = null;
        address = null;
        phoneNumber = null;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getFullName() { return fullname;}

    public String getAddress(){
        return address;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }



    public void setFirstName(String in){
        firstName = in;
    }

    public void setLastName(String in){
        lastName = in;
    }

    public void setFullName(String in) { fullname = in;}

    public void setEmail(String in) {email = in;}

    public void setAddress(String in){
         address = in;
    }

}
