package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.businessLogic.ExpenseFacade;
import com.example.myapplication.view.extras.ExpandableCategoryAdapter;
import com.example.myapplication.view.pojo.AccountPojo;
import com.example.myapplication.view.pojo.PaymentPojo;
import com.example.myapplication.businessLogic.PersistentDataModel;

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
        AccountsFacade.getInstance().addObserver(this);
        fetchData();
    }

    private void fetchData() {
        // Get all accounts and Credit Cars
        List<AccountPojo> accounts = AccountsFacade.getInstance().getAllAccountsAndCards(getApplicationContext());
        ArrayList<String> accountNames = new ArrayList<>();
        HashMap<String, List<PaymentPojo>> expenses = new HashMap<>();

        for(AccountPojo account : accounts){
            List<PaymentPojo> data = ExpenseFacade.getInstance().obtainLastFiveMovementsByAccount(getApplicationContext(), account.getId());
            accountNames.add(account.getName());
            expenses.put(account.getName(), data);
        }

        ExpandableCategoryAdapter adapter = new ExpandableCategoryAdapter(accountNames, expenses, getApplicationContext());
        
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
