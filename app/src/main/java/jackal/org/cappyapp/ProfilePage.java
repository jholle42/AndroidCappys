package jackal.org.cappyapp;

import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.PropertyName;

import org.w3c.dom.Text;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePage extends Fragment {

    EditText mAddress, mPhone;
    TextView mUserFullName, mUserEmail;

    //DAtabase things
    private DatabaseReference ref;
    private FirebaseDatabase database;

    User user;

    String name, email, address, phoneNumber, key;


    // TODO: Rename and change types of parameters
    AppUser userProfile;

    private OnFragmentInteractionListener mListener;

    public ProfilePage() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfilePage newInstance(AppUser givenUser) {
        ProfilePage fragment = new ProfilePage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userProfile = new AppUser();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users");
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        address = "Please set address";
        phoneNumber = "Please set  Phone Number";

        if (getArguments() != null) {
            name = getArguments().getString("name");
            email = getArguments().getString("email");
            key = getArguments().getString("key");
            user = new User(email, address, phoneNumber, name);
        }
        //ref.child(key).setValue(user);

        //mDatabase.child("users").child(key).setValue(user);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_page, container, false);
        mUserFullName = rootView.findViewById(R.id.name);
        mUserEmail = rootView.findViewById(R.id.email);
        mUserEmail.setText(email);
        mUserFullName.setText(name);

        mUserEmail.setText(email);
        mUserFullName.setText(name);
        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void submit(){

    }
}
