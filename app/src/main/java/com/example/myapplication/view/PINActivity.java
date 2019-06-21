package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.business.services.ConfigMail;
import com.example.myapplication.business.services.GMailSender;
import com.example.myapplication.database.dataManager.UserDataManager;
import com.example.myapplication.view.pojo.UserDataPojo;

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
                final String email = getIntent().getStringExtra(IntentViewConstants.EMAIL);

                final UserDataPojo usuario = UserDataManager.getInstance().getUserData(getApplicationContext());

                new Thread(new Runnable() {
                    @SuppressLint("SdCardPath") public void run() {
                        Boolean success = Boolean.FALSE;
                        try {
                            GMailSender sender = new GMailSender();
                            String body = ConfigMail.HI_PIN + usuario.getName()+'\n'+'\n'+
                                          ConfigMail.BODY_PIN + usuario.getPin().toString()+'\n'+'\n'+
                                          ConfigMail.SIGN;

                            sender.sendMail(ConfigMail.SUBJECT_PIN, body, ConfigMail.MAIL, usuario.getEmail());
                            Log.i("Mail", "Sent");
                            success = Boolean.TRUE;
                        } catch (Exception e) {
                            Log.i("Mail", "Failed"+e);
                        } finally {
                            final String msg;
                            if (success) {
                                msg = getString(R.string.success_pin_sent_to_mail) + " " + usuario.getEmail();
                            } else {
                                msg = getString(R.string.error_unable_to_send_pin_by_mail);
                            }
                            runOnUiThread(new Runnable()
                            {
                                public void run()
                                {
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        // Forgot PIN button
        final TextView lblIngresoPin = findViewById(R.id.lblIngresoPin);
        lblIngresoPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 4){
                    hideKeyboard(PINActivity.this);
                }
            }
        });
    }

    private boolean validateInput() {
        Boolean result = Boolean.TRUE;

        Integer pin = getIntent().getIntExtra(IntentViewConstants.PIN, INVALID_PIN);
        EditText txtComponent = findViewById(R.id.lblIngresoPin);
        Editable txtInput = txtComponent.getText();
        Integer input = (txtInput.toString().isEmpty()) ? NULL_PIN : Integer.parseInt(txtInput.toString());

        if (input.toString().length() < 4) {
            result = buildError(txtComponent, getString(R.string.error_complete_pin));
        } else if (!input.equals(pin)) {
            txtInput.clear();
            result = buildError(txtComponent, getString(R.string.error_wrong_pin));
        }
        return result;
    }

    private void greetUser() {
        String user = getIntent().getStringExtra(IntentViewConstants.USER);
        if (user != null && !user.isEmpty()) {
            TextView text = findViewById(R.id.lblGreet);
            text.setText(getString(R.string.message_greet_user, user));
        }
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

    private static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
