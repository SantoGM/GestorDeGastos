package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.view.extras.AccountsListAdapter;
import com.example.myapplication.view.pojo.AccountPojo;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AccountsActivity extends BaseActivity implements Observer {

    private FloatingActionMenu fam;
    private FloatingActionButton fabAddAccount, fabAddCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        createMenu();

        fabAddAccount =  findViewById(R.id.btnAddAcount);
        fabAddCard = findViewById(R.id.btnAddCard);
        fam = findViewById(R.id.fab_menu);


        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {

            }
        });

        fabAddAccount.setOnClickListener(onButtonClick());
        fabAddCard.setOnClickListener(onButtonClick());


        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                }
            }
        });

        AccountsFacade.getInstance().addObserver(this);
        fetchData();

    }

    private void fetchData() {

        List<AccountPojo> data = AccountsFacade.getInstance().loadAccounts(getApplicationContext());
        AccountsListAdapter adapter = new AccountsListAdapter(getApplicationContext(), data);

        ListView listView = findViewById(R.id.accountsViewList);
        listView.setAdapter(adapter);
    }

    @Override
    public void update(Observable o, Object arg) {
        fetchData();
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fabAddAccount) {
                    startActivity(new Intent(getApplicationContext(), AccountCRUDActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), CardCRUDActivity.class));
                }
                fam.close(true);
            }
        };
    }
}
