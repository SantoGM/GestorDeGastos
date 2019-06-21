package com.example.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public abstract class AbstractCRUDActivity extends AppCompatActivity {

    protected void toastMe(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected abstract HashMap<String, Object> extractInputData();

    protected abstract boolean validateInput();

    @SuppressWarnings("SameReturnValue")
    protected boolean buildError(EditText compoonent, String message) {
        compoonent.setError(message);
        compoonent.setFocusableInTouchMode(true);
        compoonent.requestFocus();
        return false;
    }
}
