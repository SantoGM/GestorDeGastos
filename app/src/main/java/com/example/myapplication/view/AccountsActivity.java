package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Selection;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.view.extras.AccountsListAdapter;
import com.example.myapplication.view.pojo.AccountPojo;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static android.view.ContextMenu.*;

public class AccountsActivity extends BaseActivity implements Observer {

    private FloatingActionMenu fam;
    private FloatingActionButton fabAddAccount, fabAddCard;
    private AdapterView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        listView = (ListView) findViewById(R.id.accountsViewList);
        registerForContextMenu(listView);

        createMenu();

        fabAddAccount =  (FloatingActionButton) findViewById(R.id.btnAddAcount);
        fabAddCard = (FloatingActionButton)findViewById(R.id.btnAddCard);
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);


        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    showToast("Menu");
                }
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
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_etiqueta, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AccountPojo ItemList  = (AccountPojo) listView.getItemAtPosition(info.position);

        switch (item.getItemId()) {
            case R.id.CtxLblOpc1:

                AccountsFacade.getInstance().deleteAccount(getApplicationContext(), ItemList.getId());
                showToast("Se ha eliminado la cuenta");
                return true;
            case R.id.CtxLblOpc2:

                showToast("Se ha Actualizado la cuenta");
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        // Do nothing
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

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
