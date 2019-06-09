package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.myapplication.database.dataManager.UserDataManager;
import com.example.myapplication.view.IntentViewConstants;
import com.example.myapplication.view.PINActivity;
import com.example.myapplication.view.RegisterActivity;
import com.example.myapplication.view.pojo.UserDataPojo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserDataPojo userData = UserDataManager.getInstance().getUserData(getApplicationContext());

        if (userData != null){
            // Creo el Intent para ir a PINActivity
            Intent intent = new Intent(getApplicationContext(),PINActivity.class);

            // Le paso los datos de ususario al Intent
            intent.putExtra(IntentViewConstants.USER, userData.getName());
            intent.putExtra(IntentViewConstants.PIN, userData.getPin());
            intent.putExtra(IntentViewConstants.EMAIL,userData.getEmail());

            // Y llamo a la pantalla de PIN
            startActivity(intent);
        } else {
            // Ir a la pantalla de alta de usuario
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        }
    }
}
