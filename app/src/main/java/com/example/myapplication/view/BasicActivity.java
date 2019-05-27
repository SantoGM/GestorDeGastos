package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.businessLogic.PersistentDataModel;

public class BasicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        createMenu();
       // Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.addExpense);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddExpense = new Intent(getApplicationContext(), AddExpenseActivity.class);
                startActivity(goToAddExpense);
            }
        });

        ArrayAdapter<PersistentDataModel> adapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_listview, AccountsFacade.getInstance().getAccountsSpinnerModel(getApplicationContext()));

        ListView listView = findViewById(R.id.basicViewList);
        listView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
