package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private void reportes(){
        // TODO que hace este método?
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case 3:
                // newGame();
                return true;
            case R.id.menu_action_report:

                goToActivity(ReportActivity.class);
                //showHelp();
                return true;
            case R.id.menu_action_out:
                //showHelp();
                return true;
            case R.id.menu_action_about:
                //showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createMenu(){

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_view);

        if(null==getSupportActionBar()) {
            setSupportActionBar(toolbar);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.btnSignIn, R.string.btnIngresar);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.inflateMenu(R.menu.menu_main);
        navigationView.setNavigationItemSelectedListener(this);

    }
    private Intent createIntent(Class className){
        return new Intent(getApplicationContext(),className);

    }
    private void goToActivity(Class className){
        startActivity(createIntent(className));
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        DrawerLayout mDrawerLayout;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //sdsd
        mDrawerLayout.closeDrawers();


        return onOptionsItemSelected(menuItem);
    }
}
