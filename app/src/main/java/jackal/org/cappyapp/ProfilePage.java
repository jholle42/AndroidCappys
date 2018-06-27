package jackal.org.cappyapp;

import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.PropertyName;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    List<hold> userHolds;
    Button mAddHold;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

   //database
   FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
   DatabaseReference mHoldReference;


    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                hold h = new hold();
                h.setApproved(postSnapshot.child("approved").getValue(Integer.class));
                h.setItemName(postSnapshot.child("itemName").getValue(String.class));
                h.setName(postSnapshot.child("name").getValue(String.class));
                h.setNumber(postSnapshot.child("number").getValue(String.class));
                h.setQuantity(postSnapshot.child("quantity").getValue(String.class));
                userHolds.add(h);
                mAdapter.notifyDataSetChanged();
            }
        }
        /*
        String newsAuthor = ds.child("newsAuthor").getValue(String.class);
            String newsDate = ds.child("newsDate").getValue(String.class);
            String newsDesc = ds.child("newsDesc").getValue(String.class);
            String newsImageUrl = ds.child("newsImageUrl").getValue(String.class);
            String newsTitle = ds.child("newsTitle").getValue(String.class);
            String newsUrl = ds.child("newsUrl").getValue(String.class);*/

        @Override
        public void onCancelled(DatabaseError databaseError) {}
    };


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
        address = "Please set address";
        phoneNumber = "Please set  Phone Number";



        if (getArguments() != null) {
            name = getArguments().getString("name");
            email = getArguments().getString("email");
            key = getArguments().getString("key");
            user = new User(email, address, phoneNumber, name);
        }

        mHoldReference = mFirebaseDatabase.getReference("holds/"+ key);

        mHoldReference.addListenerForSingleValueEvent(eventListener);


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_page, container, false);
        mRecyclerView = rootView.findViewById(R.id.sectionHolds);
        mUserFullName = rootView.findViewById(R.id.name);
        mUserEmail = rootView.findViewById(R.id.email);
        mUserEmail.setText(email);
        mUserFullName.setText(name);
        mAddHold = rootView.findViewById(R.id.addNew);
        mAddHold.setOnClickListener(mAddListener);

        initHolds();

        mUserEmail.setText(email);
        mUserFullName.setText(name);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RVAdapter(userHolds);
        mRecyclerView.setAdapter(mAdapter);

        Button.OnClickListener mAddListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createNewHold();
            }
        };



        return rootView;


    }

    Button.OnClickListener mAddListener;
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

// Attach a listener to read the data at our posts reference


    public void initHolds(){
        userHolds = new ArrayList<>();

        //userHolds.add(new hold(mUserFullName.getText().toString(),"513-------","GyroScope","2",1));
        //userHolds.add(new hold(mUserFullName.getText().toString(),"513-------","Chomolungma","1",2));
        //userHolds.add(new hold(mUserFullName.getText().toString(),"513-------","Knob Creek","4",3));
        //userHolds.add(new hold(mUserFullName.getText().toString(),"513-------","Blantons","2",2));
        if(userHolds.isEmpty()){

        }else{
            for(hold h:userHolds){
                addHold(h,h.getItemName());
            }
        }


    }

    public void addHold(final hold Hold, String key) {
        final DatabaseReference itemLocation = mHoldReference.push();
        mHoldReference.child(key).setValue(Hold);
    }

    public void createNewHold(){
        //hold nH = new hold();
        Intent i = new Intent(getActivity(), addHold.class );
        startActivity(i);
        //return nH;

    }



}
