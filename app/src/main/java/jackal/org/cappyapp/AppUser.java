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

    public AppUser() {} // Empty Constructor required by Firebase

    public AppUser(String firstName,String lastName,String email,String address,String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
}
