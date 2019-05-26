package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.database.dataManager.UserDataManager;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = findViewById(R.id.btnRegistrarse);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText lblName = findViewById(R.id.lblName);
                EditText lblMail = findViewById(R.id.lblEmail);
                EditText lblPinInput = findViewById(R.id.lblPinInput);
                EditText lblPinRepeat = findViewById(R.id.lblPinRepeat);

                if (validateRegister(lblName, lblMail, lblPinInput, lblPinRepeat)) {

                    UserDataManager.getInstance().
                            registerUser(lblName.getText().toString(),
                                    lblMail.getText().toString(),
                                    Integer.parseInt(lblPinInput.getText().toString()),
                                    getApplicationContext());

                    Intent intent = new Intent(view.getContext(), BasicActivity.class);
                    startActivityForResult(intent, 0);
                }
            }
        });
    }

    private boolean validateEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private boolean validateRegister(EditText lblNombre, EditText lblMail, EditText lblPinInput, EditText lblPinRepeat){

        boolean ok = true;

        lblMail.setError(null);

        if(!validateEmail(lblMail.getText().toString())){
            ok = buildError(lblMail, "Ingrese una direccion valida");
        }

        if (lblNombre.getText().toString().isEmpty()
                || lblNombre.getText().toString().length()<3) {
            ok = buildError(lblNombre, "Ingrese el nombre (al menos 3 caracteres)");
        }

        if (lblPinInput.getText().toString().isEmpty()
                || lblPinInput.getText().toString().length() < 4) {
            ok = buildError(lblPinInput, "Ingrese un PIN de 4 digitos");
        } else {
            String inputPin = lblPinInput.getText().toString();
            if (lblPinRepeat.getText().toString().isEmpty()){
                ok = buildError(lblPinRepeat, "Reingrese el pin para verificar");
            } else if (!lblPinRepeat.getText().toString().equals(inputPin)) {
                ok = buildError(lblPinRepeat, "El PIN ingresado no coincide");
            }
        }

        return ok;
    }

    @SuppressWarnings("SameReturnValue")
    private boolean buildError(EditText compoonent, String message) {
        compoonent.setError(message);
        compoonent.setFocusableInTouchMode(true);
        return false;
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
