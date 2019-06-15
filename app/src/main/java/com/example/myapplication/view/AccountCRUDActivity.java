package com.example.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;

import java.util.HashMap;

public class AccountCRUDActivity extends AppCompatActivity {

    private static final Integer TYPE_ACCOUNT = 0;
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_BALANCE = "balance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_crud);
 /*       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/
        Button btnAddAcount = findViewById(R.id.btnAddAcount);
        btnAddAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> inputData = extractInputData();
                String name = (String) inputData.get(FIELD_NAME);
                String descrip = (String) inputData.get(FIELD_DESCRIPTION);
                Float balance = (Float) inputData.get(FIELD_BALANCE);

                AccountsFacade.getInstance().saveAccount(name,TYPE_ACCOUNT, descrip, balance, getApplicationContext());

                toastMe("Se guardó la cuenta");
                onBackPressed();
            }
        });
    }

    private void toastMe(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }


    private HashMap<String, Object> extractInputData() {
        HashMap result = new HashMap<String, Object>();

        // Date
        EditText input = findViewById(R.id.lblAccountName);
        String name = input.getText().toString();
        result.put(FIELD_NAME, name);

        // Description
        input = findViewById(R.id.lblAccountDescrip);
        String desc = input.getText().toString();
        result.put(FIELD_DESCRIPTION, desc);

        // Amount
        input = findViewById(R.id.lblAccountBalance);
        Float balance = Float.parseFloat(input.getText().toString());
        result.put(FIELD_BALANCE, balance);


        return result;
    }

}
