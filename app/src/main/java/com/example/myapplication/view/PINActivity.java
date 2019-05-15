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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_pin);

        greetUser();
        initButtons();
    }

    private void initButtons() {
        Button btnEnter = findViewById(R.id.btnIngresar);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pin = getIntent().getIntExtra("pin",-1);
                Editable txtInput = ((EditText) findViewById(R.id.lblIngresoPin)).getText();
                Integer input = (txtInput.toString().equals(""))? new Integer(0):Integer.parseInt(txtInput.toString());

                if(input.toString().length()<4) {
                    toastMe("Complete el PIN");
                } else if (!input.equals(pin)) {
                    toastMe("PIN incorrecto");
                } else {
                    startActivity(new Intent(getApplicationContext(), BasicActivity.class));
                }
            }
        });

        TextView lblRequestPin = findViewById(R.id.lblRequestPin);
        lblRequestPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("email");
                // TODO Logica de envio de mail
                toastMe("Hemos enviado su PIN a " + email);
            }
        });
    }

    private void greetUser() {
        String user = getIntent().getStringExtra("user");
        if (user != null && !user.equals("")) {
            TextView text = findViewById(R.id.lblGreet);
            text.setText("Hola " + user + "!");
        }
    }

    private void toastMe(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
