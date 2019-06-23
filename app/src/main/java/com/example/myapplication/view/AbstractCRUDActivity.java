package com.example.myapplication.view;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

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

    public void snackbarWithLightColor(View view, String msg){
        Snackbar snb = Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null);
        View snackbarView = snb.getView();
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.primaryLightColor));
        snb.show();
    }
}
