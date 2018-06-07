package jackal.org.cappyapp;

import android.net.Uri;
import android.text.format.DateFormat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
;

/**
 * Created by jholle42 on 6/5/18.
 */

public class firebaseUser {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private AppUser user;

    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mInventoryDatabaseReference = mFirebaseDatabase.getReference();

    // constructor for the Firebase Interactor class:
    public firebaseUser() {

    }

    public void setUser(AppUser user){
        this.user = user;
    }

    public void attachFirebaseAuthListener() {

    }

    public void detachFirebaseAuthListener() {
        // to do
    }

    public AppUser getCurrentUser() {
        return user;
    }

    public void saveUserInformationToDatabase() {

    }

}
