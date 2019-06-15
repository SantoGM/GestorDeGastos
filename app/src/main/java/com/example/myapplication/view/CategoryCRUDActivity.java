package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.businessLogic.CategoryFacade;
import com.example.myapplication.businessLogic.PersistentDataModel;
import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dataManager.CategoryManager;
import com.example.myapplication.view.pojo.CategoryPojo;

import java.util.List;

public class CategoryCRUDActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_crud);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //createMenu();

        initButtons();
        loadListView();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadListView();
    }

    private void initButtons(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddCategoryActivity.class));
            }
        });
    }

    private void loadListView(){
        OpenHelper oh = new OpenHelper(getApplicationContext());
        CategoryManager cm = new CategoryManager();
        ListView listView = findViewById(R.id.listCategories);

        List<CategoryPojo> categorias = cm.getCategories(oh);
        if (!categorias.isEmpty()) {
            ArrayAdapter<PersistentDataModel> categoryAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, CategoryFacade.getInstance().getCategoryDataModel(getApplicationContext()));
            listView.setAdapter(categoryAdapter);
        }
    }

}

