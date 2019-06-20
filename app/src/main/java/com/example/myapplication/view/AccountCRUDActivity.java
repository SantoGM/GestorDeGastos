package com.example.myapplication.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;

import java.util.HashMap;

public class AccountCRUDActivity extends AbstractCRUDActivity {

    private static final Integer TYPE_ACCOUNT = 0;
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_BALANCE = "balance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_crud);

        Button btnAddAcount = findViewById(R.id.btnAddAcount);
        btnAddAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    HashMap<String, Object> inputData = extractInputData();
                    String name = (String) inputData.get(FIELD_NAME);
                    String descrip = (String) inputData.get(FIELD_DESCRIPTION);
                    Float balance = (Float) inputData.get(FIELD_BALANCE);

                    AccountsFacade.getInstance().saveAccount(name,TYPE_ACCOUNT, descrip, balance, getApplicationContext());

                    toastMe(getString(R.string.msg_account_added));
                    onBackPressed();
                } else {
                    Snackbar.make(v, getString(R.string.message_complete_mandatory_fields), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }

    protected HashMap<String, Object> extractInputData() {
        HashMap result = new HashMap<String, Object>();

        // Account Name
        EditText input = findViewById(R.id.lblAccountName);
        String name = input.getText().toString();
        result.put(FIELD_NAME, name);

        // Account Description
        input = findViewById(R.id.lblAccountDescrip);
        String desc = input.getText().toString();
        result.put(FIELD_DESCRIPTION, desc);

        // Account Balance
        input = findViewById(R.id.lblAccountBalance);
        Float balance = Float.parseFloat(input.getText().toString());
        result.put(FIELD_BALANCE, balance);


        return result;
    }

    protected boolean validateInput() {
        Boolean result = Boolean.TRUE;
        Editable input;

        EditText txtAccName = findViewById(R.id.lblAccountName);
        input = txtAccName.getText();

        if (input.toString().isEmpty()
                || input.toString().length()<3) {
            result = buildError(txtAccName, getString(R.string.error_invalid_name));
        }

        EditText nmbBalance = findViewById(R.id.lblAccountBalance);
        input = nmbBalance.getText();

        if (input == null || input.toString().isEmpty()) {
            result = buildError(nmbBalance, getString(R.string.error_balance_required));
        }

        return result;
    }
}
