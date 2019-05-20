package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.businessLogic.AccountsFacade;
import com.example.myapplication.businessLogic.CategoryFacade;
import com.example.myapplication.businessLogic.PersistentDataModel;

public class AddExpenseActivity extends AppCompatActivity {
    private TextView mTextMessage;

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
                // TODO Validate data, then INSERT record and get back to BasicActivity
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
}
