package com.example.dione.orderingapp;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dione.orderingapp.application.OrderingApplication;
import com.example.dione.orderingapp.fragment.MyCartFragment;
import com.example.dione.orderingapp.fragment.ProductsFragment;
import com.example.dione.orderingapp.helpers.Helpers;

/**
 * Created by dione on 27 Sep 2016.
 */

public class DashboardActivity extends AppCompatActivity {
    private TextView textViewSliderName;
    private TextView textViewSliderEmail;
    private ProgressDialog progressDialog;
    NavigationView nav_view;
    OrderingApplication orderingApplication;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initViews();
        initApi();
        initToolbarAndDrawer();
        setDrawerValues();
    }

    private void initToolbarAndDrawer(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_shopping_cart) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_content, new MyCartFragment()).commit();
                } else if (id == R.id.nav_logout) {
                    Helpers.clearPreferences(getApplicationContext());
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else if (id == R.id.nav_products_list) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_content, new ProductsFragment()).commit();
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void setDrawerValues(){
        textViewSliderName.setText(String.format("%s %s", Helpers.getStringPreference(this, (Constants.USER_FIRSTNAME)), Helpers.getStringPreference(this, (Constants.USER_LASTNAME))));
        textViewSliderEmail.setText(Helpers.getStringPreference(this, Constants.USER_EMAIL));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initApi(){
        orderingApplication = new OrderingApplication();
    }


    private void showCancellableProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.show();
    }
    private void initViews(){
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        View view = nav_view.getHeaderView(0);
        textViewSliderName = (TextView) view.findViewById(R.id.textViewSliderName);
        textViewSliderEmail = (TextView) view.findViewById(R.id.textViewSliderEmail);
    }



    @Override
    public void onResume() {
        super.onResume();
        orderingApplication.mBus.register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        orderingApplication.mBus.unregister(this);
    }


}
