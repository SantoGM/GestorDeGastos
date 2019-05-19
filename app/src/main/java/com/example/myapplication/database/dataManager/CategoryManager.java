package com.example.myapplication.database.dataManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.CategoryPojo;

import java.util.List;

import static com.example.myapplication.database.DataBaseContract.CategoryEntry;

public class CategoryManager {

    private static CategoryManager categoryManager = null;

    private List<CategoryPojo> categories;


    private CategoryManager() {
    }

    public static CategoryManager getInstance() {
        if (categoryManager == null) {
            categoryManager = new CategoryManager();
        }
        return categoryManager;
    }


    public List<CategoryPojo> getCategories() {
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

            CategoryPojo category = new CategoryPojo(id, name, description);
            cm.categories.add(category);
        }

        cursor.close();
    }


    public CategoryPojo findById(Long id) {
        for (CategoryPojo category : categories) {
            if (id.equals(category.getId()))  //TODO chk if equals works or we should change it to ==
                return category;
        }
        return null;
    }


    public CategoryPojo findByName(String name) {
        for (CategoryPojo category : categories) {
            if (name.equals(category.getName()))
                return category;
        }
        return null;
    }

}
