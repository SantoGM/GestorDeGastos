package com.example.myapplication.database.dataManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DataBaseContract;
import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.database.dao.CategoryDAO;

import java.util.List;

import static com.example.myapplication.database.DataBaseContract.*;

public class CategoryManager {

    private static CategoryManager categoryManager = null;

    private List<CategoryDAO> categories;


    private CategoryManager() {
    }

    public static CategoryManager getInstance() {
        if (categoryManager == null) {
            categoryManager = new CategoryManager();
        }
        return categoryManager;
    }


    public List<CategoryDAO> getCategories() {
        return categories;
    }


    public static void loadFromDB(OpenHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {CategoryEntry._ID,
                            CategoryEntry.COLUMN_NAME,
                            CategoryEntry.COLUMN_DESCRIPTION};

        Cursor categoryCur = db.query(CategoryEntry.TABLE_NAME,
                                      columns,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null);

        loadCategories(categoryCur);

    }

    private static void loadCategories(Cursor cursor) {
        int catIdPos = cursor.getColumnIndex(CategoryEntry._ID);
        int catNamePos = cursor.getColumnIndex(CategoryEntry.COLUMN_NAME);
        int catDescriptionPos = cursor.getColumnIndex(CategoryEntry.COLUMN_DESCRIPTION);

        CategoryManager cm = getInstance();
        cm.categories.clear();

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(catIdPos);
            String name = cursor.getString(catNamePos);
            String description = cursor.getString(catDescriptionPos);

            CategoryDAO categoryDAO = new CategoryDAO(id, name, description);
            cm.categories.add(categoryDAO);
        }

        cursor.close();
    }


    public CategoryDAO findById(Long id) {
        for (CategoryDAO category : categories) {
            if (id.equals(category.getId()))  //TODO chk if equals works or we should change it to ==
                return category;
        }
        return null;
    }


    public CategoryDAO findByName(String name) {
        for (CategoryDAO category : categories) {
            if (name.equals(category.getName()))
                return category;
        }
        return null;
    }

}
