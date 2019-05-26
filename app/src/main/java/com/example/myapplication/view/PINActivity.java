package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class PINActivity extends AppCompatActivity {

    private final Integer INVALID_PIN = -1;
    private final Integer NULL_PIN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_pin);

        greetUser();
        initButtons();
    }

    private void initButtons() {
        // Enter button
        Button btnEnter = findViewById(R.id.btnIngresar);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()) {
                    startActivity(new Intent(getApplicationContext(), BasicActivity.class));
                }
            }
        });

        // Forgot PIN button
        TextView lblRequestPin = findViewById(R.id.lblRequestPin);
        lblRequestPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra(IntentViewConstants.EMAIL);
                // TODO Logica de envio de mail
                toastMe("Hemos enviado su PIN a " + email);
            }
        });
    }

    private boolean validateInput() {
        Boolean result = Boolean.TRUE;

        Integer pin = getIntent().getIntExtra(IntentViewConstants.PIN, INVALID_PIN);
        EditText txtComponent = findViewById(R.id.lblIngresoPin);
        Editable txtInput = txtComponent.getText();
        Integer input = (txtInput.toString().equals("")) ? NULL_PIN : Integer.parseInt(txtInput.toString());

        if (input.toString().length() < 4) {
            result = buildError(txtComponent, "Complete el PIN");
        } else if (!input.equals(pin)) {
            txtInput.clear();
            result = buildError(txtComponent,"PIN incorrecto");
        }
        return result;
    }

    private void greetUser() {
        String user = getIntent().getStringExtra(IntentViewConstants.USER);
        if (user != null && !user.equals("")) {
            TextView text = findViewById(R.id.lblGreet);
            text.setText("Hola " + user + "!");
        }
    }

    private void toastMe(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @SuppressWarnings("SameReturnValue")
    private boolean buildError(EditText compoonent, String message) {
        compoonent.setError(message);
        compoonent.setFocusableInTouchMode(true);
        compoonent.requestFocus();
        return false;
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
