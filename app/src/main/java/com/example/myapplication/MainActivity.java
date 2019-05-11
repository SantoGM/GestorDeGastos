package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.database.DataBaseContract;
import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dataManager.UserDataManager;
import com.example.myapplication.view.pojo.UserDataPojo;

public class MainActivity extends AppCompatActivity {

    private OpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        dbOpenHelper = new OpenHelper(this);

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        db.query(DataBaseContract.UserDataEntry.TABLE_NAME,
                new String[]{DataBaseContract.UserDataEntry.COLUMN_PIN},
                null,
                 null,
                null,
                null, null);

        db.close();

        UserDataPojo userData = UserDataManager.getInstance(dbOpenHelper).getUserData();

        if (userData != null){
            // Ir a la pantalla de ingreso de PIN
        } else {
            // Ir a la pantalla de alta de usuario
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    protected void onDestroy() {
        dbOpenHelper.close();
        super.onDestroy();
    }
}
