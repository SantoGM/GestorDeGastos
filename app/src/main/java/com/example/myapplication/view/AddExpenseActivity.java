package com.example.myapplication.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.businessLogic.CategoryFacade;
import com.example.myapplication.businessLogic.ExpenseFacade;
import com.example.myapplication.businessLogic.PersistentDataModel;
import com.example.myapplication.view.extras.DateHelper;
import com.example.myapplication.view.extras.DatePickerFragment;

import java.util.Date;
import java.util.HashMap;

public class AddExpenseActivity extends BaseActivity {

    private static final String FIELD_DATE = "date";
    private static final String FIELD_CATEGORY = "categoryID";
    private static final String FIELD_DETAIL = "detail";
    private static final String FIELD_AMOUNT = "amount";
    private static final String FIELD_PAYED_WITH = "accountID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        initSipnners();
        initButtons();
    }

    private void initButtons() {
        Button btnSend = findViewById(R.id.bntAddExpense);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    HashMap<String, Object> inputData = extractInputData();
                    Date date = (Date) inputData.get(FIELD_DATE);
                    Float amount = (Float) inputData.get(FIELD_AMOUNT);
                    Long categoryID = (Long) inputData.get(FIELD_CATEGORY);
                    Long accountID = (Long) inputData.get(FIELD_PAYED_WITH);
                    String detail = (String) inputData.get(FIELD_DETAIL);
                    try {
                        ExpenseFacade.getInstance().saveExpense(date, amount, categoryID, accountID, detail, getApplicationContext());
                        toastMe(getString(R.string.expense_saved_successfuly));
                        onBackPressed();
                    } catch (IllegalArgumentException e) {
                        toastMe(e.getMessage());
                    }

                } else {
                    Snackbar.make(v, getString(R.string.message_complete_mandatory_fields), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }

    private void toastMe(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private HashMap<String, Object> extractInputData() {
        HashMap<String, Object> result = new HashMap<>();

        // Date
        EditText input = findViewById(R.id.txtDate);
        String ddmmyyyy = input.getText().toString();
        result.put(FIELD_DATE, DateHelper.obtainDateFromString(ddmmyyyy));

        // Description
        input = findViewById(R.id.txtDescripttion);
        String desc = input.getText().toString();
        result.put(FIELD_DETAIL, desc);

        // Amount
        input = findViewById(R.id.nmbAmount);
        Float amount = Float.parseFloat(input.getText().toString());
        result.put(FIELD_AMOUNT, amount);

        // Category ID
        Spinner spinner = findViewById(R.id.spnCateogry);
        PersistentDataModel model = (PersistentDataModel) spinner.getSelectedItem();
        result.put(FIELD_CATEGORY, model.getId());

        // Account ID
        spinner = findViewById(R.id.spnPayedWith);
        model = (PersistentDataModel) spinner.getSelectedItem();
        result.put(FIELD_PAYED_WITH, model.getId());

        return result;
    }

    private void initSipnners() {
        // Populate Spinners
        Spinner category = findViewById(R.id.spnCateogry);
        ArrayAdapter<PersistentDataModel> categoryAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, CategoryFacade.getInstance().getCategoryDataModel(getApplicationContext()));
        category.setAdapter(categoryAdapter);

        Spinner payedWith = findViewById(R.id.spnPayedWith);
        ArrayAdapter<PersistentDataModel> payedWithAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AccountsFacade.getInstance().getAccountsSpinnerModel(getApplicationContext()));
        payedWith.setAdapter(payedWithAdapter);

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

    private boolean validateInput() {
        Boolean result = Boolean.TRUE;

        EditText nmbAmount = findViewById(R.id.nmbAmount);
        Editable input = nmbAmount.getText();

        if (input == null || input.toString().isEmpty()) {
            result = buildError(nmbAmount, getString(R.string.error_amount_required));
        } else {

            double value = Double.parseDouble(input.toString());

            if (value <= 0) {
                result = buildError(nmbAmount, getString(R.string.error_amount_must_be_greater_than_zero));
            }
        }
        return result;
    }

    @SuppressWarnings("SameReturnValue")
    private boolean buildError(EditText compoonent, String message) {
        compoonent.setError(message);
        compoonent.setFocusableInTouchMode(true);
        compoonent.requestFocus();
        return false;
    }
}
