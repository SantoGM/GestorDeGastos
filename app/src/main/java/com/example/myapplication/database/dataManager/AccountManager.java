package com.example.myapplication.database.dataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.OpenHelper;
import com.example.myapplication.view.pojo.AccountPojo;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.database.DataBaseContract.AccountEntry;

/**
 * Class to handle all the operations related with Accounts
 */
public class AccountManager {

    private final int DISABLE = 1;
    private final int ENABLE  = 0;
    private final int BANK_ACCOUNT = 0;
    private final int CREDIT_CARD_ACCOUNT = 1;


    public AccountManager() {
    }


    /**
     * <h1>getAccounts</h1>
     * <p>Method to get all the accounts</p>
     * @param dbHelper - DB Handler
     * @return {@link List}<{@link AccountPojo}> - List of accounts found
     */
    public List<AccountPojo> getAccounts(OpenHelper dbHelper) {
        List<AccountPojo> accounts;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = AccountEntry.COLUMN_DISABLE + " = ?";

        String[] selectionArgs = {Integer.toString(ENABLE)};

        String[] columns = {AccountEntry._ID,
                AccountEntry.COLUMN_NAME,
                AccountEntry.COLUMN_TYPE,
                AccountEntry.COLUMN_DESCRIPTION,
                AccountEntry.COLUMN_BALANCE};

        String accountOrderBy = AccountEntry.COLUMN_NAME + " ASC";

        Cursor accountCur = db.query(AccountEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                accountOrderBy);

        accounts = loadAccounts(accountCur);
        db.close();
        return accounts;
    }


    /**
     * <h1>getBankAccounts</h1>
     * <p>Method to get all the <i>Bank accounts</i></p>
     * @param dbHelper - DB Handler
     * @return {@link List}<{@link AccountPojo}> - List of accounts found
     */
    public List<AccountPojo> getBankAccounts(OpenHelper dbHelper) {
        List<AccountPojo> accounts;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = AccountEntry.COLUMN_DISABLE + " = ? AND " + AccountEntry.COLUMN_TYPE + " = ?";

        String[] selectionArgs = {Integer.toString(ENABLE), Integer.toString(BANK_ACCOUNT)};

        String[] columns = {AccountEntry._ID,
                AccountEntry.COLUMN_NAME,
                AccountEntry.COLUMN_TYPE,
                AccountEntry.COLUMN_DESCRIPTION,
                AccountEntry.COLUMN_BALANCE};

        String accountOrderBy = AccountEntry.COLUMN_NAME + " ASC";

        Cursor accountCur = db.query(AccountEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                accountOrderBy);

        accounts = loadAccounts(accountCur);
        db.close();
        return accounts;
    }


    public List<AccountPojo> getCreditCardAccounts(OpenHelper dbHelper) {
        List<AccountPojo> accounts;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = AccountEntry.COLUMN_DISABLE + " = ? AND " + AccountEntry.COLUMN_TYPE + " = ?";

        String[] selectionArgs = {Integer.toString(ENABLE), Integer.toString(CREDIT_CARD_ACCOUNT)};

        String[] columns = {AccountEntry._ID,
                AccountEntry.COLUMN_NAME,
                AccountEntry.COLUMN_TYPE,
                AccountEntry.COLUMN_DESCRIPTION,
                AccountEntry.COLUMN_BALANCE};

        String accountOrderBy = AccountEntry.COLUMN_NAME + " ASC";

        Cursor accountCur = db.query(AccountEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                accountOrderBy);

        accounts = loadAccounts(accountCur);
        db.close();
        return accounts;
    }


    public AccountPojo findByIdWithDisabled(OpenHelper dbHelper, Long id) {
        AccountPojo account;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = AccountEntry._ID + " = ?";

        String[] selectionArgs = {Long.toString(id)};

        String[] columns = {AccountEntry._ID,
                AccountEntry.COLUMN_NAME,
                AccountEntry.COLUMN_TYPE,
                AccountEntry.COLUMN_DESCRIPTION,
                AccountEntry.COLUMN_BALANCE};

        String accountOrderBy = AccountEntry.COLUMN_NAME + " ASC";

        Cursor accountCur = db.query(AccountEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                accountOrderBy);

        account = loadAccounts(accountCur).get(0);
        db.close();
        return account;
    }


    public AccountPojo findById(OpenHelper dbHelper, Long id) {
        AccountPojo account;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = AccountEntry._ID + " = ? AND " + AccountEntry.COLUMN_DISABLE + " = ?";

        String[] selectionArgs = {Long.toString(id), Integer.toString(ENABLE)};

        String[] columns = {AccountEntry._ID,
                AccountEntry.COLUMN_NAME,
                AccountEntry.COLUMN_TYPE,
                AccountEntry.COLUMN_DESCRIPTION,
                AccountEntry.COLUMN_BALANCE};

        String accountOrderBy = AccountEntry.COLUMN_NAME + " ASC";

        Cursor accountCur = db.query(AccountEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                accountOrderBy);

        account = loadAccounts(accountCur).get(0);
        db.close();
        return account;
    }


    public AccountPojo findByName(OpenHelper dbHelper, String name) {
        AccountPojo account;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = AccountEntry.COLUMN_NAME + " = ? AND " + AccountEntry.COLUMN_DISABLE + " = ?";

        String[] selectionArgs = {name, Integer.toString(ENABLE)};

        String[] columns = {AccountEntry._ID,
                            AccountEntry.COLUMN_NAME,
                            AccountEntry.COLUMN_TYPE,
                            AccountEntry.COLUMN_DESCRIPTION,
                            AccountEntry.COLUMN_BALANCE};

        String accountOrderBy = AccountEntry.COLUMN_NAME + " ASC";

        Cursor accountCur = db.query(AccountEntry.TABLE_NAME,
                                     columns,
                                     selection,
                                     selectionArgs,
                                     null,
                                     null,
                                     accountOrderBy);

        account = loadAccounts(accountCur).get(0);

        return account;
    }


    public void insertAccount(OpenHelper dbHelper, AccountPojo account) throws IllegalArgumentException {

        Float balance;

        if (account.getName() == null || account.getName().isEmpty() || account.getName().trim().equals(""))
            throw new IllegalArgumentException("The name of the account cannot be empty");

        if (account.getType() != BANK_ACCOUNT && account.getType() != CREDIT_CARD_ACCOUNT)
            throw new IllegalArgumentException("It has to be a Bank Account or a Credit Card Account");

        if (account.getBalance() == null)
            balance = 0f;
        else
            balance = account.getBalance();

        ContentValues values = new ContentValues();
        values.put(AccountEntry.COLUMN_NAME, account.getName());
        values.put(AccountEntry.COLUMN_TYPE, account.getType());
        values.put(AccountEntry.COLUMN_DESCRIPTION, account.getDescription());
        values.put(AccountEntry.COLUMN_BALANCE, balance);
        values.put(AccountEntry.COLUMN_DISABLE, ENABLE);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(AccountEntry.TABLE_NAME, null, values);
    }


    public void updateAccount(OpenHelper dbHelper, AccountPojo account, Long accountId) {
        String selection = AccountEntry._ID + " = ? AND " + AccountEntry.COLUMN_DISABLE + " = ?";

        String[] selectionArgs = {Long.toString(accountId), Integer.toString(ENABLE)};

        ContentValues values = new ContentValues();
        values.put(AccountEntry._ID, accountId);
        values.put(AccountEntry.COLUMN_NAME, account.getName());
        values.put(AccountEntry.COLUMN_TYPE, account.getType());
        values.put(AccountEntry.COLUMN_DESCRIPTION, account.getDescription());
        values.put(AccountEntry.COLUMN_BALANCE, account.getBalance());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(AccountEntry.TABLE_NAME, values, selection, selectionArgs);
    }


    /**
     * <h1>deleteAccount</h1>
     * <p>Method to perform a logical delete (disable=1)</p>
     * @param dbHelper - DB handler
     * @param accountId - ID of the account to delete
     */
    public void deleteAccount(OpenHelper dbHelper, Long accountId) {
        String selection = AccountEntry._ID + " = ? AND " + AccountEntry.COLUMN_DISABLE + " = ?";

        String[] selectionArgs = {Long.toString(accountId), Integer.toString(ENABLE)};

        ContentValues values = new ContentValues();
        values.put(AccountEntry.COLUMN_DISABLE, DISABLE);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(AccountEntry.TABLE_NAME, values, selection, selectionArgs);
    }


    private List<AccountPojo> loadAccounts(Cursor cursor) {
        List<AccountPojo> accounts = new ArrayList<>();

        int accIdPos = cursor.getColumnIndex(AccountEntry._ID);
        int accNamePos = cursor.getColumnIndex(AccountEntry.COLUMN_NAME);
        int accCreditCard = cursor.getColumnIndex(AccountEntry.COLUMN_TYPE);
        int accDescriptionPos = cursor.getColumnIndex(AccountEntry.COLUMN_DESCRIPTION);
        int accBalancePos = cursor.getColumnIndex(AccountEntry.COLUMN_BALANCE);
        int accDisablePos = cursor.getColumnIndex(AccountEntry.COLUMN_DISABLE);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(accIdPos);
            String name = cursor.getString(accNamePos);
            Integer creditCard = cursor.getInt(accCreditCard);
            String description = cursor.getString(accDescriptionPos);
            Float balance = cursor.getFloat(accBalancePos);
            Integer disable = cursor.getInt(accDisablePos);

            AccountPojo account = new AccountPojo(id, name, creditCard, description, balance, disable);
            accounts.add(account);
        }

        cursor.close();

        return accounts;
    }

}
