package jackal.org.cappyapp;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Boolean isThereUser = FALSE;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final int RC_SIGN_IN = 123;
    FirebaseUser fbUser;
    NavigationView navigationView;
    TextView username, userEmail;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build());

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");
    DatabaseReference usersRef = ref.child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "jackal.org.cappyapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("Your Tag", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference ref = database.getReference("https://cappys-mobile-applications.firebaseio.com/");

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,new tapListPage());
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        username = navigationView.getHeaderView(0).findViewById(R.id.welcome_user);
        userEmail = navigationView.getHeaderView(0).findViewById(R.id.user_email);
        navigationView.setNavigationItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    mAuth = FirebaseAuth.getInstance();
                    fbUser = mAuth.getCurrentUser();
                    String tmpname = fbUser.getDisplayName();
                    String tmpemail = fbUser.getEmail();

                    username.setText("Welcome to the Cappy's App " + fbUser.getDisplayName() + "!");
                    userEmail.setText(fbUser.getEmail());

                    System.out.println("User logged in");
                    Toast.makeText(MainDrawerActivity.this, fbUser.getDisplayName() + " has Signed In!",
                            Toast.LENGTH_SHORT).show();
                    isThereUser = TRUE;
                }
                else{
                    System.out.println("User not logged in");
                    Toast.makeText(MainDrawerActivity.this, "Signed Out",
                            Toast.LENGTH_SHORT).show();
                    username.setText("Welcome to the Cappy's App Guest!");
                    userEmail.setText("");
                    isThereUser = false;
                    displayView(R.id.nav_tap_list);

                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        menu.getItem(0).setEnabled(isThereUser);
        menu.getItem(1).setEnabled(!isThereUser);
        menu.getItem(2).setEnabled(isThereUser);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        menu.getItem(0).setEnabled(isThereUser);
        menu.getItem(1).setEnabled(!isThereUser);
        menu.getItem(2).setEnabled(isThereUser);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = null;


        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.profilePage) {
            if(isThereUser) {
                Bundle bundle = new Bundle();
                bundle.putString("name", fbUser.getDisplayName());
                bundle.putString("email", fbUser.getEmail());
                bundle.putString("key", fbUser.getUid());
                //bundle.putParcelable("user",currentUser);
                fragment = new ProfilePage();
                fragment.setArguments(bundle);
            }else{
                Toast.makeText(MainDrawerActivity.this,"Please Sign in to View Profile.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.sign_in) {

            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(),RC_SIGN_IN);
            fbUser = FirebaseAuth.getInstance().getCurrentUser();
            fbUser = mAuth.getCurrentUser();
            return true;
        }

        if (id == R.id.sign_out) {
            FirebaseAuth.getInstance().signOut();
            AuthUI.getInstance().signOut(this);
            return true;
        }

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayView(item.getItemId());
        return true;
    }


    public void displayView(int viewId) {
        Fragment fragment = null;
        AlertDialog.Builder builder;

        switch (viewId) {
            case R.id.nav_tap_list:
                fragment = new tapListPage();
                break;
            case R.id.nav_events:
                fragment = new eventPage();
                break;
            /*case R.id.smWebsite:
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Continue to Cappys Website?").setPositiveButton("Yes",webDialogClickListener).setNegativeButton("No",webDialogClickListener).show();
                break;*/
            case R.id.smTwitter:
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Continue to Twitter?").setPositiveButton("Mobile App", twitDCL)
                        .setNegativeButton("Website", twitDCL).show();
                break;
            case R.id.smFacebook:
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Continue to Facebook?").setPositiveButton("Mobile App", fbDCL)
                        .setNegativeButton("Website", fbDCL).show();
                break;
            case R.id.smInstagram:
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Continue to Instagram?").setPositiveButton("Mobile App", instaDCL)
                        .setNegativeButton("Website", instaDCL).show();
                break;
            case R.id.auGeneral:
                fragment = new genInformation();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    public boolean isSignedIn(){
        return isThereUser;
    }

    DialogInterface.OnClickListener twitDCL = new DialogInterface.OnClickListener() {
        Intent i;

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        getPackageManager().getPackageInfo("twitter://user?user_id=1543988708", 0);
                        i = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("twitter://user?screen_name=CappysWineandSpirits"));
                        startActivity(i);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Twitter is not installed on this device, please try again and select 'Website'.", Toast.LENGTH_LONG).show();
                    }

                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/LovelandCappys")));
                    break;
            }
        }


    };

    DialogInterface.OnClickListener fbDCL = new DialogInterface.OnClickListener() {
        Intent i;

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        getPackageManager().getPackageInfo("com.facebook.katana", 0);
                        i = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/431259720256588"));
                        startActivity(i);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Facebook is not installed on this device, please try again and select 'Website'.", Toast.LENGTH_LONG).show();
                    }
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/LovelandCappys"));
                    startActivity(i);
                    break;
            }
        }


    };
    DialogInterface.OnClickListener webDCL = new DialogInterface.OnClickListener() {
        Intent i;

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://lovelandcappys.com/"));
                    startActivity(i);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }


    };
    DialogInterface.OnClickListener instaDCL = new DialogInterface.OnClickListener() {
        Intent i;

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        getPackageManager().getPackageInfo("com.instagram.android", 0);
                        i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/cappyswineandspirits"));
                        startActivity(i);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Instagram is not installed on this device, please try again and select 'Website'.", Toast.LENGTH_LONG).show();
                    }
                    startActivity(i);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/cappyswineandspirits/")));
                    break;
            }
        }


    };


}