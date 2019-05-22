package com.example.myapplication.view;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.businessLogic.CategoryFacade;
import com.example.myapplication.businessLogic.ExpenseFacade;
import com.example.myapplication.businessLogic.PersistentDataModel;

import java.util.Date;
import java.util.HashMap;

public class AddExpenseActivity extends BaseActivity {

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
                    Date date = (Date) inputData.get("date");
                    Float amount = (Float) inputData.get("amount");
                    Long categoryID = (Long) inputData.get("categoryID");
                    Long accountID = (Long) inputData.get("accountID");
                    String detail = (String) inputData.get("detail");
                    ExpenseFacade.getInstance().saveExpense(date, amount, categoryID, accountID, detail);
                }
            }
        });
    }

    private void initSipnners() {
        // Populate Spinners
        Spinner category = findViewById(R.id.spnCateogry);
        ArrayAdapter<PersistentDataModel> categoryAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, CategoryFacade.getInstance().getCategoryDataModel());
        category.setAdapter(categoryAdapter);

        Spinner payedWith = findViewById(R.id.spnPayedWith);
        ArrayAdapter<PersistentDataModel> payedWithAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AccountsFacade.getInstance().getAccountsSpinnerModel());
        payedWith.setAdapter(payedWithAdapter);

        // TODO Set listeners
    }

    private boolean validateInput() {
        Boolean result = true;

        EditText nmbAmount = findViewById(R.id.nmbAmount);
        Editable input = nmbAmount.getText();

        if (input == null || input.toString().trim().equals("")) {
            result = buildError(nmbAmount, "Ingrese el monto gastado");
        } else {

            Double value = Double.parseDouble(input.toString());

            if (value <= 0) {
                result = buildError(nmbAmount, "El monto ingresado debe ser mayor a cero");
            }
        }
        return result;
    }

    private boolean buildError(EditText compoonent, String message) {
        compoonent.setError(message);
        compoonent.setFocusableInTouchMode(true);
        compoonent.requestFocus();
        return false;
    }
}
