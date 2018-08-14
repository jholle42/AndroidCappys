package jackal.org.cappyapp;

import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
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
   DatabaseReference mAdminHoldReference;


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
                h.setUid(postSnapshot.child("uid").getValue(String.class));
                h.setMessage(postSnapshot.child("Message").getValue(String.class));
                userHolds.add(h);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {}
    };

    String name, email, address, phoneNumber, key;

    private OnFragmentInteractionListener mListener;

    public ProfilePage() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfilePage newInstance() {
        ProfilePage fragment = new ProfilePage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        address = "Please set address";
        phoneNumber = "Please set  Phone Number";



        if (getArguments() != null) {
            name = getArguments().getString("name");
            email = getArguments().getString("email");
            key = getArguments().getString("key");
        }

        mHoldReference = mFirebaseDatabase.getReference("holds/"+ key);
        mAdminHoldReference = mFirebaseDatabase.getReference("adminHolds");
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
        mAddHold.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                createNewHold();
            }
        });

        initHolds();

        mUserEmail.setText(email);
        mUserFullName.setText(name);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RVAdapter(userHolds, mHoldReference,mAdminHoldReference, getActivity());
        mRecyclerView.setAdapter(mAdapter);


        Button.OnClickListener mAddListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createNewHold();
            }
        };



        return rootView;


    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onResume() {

        super.onResume();
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
        if(userHolds.isEmpty()){

        }else{
            for(hold h:userHolds){
                addHold(h,h.getItemName(),h.getUid());
            }
        }


    }

    public void addHold(final hold Hold, String key, String aKey) {
        final DatabaseReference itemLocation = mHoldReference.push();
        mHoldReference.child(key).setValue(Hold);
        mAdminHoldReference.child(aKey).setValue(Hold);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101) {
            if (resultCode == 102) {
                hold tmp = new hold(name,data.getStringExtra("n"),data.getStringExtra("i"),data.getStringExtra("a"),"Awaiting Approval...",2,mAdminHoldReference.push().getKey());
                userHolds.add(tmp);
                mAdapter.notifyDataSetChanged();
                addHold(tmp,tmp.getItemName(),tmp.getUid());
            }
        }
    }



    public void createNewHold(){
        //hold nH = new hold();
        Intent i = new Intent(getActivity(), addNewHold.class );
        startActivityForResult(i, 101);
        //return nH;

    }




}
