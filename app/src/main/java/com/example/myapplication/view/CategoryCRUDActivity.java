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
import com.example.myapplication.view.extras.CategoryAdapter;
import com.example.myapplication.view.pojo.CategoryPojo;

import java.util.List;

public class CategoryCRUDActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_crud);

        createMenu();

        initButtons();
        loadListView();
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

        List<CategoryPojo> categories = cm.getCategories(oh);
        if (!categories.isEmpty()) {
            CategoryAdapter categoryAdapter = new CategoryAdapter(getApplicationContext(), categories);
            listView.setAdapter(categoryAdapter);
        }
    }

}

