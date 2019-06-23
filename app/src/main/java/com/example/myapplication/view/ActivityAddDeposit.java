package com.example.myapplication.view;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.businessLogic.MovementsFacade;
import com.example.myapplication.businessLogic.PersistentDataModel;

import java.util.Date;
import java.util.HashMap;

public class ActivityAddDeposit extends AbstractAddTransfer {
    @Override
    protected void initSipnners() {
        // Populate Spinners
        Spinner origin = findViewById(R.id.spnTrfOrigin);
        ArrayAdapter<PersistentDataModel> originAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AccountsFacade.getInstance().getExternalAccountSpinnerModel());
        origin.setAdapter(originAdapter);
        origin.setEnabled(Boolean.FALSE);

        Spinner destination = findViewById(R.id.spnTrfDestination);
        ArrayAdapter<PersistentDataModel> payedWithAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AccountsFacade.getInstance().getAllAccountsSpinnerModel(getApplicationContext()));
        destination.setAdapter(payedWithAdapter);
    }

    @Override
    protected void initButtons() {
        Button btnSend = findViewById(R.id.btnAddTransfer);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    HashMap<String, Object> inputData = extractInputData();
                    Date date = (Date) inputData.get(FIELD_DATE);
                    Long destinationID = (Long) inputData.get(FIELD_DESTINATION);
                    Float amount = (Float) inputData.get(FIELD_AMOUNT);
                    String detail = (String) inputData.get(FIELD_DETAIL);
                    try {
                        MovementsFacade.getInstance().saveDeposit(date, destinationID, amount, detail, getApplicationContext());
                        toastMe(getString(R.string.expense_saved_successfuly));
                        onBackPressed();
                    } catch (IllegalArgumentException e) {
                        toastMe(e.getMessage());
                    }
                } else {
                    snackbarWithLightColor(v, getString(R.string.message_complete_mandatory_fields));
                }
            }
        });
    }
}
