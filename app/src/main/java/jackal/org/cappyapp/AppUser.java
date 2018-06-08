package jackal.org.cappyapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jholle42 on 6/5/18.
 */

public class AppUser implements Parcelable{

    private String email;
    private String address;
    private String phoneNumber;
    private String fullname;

    public AppUser() {} // Empty Constructor required by Firebase

    public AppUser(String fullname, String email,String address,String phoneNumber) {
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    public AppUser(Parcel in) {
        email = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        fullname = in.readString();
    }

    public static final Creator<AppUser> CREATOR = new Creator<AppUser>() {
        @Override
        public AppUser createFromParcel(Parcel in) {
            return new AppUser(in);
        }

        @Override
        public AppUser[] newArray(int size) {
            return new AppUser[size];
        }
    };

    public void resetUser(){
        fullname = " ";
        email = " ";
        address = " ";
        phoneNumber = " ";
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


    public void setFullName(String in) { fullname = in;}

    public void setEmail(String in) {email = in;}

    public void setAddress(String in){
         address = in;
    }

    public void setPhoneNumber(String in) { phoneNumber = in;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(address);
        parcel.writeString(phoneNumber);
        parcel.writeString(fullname);
    }
}
