package com.example.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class PINActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_pin);

        Button btnregistro = (Button) findViewById(R.id.btnRegistrarse);
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText lblPin = (EditText) findViewById(R.id.lblIngresoPin);

            }
        });
    }
}
