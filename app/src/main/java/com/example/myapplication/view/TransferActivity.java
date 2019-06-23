package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

public class TransferActivity extends BaseActivity {

    private FloatingActionMenu fam;
    private FloatingActionButton fabTrfInternal, fabTrfExternal, fabTrfPayCreditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        createMenu();

        initFabMenu();
    }

    private void initFabMenu() {
        fabTrfInternal =  findViewById(R.id.fabTrfInternal);
        fabTrfExternal = findViewById(R.id.fabTrfExternal);
        fabTrfPayCreditCard = findViewById(R.id.fabTrfPayCard);
        fam = findViewById(R.id.fabTrfMenu);

        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {

            }
        });

        fabTrfInternal.setOnClickListener(onButtonClick());
        fabTrfExternal.setOnClickListener(onButtonClick());
        fabTrfPayCreditCard.setOnClickListener(onButtonClick());

        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                }
            }
        });
    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fabTrfInternal) {
                    startActivity(new Intent(getApplicationContext(), ActivityAddTransfer.class));
                } else if (view == fabTrfExternal){
                    //startActivity(new Intent(getApplicationContext(), CardCRUDActivity.class));
                } else if (view == fabTrfPayCreditCard){
                    startActivity(new Intent(getApplicationContext(), ActivityPayCreditCard.class));
                }
                fam.close(Boolean.TRUE);
            }
        };
    }
}
