package com.example.myapplication.database.dataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.CategoryPojo;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.database.DataBaseContract.CategoryEntry;

public class CategoryManager {


    public CategoryManager() {
    }


    public List<CategoryPojo> getCategories(OpenHelper dbHelper) {
        List<CategoryPojo> categories;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {CategoryEntry._ID,
                            CategoryEntry.COLUMN_NAME,
                            CategoryEntry.COLUMN_DESCRIPTION};

        String accountOrderBy = CategoryEntry.COLUMN_NAME + " ASC";

        Cursor categoryCur = db.query(CategoryEntry.TABLE_NAME,
                                      columns,
                                      null,
                                      null,
                                      null,
                                      null,
                                      accountOrderBy);

        categories = loadCategories(categoryCur);
        db.close();
        return categories;
    }


    private List<CategoryPojo> loadCategories(Cursor cursor) {
        List<CategoryPojo> categories = new ArrayList<>();

        int catIdPos = cursor.getColumnIndex(CategoryEntry._ID);
        int catNamePos = cursor.getColumnIndex(CategoryEntry.COLUMN_NAME);
        int catDescriptionPos = cursor.getColumnIndex(CategoryEntry.COLUMN_DESCRIPTION);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(catIdPos);
            String name = cursor.getString(catNamePos);
            String description = cursor.getString(catDescriptionPos);

            CategoryPojo category = new CategoryPojo(id, name, description);
            categories.add(category);
        }

        cursor.close();

        return categories;
    }


    public CategoryPojo findById(OpenHelper dbHelper, Long id) {
        CategoryPojo category;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = CategoryEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        String[] columns = {CategoryEntry._ID,
                            CategoryEntry.COLUMN_NAME,
                            CategoryEntry.COLUMN_DESCRIPTION};

        String accountOrderBy = CategoryEntry.COLUMN_NAME + " ASC";

        Cursor categoryCur = db.query(CategoryEntry.TABLE_NAME,
                                      columns,
                                      selection,
                                      selectionArgs,
                                      null,
                                      null,
                                      accountOrderBy);

        category = loadCategories(categoryCur).get(0);
        db.close();
        return category;
    }


    public CategoryPojo findByName(OpenHelper dbHelper, String name) {
        CategoryPojo category;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = CategoryEntry._ID + " = ?";
        String[] selectionArgs = {name};

        String[] columns = {CategoryEntry._ID,
                            CategoryEntry.COLUMN_NAME,
                            CategoryEntry.COLUMN_DESCRIPTION};

        String accountOrderBy = CategoryEntry.COLUMN_NAME + " ASC";

        Cursor categoryCur = db.query(CategoryEntry.TABLE_NAME,
                                      columns,
                                      selection,
                                      selectionArgs,
                                      null,
                                      null,
                                      accountOrderBy);

        category = loadCategories(categoryCur).get(0);

        return category;
    }


    public void insertCategory(OpenHelper dbHelper, CategoryPojo category) throws IllegalArgumentException {

        if (category.getName() == null || category.getName().isEmpty() || category.getName().trim().equals(""))
            throw new IllegalArgumentException("The name of the category cannot be empty");

        ContentValues values = new ContentValues();
        values.put(CategoryEntry.COLUMN_NAME, category.getName());
        values.put(CategoryEntry.COLUMN_DESCRIPTION, category.getDescription());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(CategoryEntry.TABLE_NAME, null, values);
    }


    public void updateCategory(OpenHelper dbHelper, CategoryPojo category, int categoryId) {
        String selection = CategoryEntry._ID + " = ?";
        String[] selectionArgs = {Integer.toString(categoryId)};

        ContentValues values = new ContentValues();
        values.put(CategoryEntry._ID, categoryId);
        values.put(CategoryEntry.COLUMN_NAME, category.getName());
        values.put(CategoryEntry.COLUMN_DESCRIPTION, category.getDescription());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(CategoryEntry.TABLE_NAME, values, selection, selectionArgs);
    }

}
