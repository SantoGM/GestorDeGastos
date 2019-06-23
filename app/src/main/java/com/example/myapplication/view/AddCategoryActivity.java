package com.example.myapplication.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.CategoryFacade;

import java.util.HashMap;

public class AddCategoryActivity extends AbstractCRUDActivity {
    private static final String FIELD_CATEGORY = "categoryName";
    private static final String FIELD_DETAIL = "detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        initButtons();
    }

    private void initButtons() {
        Button btnSend = findViewById(R.id.btnAddCategory);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    HashMap<String, Object> inputData = extractInputData();
                    String name = (String) inputData.get(FIELD_CATEGORY);
                    String detail = (String) inputData.get(FIELD_DETAIL);
                    CategoryFacade.getInstance().saveCategory(name, detail, getApplicationContext());
                    toastMe(getString(R.string.category_saved_successfuly));
                    onBackPressed();
                } else {
                    Snackbar.make(v, getString(R.string.message_complete_mandatory_fields), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    protected HashMap<String, Object> extractInputData() {
        HashMap result = new HashMap<String, Object>();
        EditText input;

        // Description
        input = findViewById(R.id.txtDescription);
        String desc = input.getText().toString();
        result.put(FIELD_DETAIL, desc);

        // Category name
        input = findViewById(R.id.txtName);
        String name = input.getText().toString();
        result.put(FIELD_CATEGORY, name);

        return result;
    }

    @Override
    protected boolean validateInput() {
        Boolean result = Boolean.TRUE;

        EditText input = findViewById(R.id.txtName);

        if (input == null || input.getText() == null || input.getText().toString().isEmpty()) {
            result = buildError(input, getString(R.string.error_category_name_required));
        }

        return result;
    }
}
