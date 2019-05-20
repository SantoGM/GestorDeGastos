package com.example.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private Intent crearIntent(Class clas){
        return new Intent(getApplicationContext(),clas);

    }
    private void goToActivity(Class clas){
        startActivity(crearIntent(clas));
    }
    private void reportes(){

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_action_check:
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

}
