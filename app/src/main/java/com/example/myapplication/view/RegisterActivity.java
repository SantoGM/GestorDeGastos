package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnregistro = (Button) findViewById(R.id.btnRegistrarse);
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText lblMail = (EditText) findViewById(R.id.lblEmail);
                EditText lblNombre = (EditText) findViewById(R.id.lblNombre);
                EditText lblApellido = (EditText) findViewById(R.id.lblApellido);
                EditText lblPin = (EditText) findViewById(R.id.lblPin);

                if (validarCampos(lblMail, lblNombre, lblApellido, lblPin)) {
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    startActivityForResult(intent, 0);
                }
            }
        });
    }

    private boolean validarEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private boolean validarCampos(EditText lblMail,EditText lblNombre, EditText lblApellido, EditText lblPin){

        boolean ok = false;

        lblMail.setBackgroundColor(getResources().getColor(android.R.color.white));
        lblNombre.setBackgroundColor(getResources().getColor(android.R.color.white));
        lblApellido.setBackgroundColor(getResources().getColor(android.R.color.white));
        lblPin.setBackgroundColor(getResources().getColor(android.R.color.white));

        lblMail.setError(null);


        if(!validarEmail(lblMail.getText().toString())){

            lblMail.setError("Ingrese mail");

            lblMail.setBackgroundColor((int)R.color.colorAccent);

            lblMail.setFocusableInTouchMode(true);
            lblMail.requestFocus();

        }else {
            if (lblNombre.getText().toString().isEmpty()) {

                lblNombre.setError("Ingrese nombre");

                lblNombre.setBackgroundColor((int) R.color.colorAccent);

                lblNombre.setFocusableInTouchMode(true);
                lblNombre.requestFocus();

            } else {
                if (lblApellido.getText().toString().isEmpty()) {

                    lblApellido.setError("Ingrese apellido");

                    lblApellido.setBackgroundColor((int) R.color.colorAccent);

                    lblApellido.setFocusableInTouchMode(true);
                    lblApellido.requestFocus();


                } else {
                    if (lblPin.getText().toString().isEmpty()) {

                        lblPin.setError("Ingrese PIN");

                        lblPin.setBackgroundColor((int) R.color.colorAccent);

                        lblPin.setFocusableInTouchMode(true);
                        lblPin.requestFocus();

                        
                    } else {
                        ok = true;
                    }
                }
            }
        }
        return ok;
    }
}
