package com.example.myapplication.view;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.businessLogic.PersistentDataModel;

public class ActivityAddTransfer extends AbstractAddTransfer {

    protected void initSipnners() {
        // Populate Spinners
        Spinner origin = findViewById(R.id.spnTrfOrigin);
        ArrayAdapter<PersistentDataModel> categoryAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AccountsFacade.getInstance().getBankAccountsSpinnerModel(getApplicationContext()));
        origin.setAdapter(categoryAdapter);

        Spinner destination = findViewById(R.id.spnTrfDestination);
        ArrayAdapter<PersistentDataModel> payedWithAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AccountsFacade.getInstance().getBankAccountsSpinnerModel(getApplicationContext()));
        destination.setAdapter(payedWithAdapter);
    }
}