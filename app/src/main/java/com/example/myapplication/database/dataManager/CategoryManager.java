package com.example.myapplication.database.dataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.CategoryPojo;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.database.DataBaseContract.CategoryEntry;

/**
 * Class to handle all the operations related with <i>Categories</i>
 */
public class CategoryManager {

    private final int DISABLE = 1;
    private final int ENABLE  = 0;


    public CategoryManager() {
    }


    /**
     * <h1>getCategories</h1>
     * <p>Method to get all the enabled <i>Categories</i></p>
     * @param dbHelper - DB Handler
     * @return {@link List}<{@link CategoryPojo}> - List of the categories found
     */
    public List<CategoryPojo> getCategories(OpenHelper dbHelper) {
        List<CategoryPojo> categories;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = CategoryEntry.COLUMN_DISABLE + " = ?";

        String[] selectionArgs = {Integer.toString(ENABLE)};

        String[] columns = {CategoryEntry._ID,
                            CategoryEntry.COLUMN_NAME,
                            CategoryEntry.COLUMN_DESCRIPTION,
                            CategoryEntry.COLUMN_DISABLE};

        String accountOrderBy = CategoryEntry.COLUMN_NAME + " ASC";

        Cursor categoryCur = db.query(CategoryEntry.TABLE_NAME,
                                      columns,
                                      selection,
                                      selectionArgs,
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
        int catDisablePos = cursor.getColumnIndex(CategoryEntry.COLUMN_DISABLE);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(catIdPos);
            String name = cursor.getString(catNamePos);
            String description = cursor.getString(catDescriptionPos);
            Integer disable = cursor.getInt(catDisablePos);

            CategoryPojo category = new CategoryPojo(id, name, description, disable);
            categories.add(category);
        }

        cursor.close();

        return categories;
    }


    /**
     * <h1>findByIdWithDisabled</h1>
     * <p>Method to find a category by ID including the disabled ones</p>
     * @param dbHelper - DB handler
     * @param idCategory - ID of the category
     * @return {@link CategoryPojo} - The category found or NULL if the category doesn't exist
     */
    public CategoryPojo findByIdWithDisabled(OpenHelper dbHelper, Long idCategory) {
        CategoryPojo category;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = CategoryEntry._ID + " = ?";

        String[] selectionArgs = {Long.toString(idCategory)};

        String[] columns = {CategoryEntry._ID,
                CategoryEntry.COLUMN_NAME,
                CategoryEntry.COLUMN_DESCRIPTION,
                CategoryEntry.COLUMN_DISABLE};

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


    /**
     * <h1>findById</h1>
     * <p>Method to find a category by ID</p>
     * @param dbHelper - DB handler
     * @param idCategory - ID of the category
     * @return {@link CategoryPojo} - The category found or NULL if the category doesn't exist
     */
    public CategoryPojo findById(OpenHelper dbHelper, Long idCategory) {
        CategoryPojo category;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = CategoryEntry._ID + " = ? AND " + CategoryEntry.COLUMN_DISABLE + " = ?";

        String[] selectionArgs = {Long.toString(idCategory), Integer.toString(ENABLE)};

        String[] columns = {CategoryEntry._ID,
                CategoryEntry.COLUMN_NAME,
                CategoryEntry.COLUMN_DESCRIPTION,
                CategoryEntry.COLUMN_DISABLE};

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


    /**
     * <h1>findByName</h1>
     * <p>Method to find a category by name</p>
     * @param dbHelper - DB handler
     * @param name - Name of the category
     * @return {@link CategoryPojo} - The category found or NULL id the category doesn't exist
     */
    public CategoryPojo findByName(OpenHelper dbHelper, String name) {
        CategoryPojo category;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = CategoryEntry._ID + " = ? AND " + CategoryEntry.COLUMN_DISABLE + " = ?";

        String[] selectionArgs = {name, Integer.toString(ENABLE)};

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


    /**
     * <h1>insertCategory</h1>
     * <p>Method to insert a category</p>
     * @param dbHelper - DB handler
     * @param category - {@link CategoryPojo} category to insert
     * @throws IllegalArgumentException
     */
    public void insertCategory(OpenHelper dbHelper, CategoryPojo category) throws IllegalArgumentException {

        if (category.getName() == null || category.getName().isEmpty() || category.getName().trim().equals(""))
            throw new IllegalArgumentException("The name of the category cannot be empty");

        ContentValues values = new ContentValues();
        values.put(CategoryEntry.COLUMN_NAME, category.getName());
        values.put(CategoryEntry.COLUMN_DESCRIPTION, category.getDescription());
        values.put(CategoryEntry.COLUMN_DISABLE, ENABLE);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(CategoryEntry.TABLE_NAME, null, values);
    }


    /**
     * <h1>updateCategory</h1>
     * <p>Method to update a category</p>
     * @param dbHelper - DB handler
     * @param category - {@link CategoryPojo} category to update
     * @param categoryId - ID of the category to update
     */
    public void updateCategory(OpenHelper dbHelper, CategoryPojo category, Long categoryId) {
        String selection = CategoryEntry._ID + " = ? AND " + CategoryEntry.COLUMN_DISABLE + " = ?";

        String[] selectionArgs = {Long.toString(categoryId), Integer.toString(ENABLE)};

        ContentValues values = new ContentValues();
        values.put(CategoryEntry._ID, categoryId);
        values.put(CategoryEntry.COLUMN_NAME, category.getName());
        values.put(CategoryEntry.COLUMN_DESCRIPTION, category.getDescription());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(CategoryEntry.TABLE_NAME, values, selection, selectionArgs);
    }


    /**
     * <h1deleteCategory></h1>
     * <p>Method to perform a logical delete (disable=1)</p>
     * @param dbHelper - DB handler
     * @param categoryId- ID of the category to delete
     */
    public void deleteCategory(OpenHelper dbHelper, Long categoryId) {
        String selection = CategoryEntry._ID + " = ? AND " + CategoryEntry.COLUMN_DISABLE + " = ?";
        String[] selectionArgs = {Long.toString(categoryId), Integer.toString(ENABLE)};

        ContentValues values = new ContentValues();
        values.put(CategoryEntry.COLUMN_DISABLE, DISABLE);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(CategoryEntry.TABLE_NAME, values, selection, selectionArgs);
    }

}
