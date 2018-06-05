package jackal.org.cappyapp;

import android.net.Uri;
import android.text.format.DateFormat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
;

/**
 * Created by jholle42 on 6/5/18.
 */

public class firebaseUser {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    //private DatabaseReference mInventoryDatabaseReference = mFirebaseDatabase.getReference();

    private String mCurrentUser, uid, date_time;
    private boolean accepted_terms = true;
    private static final String ANONYMOUS = " ";

    // constructor for the Firebase Interactor class:
    public firebaseUser() {

    }

    public void attachFirebaseAuthListener() {

    }

    public void detachFirebaseAuthListener() {
        // to do
    }

    public String getCurrentUser() {
        return mCurrentUser;
    }

    public  void userAcceptedTerms() {
        accepted_terms = true;
    }

    public void saveUserInformationToDatabase() {

    }

    private String getDateTime() {
        date_time = (DateFormat.format("MM-dd-yyyy hh:mm:ss", new java.util.Date()).toString());
        return date_time;
    }

}
