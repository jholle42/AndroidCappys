package jackal.org.cappyapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    firebaseDatabaseInteractor dbInteractor;
    Boolean isThereUser = FALSE;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final int RC_SIGN_IN = 123;
    AppUser currentUser = new AppUser();
    FirebaseUser fbUser;
    NavigationView navigationView;
    TextView username, userEmail;


    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build());

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        //Intent i = new Intent(this, LoginActivity.class);
        //startActivityForResult(i, 123);

        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

                    setUser(tmpname,tmpemail);

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.sign_in) {

            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(),RC_SIGN_IN);
            fbUser = FirebaseAuth.getInstance().getCurrentUser();
            fbUser = mAuth.getCurrentUser();
             if(fbUser!= null) {
                 setUser(fbUser.getDisplayName(),fbUser.getEmail());
             }
            return true;
        }

        if (id == R.id.sign_out) {
            FirebaseAuth.getInstance().signOut();
            AuthUI.getInstance().signOut(this);
            return true;
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

        switch (viewId) {
            case R.id.profilePage:
                if(isThereUser) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("user",currentUser);
                    fragment = new ProfilePage();
                    fragment.setArguments(bundle);
                }else{
                    Toast.makeText(MainDrawerActivity.this,"Please Sign in to View Profile.",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_main:
                fragment = new mainPage();
                break;
            case R.id.nav_tap_list:
                fragment = new tapListPage();
                break;
            case R.id.nav_blog:
                fragment = new blogPage();
                break;
            case R.id.nav_events:
                fragment = new eventPage();
                break;
            case R.id.smTwitter:
                fragment = new twitterFeed();
                break;
            case R.id.smFacebook:
                fragment = new facebookFeed();
                break;
            case R.id.smInstagram:
                fragment = new instagramFeed();
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

    public void setUser(String name, String email){
        currentUser.setFullName(name);
        currentUser.setEmail(email);
        //currentUser.setAddress("Please Set Address.");
        //currentUser.setPhoneNumber("Please Set Phone Numer");
    }

}


