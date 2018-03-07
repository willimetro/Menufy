package com.udemy.wilfredo.menufy.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.udemy.wilfredo.menufy.R;
import com.udemy.wilfredo.menufy.fragments.AlertsFragment;
import com.udemy.wilfredo.menufy.fragments.EmailFragment;
import com.udemy.wilfredo.menufy.fragments.InformationFragment;

public class MenufyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menufy);

        setToolbar();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);

        setDefaultFragment();
    }

    private void setDefaultFragment(){
        changeFragment(new EmailFragment(), navigationView.getMenu().getItem(0));
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void changeFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.menu_nav_email:
                fragment = new EmailFragment();
                fragmentTransaction = true;
                break;
            case R.id.menu_nav_alerts:
                fragment = new AlertsFragment();
                fragmentTransaction = true;
                break;
            case R.id.menu_nav_information:
                fragment = new InformationFragment();
                fragmentTransaction = true;
                break;
        }

        if(fragmentTransaction){
            changeFragment(fragment,item);
            drawerLayout.closeDrawers();
        }
        return true;
    }
}
