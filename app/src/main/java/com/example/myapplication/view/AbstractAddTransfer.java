package com.example.myapplication.view;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.businessLogic.MovementsFacade;
import com.example.myapplication.businessLogic.PersistentDataModel;
import com.example.myapplication.view.extras.DateHelper;
import com.example.myapplication.view.extras.DatePickerFragment;

import java.util.Date;
import java.util.HashMap;

public abstract class AbstractAddTransfer extends AbstractCRUDActivity{
    protected static final String FIELD_DATE = "date";
    protected static final String FIELD_ORIGIN = "originID";
    protected static final String FIELD_DESTINATION = "destinationID";
    protected static final String FIELD_AMOUNT = "amount";
    protected static final String FIELD_DETAIL = "detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transfer);

        initSipnners();
        initDatePicker();
        initButtons();
    }

    protected void initButtons() {
        Button btnSend = findViewById(R.id.btnAddTransfer);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    HashMap<String, Object> inputData = extractInputData();
                    Date date = (Date) inputData.get(FIELD_DATE);
                    Long originID = (Long) inputData.get(FIELD_ORIGIN);
                    Long destinationID = (Long) inputData.get(FIELD_DESTINATION);
                    Float amount = (Float) inputData.get(FIELD_AMOUNT);
                    String detail = (String) inputData.get(FIELD_DETAIL);
                    try {
                        MovementsFacade.getInstance().saveTransfer(date, originID, destinationID, amount, detail, getApplicationContext());
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

    abstract protected void initSipnners();

    private void initDatePicker(){
        // Set up DatePicker
        final EditText txtDate = findViewById(R.id.txtDate);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.txtDate) {
                    showDatePickerDialog(txtDate);
                }
            }
        });
        txtDate.setText(DateHelper.getTodayDate());
    }

    private void showDatePickerDialog(final EditText txtDate) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = DateHelper.toStringDDMMYYYY(day, month, year);
                txtDate.setText(selectedDate);
            }
        }, Boolean.TRUE);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected HashMap<String, Object> extractInputData() {
        HashMap<String, Object> result = new HashMap<>();

        // Date
        EditText input = findViewById(R.id.txtDate);
        String ddmmyyyy = input.getText().toString();
        result.put(FIELD_DATE, DateHelper.obtainDateFromString(ddmmyyyy));

        // Origin Account ID
        Spinner spinner = findViewById(R.id.spnTrfOrigin);
        PersistentDataModel model = (PersistentDataModel) spinner.getSelectedItem();
        result.put(FIELD_ORIGIN, model.getId());

        // Destination Account ID
        spinner = findViewById(R.id.spnTrfDestination);
        model = (PersistentDataModel) spinner.getSelectedItem();
        result.put(FIELD_DESTINATION, model.getId());

        // Amount
        input = findViewById(R.id.txtTrfAmount);
        Float amount = Float.parseFloat(input.getText().toString());
        result.put(FIELD_AMOUNT, amount);

        // Description
        input = findViewById(R.id.txtTrfDescription);
        String desc = input.getText().toString();
        result.put(FIELD_DETAIL, desc);

        return result;
    }

    @Override
    protected boolean validateInput() {
        Boolean result = Boolean.TRUE;

        Spinner spinner = findViewById(R.id.spnTrfOrigin);
        String origin = spinner.getSelectedItem().toString();
        spinner = findViewById(R.id.spnTrfDestination);
        String destination = spinner.getSelectedItem().toString();

        if (origin.equals(destination)){
            TextView errorText = (TextView)spinner.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            toastMe(getString(R.string.error_trf_same_account));
            result = false;
        }

        EditText nmbAmount = findViewById(R.id.txtTrfAmount);
        Editable input = nmbAmount.getText();

        if (input == null || input.toString().isEmpty()) {
            result = buildError(nmbAmount, getString(R.string.error_trf_amount_required));
        } else {

            double value = Double.parseDouble(input.toString());

            if (value <= 0) {
                result = buildError(nmbAmount, getString(R.string.error_amount_must_be_greater_than_zero));
            }
        }

        return result;
    }
}
