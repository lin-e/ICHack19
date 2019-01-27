package com.ichack.educate;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.NavigableMap;

public class HomeActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private DrawerLayout drawer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    // Set styling for the toolbar
    Toolbar toolbar = findViewById(R.id.homeToolbar);
    setSupportActionBar(toolbar);

    // Add functionality of the navigation menu 'drawer'
    drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    // Set the user information for the navigation menu
    TextView displayNameTV = findViewById(R.id.displayNameTV);
    TextView emailTV = findViewById(R.id.emailTV);
    ImageView profilePicImgV = findViewById(R.id.profilePicImgV);

    //TODO: Insert retrieval of user's data and save as a string in res files.
    //displayNameTV.setText(getResources().getString(R.string.display_name));
    //emailTV.setText(getResources().getString(R.string.account_email));
    //profilePicImgV.setImageBitmap(Image path);


    // If it is the first time the activity is loaded, it wil default to the dashboard,
    // otherwise it will not keep refreshing back to the dashboard on every refresh.
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
              new DashboardFragment()).commit();
      navigationView.setCheckedItem(R.id.nav_dashboard);
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    switch (menuItem.getItemId()) {
      case R.id.nav_dashboard:
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, new DashboardFragment())
            .commit();
        break;
      case R.id.nav_groups:
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, new GroupsFragment())
            .commit();
        break;
      case R.id.nav_assignments:
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, new AssignmentsFragment())
            .commit();
        break;
      case R.id.nav_invites:
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new InvitesFragment())
                .commit();
        break;
      case R.id.nav_settings:
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, new SettingsFragment())
            .commit();
        break;
    }

    drawer.closeDrawer(GravityCompat.START);

    return true;
  }

  // If the back button is pressed when the nav menu is open, just close the menu
  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }
}
