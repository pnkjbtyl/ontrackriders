package com.ontrack.ontrackriders.activity.home;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.fragment_home.HomeFragment;
import com.ontrack.ontrackriders.activity.fragment_profile.ProfileFragment;
import com.ontrack.ontrackriders.activity.fragment_routes.RouteFragment;
import com.ontrack.ontrackriders.activity.fragment_trip.TripFragment;
import com.ontrack.ontrackriders.activity.fragment_vehicle.VehicleFragment;
import com.ontrack.ontrackriders.activity.login.LoginActivity;
import com.ontrack.ontrackriders.utils.Pref;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private static String USER_EMAIL;
    private static final String TAG="HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        checkUser();
    }

    private void checkUser() {
        Log.d(TAG,"Checking user is logged in info");
        USER_EMAIL=Pref.getUserEmail(this);
        if(USER_EMAIL!=null)
        {
            Log.d(TAG,"Logged In=> "+USER_EMAIL);
        }
        else
        {
            startActivity(new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    private void initViews() {
        //initialise views
        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout= findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }
    @Override
    public void onBackPressed() {
        drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.nav_profile)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,
                    new ProfileFragment()).commit();
        }
       else if(item.getItemId()==R.id.nav_home)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,
                    new HomeFragment()).commit();
        }
       else if(item.getItemId()==R.id.nav_routes)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,
                    new RouteFragment()).commit();
        }
      else  if(item.getItemId()==R.id.nav_vehicle)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,
                    new VehicleFragment()).commit();
        }
        else  if(item.getItemId()==R.id.nav_trips)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,
                    new TripFragment()).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
