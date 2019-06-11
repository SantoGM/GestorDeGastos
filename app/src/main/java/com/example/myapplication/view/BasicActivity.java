package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.ExpenseFacade;
import com.example.myapplication.view.extras.ExpandableCategoryAdapter;
import com.example.myapplication.view.pojo.PaymentPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BasicActivity extends BaseActivity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        createMenu();

        FloatingActionButton fab = findViewById(R.id.addExpense);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddExpense = new Intent(getApplicationContext(), AddExpenseActivity.class);
                startActivity(goToAddExpense);
            }
        });

        ExpenseFacade.getInstance().addObserver(this);
        fetchData();
    }

    private void fetchData() {

        List<PaymentPojo> data = ExpenseFacade.getInstance().obtainLastFiveMovements(getApplicationContext());

        ArrayList<String> accounts = new ArrayList<>();
        HashMap<String, List<PaymentPojo>> expenses = new HashMap<>();

        accounts.add("Cuenta base");
        expenses.put(accounts.get(0), data);

        ExpandableCategoryAdapter adapter = new ExpandableCategoryAdapter(accounts, expenses, getApplicationContext());
        
        ExpandableListView listView = findViewById(R.id.basicViewList);
        listView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        // Do nothing
    }

    @Override
    public void update(Observable o, Object arg) {
        fetchData();
    }
}
